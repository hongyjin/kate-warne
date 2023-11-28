package katewarne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Anagram extends JPanel {

    private static final String TARGET_WORD = "insulation";
    private List<CharacterLabel> characterLabels;
    private Image backgroundImage;
    private JLabel instructionLabel;
    public Anagram() {
        // 배경 이미지
        ImageIcon backgroundImageIcon = new ImageIcon("./assets/images/background.png");
        backgroundImage = backgroundImageIcon.getImage();

        

        // 정답 입력창
        JTextField answerField = new JTextField();
        answerField.setFont(new Font("Arial", Font.PLAIN, 18));
        answerField.setHorizontalAlignment(JTextField.CENTER);
        answerField.setBounds(170, 500, 300, 30);
        

        // 확인 버튼
        JButton checkButton = new JButton("정답 입력");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userAnswer = answerField.getText();
                if (userAnswer.equalsIgnoreCase(TARGET_WORD)) {
                    JOptionPane.showMessageDialog(Anagram.this, "맞았습니다!");
                } else {
                    JOptionPane.showMessageDialog(Anagram.this, "틀렸습니다. 다시 입력해보세요");
                }
            }
        });
        checkButton.setBounds(470, 500, 120, 30);
        
        // 게임 설명 레이블 
        ImageIcon instructionIcon = new ImageIcon("./assets/images/anagram_instruction.png");
        Image instructionImage = instructionIcon.getImage().getScaledInstance(765, 550, Image.SCALE_SMOOTH);
        ImageIcon resizedInstructionIcon = new ImageIcon(instructionImage);
        instructionLabel = new JLabel(resizedInstructionIcon);
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
       
        instructionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                instructionLabel.setVisible(false);
                initializeCharacterLabels();
                add(checkButton);
                add(answerField);
                setLayout(null);
            }
        });
        add(instructionLabel);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 배경 이미지 그리기
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    private void initializeCharacterLabels() {
        characterLabels = new ArrayList<>();
        for (int i = 0; i < TARGET_WORD.length(); i++) {
            String imagePath = "./assets/images/" + Character.toUpperCase(TARGET_WORD.charAt(i)) + ".png";
            CharacterLabel label = new CharacterLabel(imagePath);
            add(label);
            characterLabels.add(label);
        }
        Collections.shuffle(characterLabels);

        Timer timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (CharacterLabel label : characterLabels) {
                    label.moveRandomly();
                    ensureLabelInsidePanel(label);
                }
                repaint(); // 이미지가 움직일 때마다 패널을 다시 그리도록 repaint 호출
            }
        });
        timer.start();
    }

    private void ensureLabelInsidePanel(CharacterLabel label) {
        // 라벨이 패널 밖으로 나가지 않도록 조정
        int x = label.getLocation().x;
        int y = label.getLocation().y;
        int maxX = getWidth() - label.getWidth();
        int maxY = getHeight() - label.getHeight();

        if (x < 0) {
            x = 0;
        } else if (x > maxX) {
            x = maxX;
        }

        if (y < 0) {
            y = 0;
        } else if (y > maxY) {
            y = maxY;
        }

        label.setLocation(x, y);
    }

    private class CharacterLabel extends JLabel {
        private static final int IMAGE_WIDTH = 60;
        private static final int IMAGE_HEIGHT = 60;
        private static final int MOVEMENT_RANGE = 15;

        public CharacterLabel(String imagePath) {
            ImageIcon icon = new ImageIcon(imagePath);
            icon = new ImageIcon(icon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH));
            setIcon(icon);
            setHorizontalAlignment(JLabel.CENTER);

            int x = (int) (Math.random() * 600);
            int y = (int) (Math.random() * 400);
            setBounds(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
        }

        public void moveRandomly() {
            int x = getLocation().x + (int) (Math.random() * MOVEMENT_RANGE * 2) - MOVEMENT_RANGE;
            int y = getLocation().y + (int) (Math.random() * MOVEMENT_RANGE * 2) - MOVEMENT_RANGE;

            setLocation(x, y);
        }
    }

    public static void main(String[] args) {
        // 테스트용으로 프레임 생성 및 패널 추가
        JFrame frame = new JFrame("애너그램");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        Anagram anagramPanel = new Anagram();
        frame.getContentPane().add(anagramPanel);
        frame.setVisible(true);
    }
}