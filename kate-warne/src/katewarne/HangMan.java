package katewarne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;  
import java.awt.event.MouseEvent;   
import java.awt.event.MouseListener;

public class HangMan extends JPanel {
    private ImageIcon backgroundImage;
    private ImageIcon levelImage;
    private String answer = "lawyer"; // 정답 단어
    private StringBuilder guessedWord; // 현재까지 맞춘 글자들을 저장하는 변수
    private JButton retryButton; //다시 시작 버튼
    private JLabel instructionLabel;
    private Key key;  // Key 클래스 인스턴스 추가
    private int lives = 9; // 목숨 수
    private int currentLevel = 2; // 현재 레벨

    public HangMan() {
    	key = Key.getInstance();
        setLayout(new BorderLayout());
        
        // 배경 이미지 설정
        backgroundImage = new ImageIcon("./assets/images/background.png");

        // 이미지 레이블 추가
        levelImage = new ImageIcon("./assets/images/level1.png");

        // 정답 단어의 길이만큼 '_'로 초기화된 문자열을 guessedWord에 저장
        guessedWord = new StringBuilder("_".repeat(answer.length()));
        
        // 게임 설명 레이블 
        ImageIcon instructionIcon = new ImageIcon("./assets/images/hangman_gameinstruction.png");
        Image instructionImage = instructionIcon.getImage().getScaledInstance(765, 550, Image.SCALE_SMOOTH);
        ImageIcon resizedInstructionIcon = new ImageIcon(instructionImage);
        instructionLabel = new JLabel(resizedInstructionIcon);
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        

        // 버튼 패널 생성 및 추가
        JPanel buttonPanel = new JPanel(new GridLayout(2, 13)); // 2행 13열의 그리드 레이아웃
        buttonPanel.setOpaque(false);
        buttonPanel.setVisible(false);
        add(buttonPanel, BorderLayout.NORTH);
        buttonPanel.setPreferredSize(new Dimension(getWidth(), 100));
        
        // A부터 Z까지의 버튼 생성 및 추가
        for (char c = 'A'; c <= 'Z'; c++) {
            JButton button = createAlphabetButton(String.valueOf(c));
            button.addActionListener(new ButtonClickListener(button, buttonPanel));
            buttonPanel.add(button);
        }

        // 정답을 표시하는 레이블 추가
        JLabel answerLabel = new JLabel(guessedWord.toString(), SwingConstants.CENTER);
        answerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        answerLabel.setForeground(Color.WHITE);

        // 중앙정렬을 위한 패널
        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(answerLabel);

        
        instructionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                instructionLabel.setVisible(false);
                buttonPanel.setVisible(true);
             // 메인 패널의 중앙에 centerPanel 추가
                add(centerPanel, BorderLayout.CENTER);
                
            }
        });

        retryButton = new JButton("Retry");
        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        
        add(instructionLabel, BorderLayout.CENTER);

    }
    
    private void updateAnswerLabel() {
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                Component[] innerComponents = panel.getComponents();
                for (Component innerComponent : innerComponents) {
                    if (innerComponent instanceof JLabel) {
                        JLabel label = (JLabel) innerComponent;
                        label.setText(guessedWord.toString());
                    }
                }
            }
        }
    }
    
    // 게임을 초기 상태로 재설정하는 메서드
    private void resetGame() {
        // 변수와 구성 요소를 초기 상태로 재설정
        lives = 9;
        currentLevel = 2;
        levelImage = new ImageIcon("./assets/images/level1.png");
        guessedWord = new StringBuilder("_".repeat(answer.length()));

        // 모든 알파벳 버튼을 활성화
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                Component[] innerComponents = panel.getComponents();
                for (Component innerComponent : innerComponents) {
                    if (innerComponent instanceof JButton) {
                        JButton button = (JButton) innerComponent;
                        button.setEnabled(true);
                    }
                }
            }
        }

        // 정답 레이블 업데이트
        updateAnswerLabel();

        // 패널 다시 그리기
        repaint();
        
        
    }
    
    

    private JButton createAlphabetButton(String text) {
        JButton button = new JButton(text);
        button.setBorderPainted(false); // 테두리 제거
        button.setContentAreaFilled(false); // 내용 영역 채우기 제거
        button.setFocusPainted(false); // 포커스 표시 제거
        button.setForeground(Color.WHITE); // 글씨색을 흰색으로 설정
        button.setFont(new Font("SansSerif", Font.BOLD, 18)); // 글씨 크기 조정

        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 배경 이미지 그리기
        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        // level 이미지 그리기
        g.drawImage(levelImage.getImage(), 100, 150, 550, 400, this);
    }
    



    // 버튼 클릭 리스너
    private class ButtonClickListener implements ActionListener {
        private JButton button;
        private JPanel buttonPanel;

        public ButtonClickListener(JButton button, JPanel buttonPanel) {
            this.button = button;
            this.buttonPanel = buttonPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // 버튼을 비활성화
            button.setEnabled(false);

            char guessedChar = button.getText().charAt(0);
            boolean correctGuess = false;

            for (int i = 0; i < answer.length(); i++) {
                if (Character.toLowerCase(answer.charAt(i)) == Character.toLowerCase(guessedChar)) {
                    guessedWord.setCharAt(i, guessedChar);
                    correctGuess = true;
                }
            }

            // 정답을 표시하는 레이블 업데이트
            updateAnswerLabel();

            // 정답을 모두 맞추면 게임 종료
            if (guessedWord.toString().equalsIgnoreCase(answer)) {

            	
                int option = JOptionPane.showOptionDialog(HangMan.this,
                        "축하합니다! 단어를 맞췄습니다.", "게임 종료", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, null, null);

                if (option == JOptionPane.OK_OPTION) {
                	setVisible(false);
        	        Choice.getInstance().setVisible(true);
        	    }

               
            } else {
                // 틀린 경우 목숨 감소 및 레벨 변경
                if (!correctGuess) {
                    lives--;

                    // 목숨이 남아있으면 게임 계속 진행
                    if (lives > 0) {
                        updateLevelImage();
                    } else {
                        // 목숨이 없으면 게임 종료
                        Object[] options = {"Retry", "Exit"};
                        int option = JOptionPane.showOptionDialog(HangMan.this,
                                "Game Over", "게임 종료", JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                        if (option == 0) {
                            resetGame(); // "Retry" 버튼을 누르면 게임 초기화
                        } else {
                            System.exit(0); // "Exit" 버튼이나 다른 버튼을 누르면 종료
                        }
                    }
                }
            }
        }

        // updateLevelImage 메서드 추가
        private void updateLevelImage() {
            // 현재 레벨에 따라 이미지 업데이트
            if (currentLevel <= 9) {
                levelImage = new ImageIcon("./assets/images/level" + currentLevel + ".png");
                currentLevel++;
                repaint();
            }
        }
    }


}