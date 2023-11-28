package katewarne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class FullStory extends JFrame {
    private JLabel hImageLabel;
    private JLabel textLabel1;
    private JLabel textLabel2;
    private JLabel chatButton;
    private boolean isFirstClick = true;

    private List<String> conversations; // 대화 내용을 저장하는 리스트
    private int currentConversationIndex = 0; // 현재 대화 인덱스

    public FullStory() {
        setTitle("케이트 와르네: 대저택 살인사건");
        setSize(800, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        conversations = new ArrayList<>(); // 대화 내용을 저장할 리스트 초기화

        hImageLabel = new JLabel();
        hImageLabel.setBounds(0, 0, 800, 600);
        add(hImageLabel);
        addTextLabels();

        addChatButton("./assets/images/chatButton.png", 650, 525);
        addChatBoxImage("./assets/images/chatBoxImage.png", 78, 390);
        addImageLabel("./assets/images/warne.png", 100, 100); // 탐정 캐릭터 이미지
        addImageLabel("./assets/images/BathroomImage.png", 0, 0); // 욕실 배경 이미지
        setVisible(true);

        initializeConversations(); // 대화 내용 초기화
        updateConversation(); // 초기 대화 출력
    }

    private void addTextLabels() {
        // 캐릭터 이름
        textLabel1 = new JLabel("케이트 와르네");
        textLabel1.setForeground(Color.WHITE);
        textLabel1.setFont(new Font("Malgun Gothic", Font.BOLD, 18));
        textLabel1.setBounds(120, 450, 600, 30);
        add(textLabel1);

        // 대화 내용
        textLabel2 = new JLabel();
        textLabel2.setForeground(Color.WHITE);
        textLabel2.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
        textLabel2.setBounds(120, 490, 600, 50);
        add(textLabel2);
    }

    private void addImageLabel(String imagePath, int x, int y) {
        JLabel label = new JLabel();
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();
        Image sizedImage = image.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        ImageIcon sizedIcon = new ImageIcon(sizedImage);
        label.setIcon(sizedIcon);
        label.setBounds(x, y, 800, 600);
        add(label);
    }

    private void addChatBoxImage(String imagePath, int x, int y) {
        JLabel chatBoxLabel = new JLabel();
        ImageIcon chatBoxIcon = new ImageIcon(imagePath);
        Image chatBoxImage = chatBoxIcon.getImage();
        Image sizedChatBoxImage = chatBoxImage.getScaledInstance(650, 200, Image.SCALE_SMOOTH);
        ImageIcon sizedChatBoxIcon = new ImageIcon(sizedChatBoxImage);
        chatBoxLabel.setIcon(sizedChatBoxIcon);
        chatBoxLabel.setBounds(x, y, 650, 200);
        add(chatBoxLabel);
    }

    private void addChatButton(String imagePath, int x, int y) {
        chatButton = new JLabel();
        ImageIcon buttonIcon = new ImageIcon(imagePath);
        Image buttonImage = buttonIcon.getImage();
        Image sizedButtonImage = buttonImage.getScaledInstance(25, 15, Image.SCALE_SMOOTH);
        ImageIcon sizedButtonIcon = new ImageIcon(sizedButtonImage);
        chatButton.setIcon(sizedButtonIcon);


        int width = sizedButtonIcon.getIconWidth();
        int height = sizedButtonIcon.getIconHeight();
        chatButton.setBounds(x, y, width, height);

        chatButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (currentConversationIndex < conversations.size() - 1) {
                    currentConversationIndex++;
                    updateConversation();
                } else {
                    // 대화가 끝난 경우 missioncomplete 띄우기
                    ImageIcon missionCompleteIcon = new ImageIcon("./assets/images/mission.png");
                    Image missionCompleteImage = missionCompleteIcon.getImage();
                    Image sizedMissionCompleteImage = missionCompleteImage.getScaledInstance(800, 500, Image.SCALE_SMOOTH);
                    missionCompleteIcon = new ImageIcon(sizedMissionCompleteImage);
                    hImageLabel.setIcon(missionCompleteIcon);

                    // 이미지의 위치를 설정
                    int missionCompleteWidth = missionCompleteIcon.getIconWidth();
                    int missionCompleteHeight = missionCompleteIcon.getIconHeight();
                    hImageLabel.setBounds(100, 100, missionCompleteWidth, missionCompleteHeight);

                    chatButton.setVisible(false); // 대화 버튼 감춤
                }
            }
        });
        add(chatButton);
    }
    private void initializeConversations() {
        conversations.add("김변호가 고등학생 시절, 홍작가에 의해 화재 사건이 일어났어.");
        conversations.add("<html>홍작가는 소설을 쓰는데 사용하기 위해 직접 방화를 저질렀고<br> 그 사건으로 김변호의 절친한 친구가 억울하게 죽었지..</html>");
        conversations.add("<html>김변호는 오직 친구의 억울한 죽음의 진실을 파헤치기 위해 살아왔고, <br> 결국 홍작가가 범인인 걸 알게 되었어.</html>");
        conversations.add("그래서 변호사로서 홍작가에게 접근해 살인을 저지른거지..");
        conversations.add("홍작가가 반신욕을 하는 틈을 타 RC카를 조종해 스피커로 감전되게 했어.");
        conversations.add("<html>반신욕에 쓰던 입욕제는 전기를 통하게 했고, <br>홍변호는 절연 장갑을 이용해 감전을 피했지.</html>");
        conversations.add("그렇게 홍작가는 사망한거야..");
    }

    private void updateConversation() {
        textLabel2.setText(conversations.get(currentConversationIndex));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FullStory();
        });
    }
}