package katewarne;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.text.DefaultCaret;

public class readletter extends JFrame {
    private JTextArea textArea;

    private String textToShow = 
    		"안녕하세요, 케이트 와르네 탐정님\n\n" 
    		+ "사건을 의뢰하기 위해 이렇게 편지를 보냅니다.\n"
            + "저는 현재 총 3권의 대작을 지필하여 그래도 꽤 유명한 소설가 hong입니다.\n" 
    		+ "제가 지내고 있는 집에 수상한 기운이 느껴져 이렇게 미리 편지를 적습니다.\n"
            + "만약 이 편지가 탐정님 손에 있다면 저에게 무슨 일이 있는 거겠죠...\n" 
    		+ "저희 집에는 총 두 명의 손님이 계시는데,\n"
            + "한 분은 3년 째 같이 일하고 계시는 변호사로 같이 지내면서 저의 법적 사항들을 관리하고 있습니다.\n"
            + "다른 한 분은 저의 차기작 집필과 자문을 위해 5일 전부터 대저택에 묵고 계시는 자문가 손님입니다.\n"
            + "아무래도 저의 집 분위기가 심상치 않아 이렇게 적어둡니다.\n" 
            + "만일 사건이 일어났다면... 해결해주시길 바랍니다.\n\n" + "이상. 소설가 hong 올림.";

    private int currentIndex = 0;

    public readletter() {
        setTitle("케이트 와르네: 대저택 살인사건");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        String imagePath = "./assets/images/KakaoTalk_20231110_000207744.jpg";
        JLabel backgroundLabel = new JLabel(new ImageIcon(imagePath));
        add(backgroundLabel, BorderLayout.CENTER);

        textArea = new JTextArea();
        textArea.setFont(new Font("Malgun Gothic", Font.PLAIN, 16));
        textArea.setForeground(Color.BLACK);
        textArea.setOpaque(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.add(textArea);

        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < textToShow.length()) {
                    textArea.append(String.valueOf(textToShow.charAt(currentIndex)));
                    currentIndex++;

                    if (textArea.getLineCount() >= textArea.getHeight() / textArea.getFont().getSize()) {
                        textArea.append("\n");
                    }
                } else {
                    ((Timer) e.getSource()).stop();

                    // Add code to navigate to bathRoomChat
                    dispose();
                    new bathRoomChat();
                }
            }
        });

        timer.start();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new readletter());
    }
}
