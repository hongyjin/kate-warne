package kate_hyen;
import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;




public class LibraryRoom extends JFrame {
	String message1 = "홍작가의 방에 웬 rc카가..? \n"
			+ "홍작가한테 rc카를 좋아한다는 얘긴 못 들었는데..\n"
			+ "그러고보니, 여기에 무슨 소금결정 같은게 남아있잖아?! \n"
			+ "좀 젖어있는 것 같기도 해! 뭔가 중요한 단서일 것 같군";
	String message2 = "이름은 최손님인데, 최손님의 사진이 아니잖아?!\n"
			+ " 그럼 설마 교도관 신분으로 와있다는 최손님이 설마 교도관이 아닌걸까?\n"
			+ "이걸 홍작가는 알고 있던거야?!\n"
			+ "확실히 수상하군";
	String message3 = "재산의 50%를 세민재단에 넘긴다라...\n"
	        + " 재산과 관련된 문제가 얽혀있나보군.\n"
	        + "세민재단… 잘 기억해둬야겠어.";

    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            //backgroundImage = new ImageIcon("C:\\Users\\user\\git\\kate-warne\\kate-warne\\assets\\images\\LibraryRoomImage.jpg").getImage();
            backgroundImage = new ImageIcon("../../../assets/images/LibraryRoomImage.jpg").getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

   // 이미지 크기 조절 메서드
    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    public LibraryRoom() {
        setTitle("홍작가 방");
        setSize(800, 600);
        setResizable(false); //크기못바꾸게
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        
        backgroundPanel.setLayout(null);
    

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
        

        // 유언장 버튼
        //ImageIcon button1Icon = new ImageIcon("C:\\Users\\user\\Downloads\\자바 일러스트 모음\\유언장.png");
        ImageIcon button1Icon = new ImageIcon("../../../assets/images/유언장.png");
        JLabel button1 = new JLabel(resizeIcon(button1Icon, 30, 30));
        button1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                // 마우스가 버튼에 들어갈 때 호버 
                button1.setIcon(resizeIcon(button1Icon, 45, 45));
                button1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            }

            public void mouseExited(MouseEvent e) {
                // 마우스가 버튼에서 나갈 때
                button1.setIcon(resizeIcon(button1Icon, 30, 30));
                button1.setBorder(null);
            }

            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭 시 동작
            	showDescriptionDialog(button1Icon,message3, "유언장");
            }
        });
        // 교도관 이력서 버튼
        //ImageIcon button2Icon = new ImageIcon("C:\\Users\\user\\Downloads\\자바 일러스트 모음\\교도관 이력서.jpg");
        ImageIcon button2Icon = new ImageIcon("../../../assets/images/교도관 이력서.jpg");
        JLabel button2 = new JLabel(resizeIcon(button2Icon, 30, 30));
        button2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                // 마우스가 버튼에 들어갈 때 호버 
                button2.setIcon(resizeIcon(button2Icon, 45, 45));
                button2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            }

            public void mouseExited(MouseEvent e) {
                // 마우스가 버튼에서 나갈 때
                button2.setIcon(resizeIcon(button2Icon, 30, 30));
                button2.setBorder(null);
            }

            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭 시 동작
            	showDescriptionDialog(button2Icon,message2, "교도관 이력서");
            }
        });
        
        // rc카 버튼
        //ImageIcon button3Icon = new ImageIcon("C:\\Users\\user\\Downloads\\자바 일러스트 모음\\rc카.png");
        ImageIcon button3Icon = new ImageIcon("../../../assets/images/rc카.png");
        JLabel button3 = new JLabel(resizeIcon(button3Icon, 30, 30));
        button3.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                // 마우스가 버튼에 들어갈 때 호버 
                button3.setIcon(resizeIcon(button3Icon, 45, 45));
                button3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            }

            public void mouseExited(MouseEvent e) {
                // 마우스가 버튼에서 나갈 때
                button3.setIcon(resizeIcon(button3Icon, 30, 30));
                button3.setBorder(null);
            }

            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭 시 동작
            	showDescriptionDialog(button3Icon,message1, "rc카");
            }
        });
        

        // 뒤로가기 버튼
        goBackButton.setBounds(50,40,90,30);
        backgroundPanel.add(goBackButton);
        

        // 유언장 버튼
        button1.setBounds(250,450,50,50);
        backgroundPanel.add(button1);
    
        
        // 교도관 이력서 버튼
        button2.setBounds(570,390,50,50);
        backgroundPanel.add(button2);
       
        
        // rc카 버튼
        button3.setBounds(585,480,50,50);
        backgroundPanel.add(button3);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    // 설명을 보여줄 다이얼로그 메서드
    private void showDescriptionDialog(ImageIcon image,String message, String title) {
        JDialog dialog = new JDialog(this, title, true);
        dialog.setSize(600, 300);
        dialog.setUndecorated(true);
        dialog.setLocationRelativeTo(this);
        JPanel panel = new JPanel(new GridLayout(1, 2));
        // 1열(이미지)
        Image scaledImage = image.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER); 
        imageLabel.setBackground(Color.BLACK); 
        imageLabel.setOpaque(true); 
        panel.add(imageLabel);

        // 2열(텍스트)
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBackground(Color.BLACK); 
        JLabel label = new JLabel("<html><div style='color: white;'>" + message + "</div></html>");
        label.setHorizontalAlignment(JLabel.CENTER); 
        textPanel.add(label, BorderLayout.CENTER);
        panel.add(textPanel);
           
        dialog.add(panel);
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));

        // 창을 클릭하면 창이 닫히도록 설정
        dialog.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dialog.dispose();
            }
        });
        dialog.setOpacity(0.85f);
        dialog.setVisible(true);
    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LibraryRoom().setVisible(true);
        });
    }
}