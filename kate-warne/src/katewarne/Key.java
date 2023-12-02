package katewarne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Key {
    private static Key instance;  // 싱글톤 인스턴스
    private int successGames;      // 성공한 미니게임 수
    private JLabel[] keyLabels;    // 열쇠 이미지를 표시하는 JLabel 배열

    private Key() {
        this.successGames = 0;
        this.keyLabels = new JLabel[3];  // 세 개의 열쇠 이미지를 표시할 JLabel 배열 생성
        initializeKeyLabels();  // JLabel 초기화 메서드 호출
        
     // 열쇠 라벨에 마우스 클릭 이벤트
        for (int i = 0; i < 3; i++) {
            final int index = i;
            keyLabels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showKeyDescription(index);
                }
            });
        }
    }

    public static Key getInstance() {
        if (instance == null) {
            instance = new Key();
        }
        return instance;
    }

    private void initializeKeyLabels() {
        for (int i = 0; i < 3; i++) {
            keyLabels[i] = new JLabel();
            keyLabels[i].setIcon(new ImageIcon("./assets/images/greykeyImage.png"));  // 초기에 회색 열쇠 이미지로 설정
            keyLabels[i].setBounds(20 + i * 70, 20, 60, 60);  // 열쇠의 위치 및 크기 설정
            keyLabels[i].setEnabled(false);
        }
    }

    public void setKeyImage(int index, boolean success) {
        if (index >= 0 && index < 3) {
            keyLabels[index].setIcon(new ImageIcon(success ? "./assets/images/keyImage.png" : "./assets/images/greykeyImage.png"));
            successGames++;  // 성공한 미니게임 수 증가

            if (success) {
                keyLabels[index].setEnabled(true);  // 미니게임에 성공한 경우에만 마우스 이벤트 활성화
            } else {
                keyLabels[index].setEnabled(false);  // 미니게임에 실패한 경우에는 마우스 이벤트 비활성화
            }
        }
    }

    public int getSuccessfulGames() {
        return successGames;
    }

    public JLabel[] getKeyLabels() {
        return keyLabels;
    }
    private void showKeyDescription(int index) {
        if (keyLabels[index].isEnabled()) {  // 열쇠가 활성화된 경우에만 설명창 띄우기
            String keyImagePath;
            switch (index) {
                case 0:
                    keyImagePath = "./assets/images/guestKey.png";
                    break;
                case 1:
                    keyImagePath = "./assets/images/writerKey.png";
                    break;
                case 2:
                    keyImagePath = "./assets/images/lawyerKey.png";
                    break;
                default:
                    keyImagePath = "";
                    break;
            }

            if (!keyImagePath.isEmpty()) {
                ImageIcon keyIcon = new ImageIcon(keyImagePath);
                Image keyImage = keyIcon.getImage().getScaledInstance(550, 400, Image.SCALE_SMOOTH);
                ImageIcon resizedKeyIcon = new ImageIcon(keyImage);

                // 열쇠 설명창을 띄우는 코드
                JLabel keyDescriptionLabel = new JLabel(resizedKeyIcon);
                keyDescriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

                JFrame keyDescriptionFrame = new JFrame("열쇠 다시보기");
                keyDescriptionFrame.setSize(550, 400);
                keyDescriptionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                keyDescriptionFrame.getContentPane().add(keyDescriptionLabel, BorderLayout.CENTER);
                keyDescriptionFrame.setLocationRelativeTo(null);
                keyDescriptionFrame.setVisible(true);
            }
        }
    }
}