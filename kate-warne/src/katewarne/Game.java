package katewarne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame {
    private Image backgroundImage;
    private Timer timer;
    private JButton lastClickedButton; // 마지막으로 클릭된 버튼
    private int remainingButtons; // 남은 버튼의 수

    public Game() {
        // JFrame 설정
        setTitle("카드 뒤집기 게임");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 배경 이미지 로드
        String imagePath = "./asset/image/game/darkBackground.png";
        backgroundImage = new ImageIcon(imagePath).getImage();

        // JFrame에 컨텐츠 팬 추가
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBackground(g);
            }
        };

        // JButton 생성 및 추가
        for (int i = 1; i <= 20; i++) {
            JButton button = createButton(i);
            mainPanel.add(button);
        }

        // JFrame에 컨텐츠 팬으로 mainPanel 추가
        setContentPane(mainPanel);

        // Timer 설정
        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideImages();
                lastClickedButton = null; // 타이머가 끝나면 마지막 클릭 버튼 초기화
            }
        });

        // JFrame 표시 설정
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createButton(int index) {
        JButton button = new JButton("뒤집기");

        int buttonSize = 100; // 버튼 크기
        button.setPreferredSize(new Dimension(buttonSize, buttonSize)); // 크기 조정

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 이미지 표시
                showImage(button, index, buttonSize);
            }
        });

        return button;
    }

    private void drawBackground(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    private void showImage(JButton button, int index, int buttonSize) {
        String imagePath = getButtonImagePath(index);
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH);

        // 이미지 표시
        button.setIcon(new ImageIcon(scaledImage));

        // 마지막 클릭된 버튼 기록
        lastClickedButton = button;

        // 남은 버튼의 수 초기화
        remainingButtons = getContentPane().getComponentCount();

        // 타이머 시작
        timer.restart();
    }

    private void hideImages() {
        if (remainingButtons > 1) {
            // 마지막 클릭 버튼을 제외하고 모든 버튼의 이미지 숨기기
            for (Component component : getContentPane().getComponents()) {
                if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    if (button != lastClickedButton) {
                        button.setIcon(null);
                    }
                }
            }
        }
    }

    private String getButtonImagePath(int index) {
        // 각 버튼에 대한 이미지 경로 반환 (이미지가 10개씩 반복됨)
        int imageIndex = (index - 1) % 10 + 1;
        return "./asset/image/game/button" + imageIndex + ".png";
    }

    public static void main(String[] args) {
        new Game();
    }
}
