package katewarne;

import javax.swing.*;
import java.awt.*;

public class Key {
    private static Key instance;  // 싱글톤 인스턴스
    private int successGames;      // 성공한 미니게임 수
    private JLabel[] keyLabels;    // 열쇠 이미지를 표시하는 JLabel 배열

    private Key() {
        this.successGames = 0;
        this.keyLabels = new JLabel[3];  // 세 개의 열쇠 이미지를 표시할 JLabel 배열 생성
        initializeKeyLabels();  // JLabel 초기화 메서드 호출
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
        }
    }

    public void setKeyImage(int index, boolean success) {
        if (success && index >= 0 && index < 3) {
            keyLabels[index].setIcon(new ImageIcon("./assets/images/keyImage.png"));  // 미니게임 성공 시 열쇠 이미지 변경
            successGames++;  // 성공한 미니게임 수 증가
        }
    }

    public int getSuccessfulGames() {
        return successGames;
    }

    public JLabel[] getKeyLabels() {
        return keyLabels;
    }
}