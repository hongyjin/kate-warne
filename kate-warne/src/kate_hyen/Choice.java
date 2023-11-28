package kate_hyen;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicRadioButtonUI;

import guest_game.Main;
import guest_game.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Choice extends JFrame {
	
	 private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
	       Image img = icon.getImage();
	       Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	       return new ImageIcon(resizedImg);
	   }
	 private void moveToAnotherPage() {
         setVisible(false);
         MainFrame.getInstance().setVisible(true);
     }
	 
    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            //backgroundImage = new ImageIcon("C:\\Users\\user\\Downloads\\자바 일러스트 모음\\darkBackground.png").getImage();
            backgroundImage = new ImageIcon("../../../assets/images/darkBackground.png").getImage();
            JLabel textLabel = new JLabel("범인은 누구일까요?");
            Font labelFont = textLabel.getFont();
            textLabel.setFont(new Font(labelFont.getName(), Font.BOLD, 30)); // h2 크기로 설정
            textLabel.setForeground(Color.WHITE); // 흰색 글씨로 설정
            textLabel.setBounds(270, 80, 300, 30);
            add(textLabel);

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    private class ChoicePanel extends JPanel {
        private JButton button1;
        private JButton button2;
        String success = "정답입니다!\n이제 사건의 전말을 공개합니다";
        String failure = "틀렸습니다!\n이제 사건의 전말을 공개합니다";

        public ChoicePanel() {
            setSize(600, 300);
            setOpaque(true);
            setBackground(new Color(255, 255, 255, 80));
            setBorder(BorderFactory.createLineBorder(Color.YELLOW, 6));
            setLayout(null);

            //ImageIcon imageIcon = new ImageIcon("C:\\Users\\user\\Downloads\\자바 일러스트 모음\\최손님.png");
            ImageIcon imageIcon = new ImageIcon("../../../assets/images/최손님.png");
            
            JLabel imageLabel = new JLabel(resizeIcon(imageIcon, 110, 135));
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            imageLabel.setBounds(110, 45, 110, 135);
            add(imageLabel);

            //ImageIcon imageIcon2 = new ImageIcon("C:\\Users\\user\\Downloads\\자바 일러스트 모음\\김변호.png");
            ImageIcon imageIcon2 = new ImageIcon("../../../assets/images/김변호.png");
            JLabel imageLabel2 = new JLabel(resizeIcon(imageIcon2, 100, 130));
            imageLabel2.setHorizontalAlignment(JLabel.CENTER);
            imageLabel2.setBounds(380, 45, 100, 130);
            add(imageLabel2);


            // button1 필드로 변경
            button1 = new JButton("최손님");
            button1.setBounds(120, 210, 100, 50);
            button1.setBackground(Color.MAGENTA);
            button1.setForeground(Color.WHITE);
            button1.setFocusPainted(false);
            button1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
            setButtonFont(button1, 22, Font.BOLD);
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    button1.setBackground(Color.YELLOW);
                    button1.setForeground(Color.BLACK);
                    button2.setBackground(Color.LIGHT_GRAY);
                    button2.setForeground(Color.WHITE);
                    showResultDialog("오답", failure);
                }
            });
            add(button1);

            // button2 필드로 변경
            button2 = new JButton("김변호");
            button2.setBounds(380, 210, 100, 50);
            button2.setBackground(Color.LIGHT_GRAY);
            button2.setForeground(Color.WHITE);
            button2.setFocusPainted(false);
            button2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
            setButtonFont(button2, 22, Font.BOLD);
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    button2.setBackground(Color.YELLOW);
                    button2.setForeground(Color.BLACK);
                    button1.setBackground(Color.MAGENTA);
                    button1.setForeground(Color.WHITE);
                    showResultDialog("정답", success);
                }
            });
            add(button2);
        }

        private void setButtonFont(JButton button, int fontSize, int style) {
        	Font font = button.getFont();
            button.setFont(new Font(font.getName(), style, fontSize));
			
		}
        // 범인 선택후 뜨는 다이얼로그
        private void showResultDialog(String title, String message) {
            int option = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                moveToAnotherPage();
            }
        }

		// button1과 button2의 상태를 가져오기 위한 메서드 추가
        public JButton getButton1() {
            return button1;
        }

        public JButton getButton2() {
            return button2;
        }
    }
    
    public Choice() {
        setTitle("범인 선택하기");
        setSize(800, 600);
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null);
        ChoicePanel choicePanel = new ChoicePanel();
        choicePanel.setLayout(null);
        choicePanel.setBounds(100,140,600,300);
        backgroundPanel.add(choicePanel);
       
        
        //일단 종료하면 종료되도록
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 버튼
        JButton goBackButton = new JButton("뒤로가기");
        goBackButton.setBackground(Color.BLACK);
        goBackButton.setForeground(Color.WHITE);
        goBackButton.setFocusPainted(false);
        Border border = BorderFactory.createLineBorder(Color.WHITE, 2);
        goBackButton.setBorder(border);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MainFrame.getInstance().setVisible(true);
            }
        });

        // 뒤로가기 버튼
        goBackButton.setBounds(50, 40, 90, 30);
        backgroundPanel.add(goBackButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Choice().setVisible(true);
        });
    }
}
