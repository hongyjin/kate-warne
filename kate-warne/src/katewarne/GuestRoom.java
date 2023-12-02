package katewarne;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import katewarne.Main.LastPanel;
import katewarne.MainFrame.BackgroundPanel;

public class GuestRoom extends JFrame {
	private static GuestRoom instance;
	public LastPanel lastPanel;
	private JButton button1;  // 필드로 선언
    private JButton button2;  // 필드로 선언
    private JButton button3;  // 필드로 선언
	String message1 = "홍작가 무서운 사람이잖아..?\n"
			+ "소설을 위해서 이런 범행까지 저질르다니… \n"
			+ "근데 이걸 왜 최손님이 갖고 있는 걸까?";
	String message2 = "어?! 이건 홍작가에 대한 인터뷰 스크랩이잖아.\n"
			+ "홍작가에 대해 왜 이렇게 관심이 많은 거지?";
	String message3 = "전기기사자격증……\n"
			+ "감전사고로 의심되는 사건의 용의자가 전기기사자격증이라…\n"
			+ "확실히 의심스럽군.";
   public class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            
            backgroundImage = new ImageIcon("./assets/images/GuestRoomImage.jpg").getImage();

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
   
   public class LastPanel extends JPanel {
       private Image lastImage;
       private JButton button1;
       private JButton button2;
       private JButton button3;

       public LastPanel(JButton button1, JButton button2, JButton button3) {
    	   this.button1 = button1;
           this.button2 = button2;
           this.button3 = button3;
           lastImage = new ImageIcon("./assets/images/guestKey.png").getImage();
           addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
                   removeLastPanel(); 
               }
           });
       }
       @Override
       protected void paintComponent(Graphics g) {
           super.paintComponent(g);
           g.drawImage(lastImage, 0, 0, getWidth(), getHeight(), this);
           hideButton();
       }
       public void hideButton() {
           if (button1 != null) button1.setVisible(false);
           if (button2 != null) button2.setVisible(false);
           if (button3 != null) button3.setVisible(false);
           repaint();
       }
   }
	private void removeLastPanel() {
       getContentPane().remove(lastPanel);
       revalidate();
       repaint();
   }
      
   // 이미지 크기 조절 메서드
   private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
       Image img = icon.getImage();
       Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
       return new ImageIcon(resizedImg);
   }
   
   public static GuestRoom getInstance() {
       if (instance == null) {
           instance = new GuestRoom();
       }
       return instance;
   }
   public void setKeyStatus(boolean keyStatus) {
       if (keyStatus) {
           add(lastPanel);
           lastPanel.setBounds(0, 0, 800, 600);
       }
   }
   
    public GuestRoom() {
        setTitle("최손님 방");
        setSize(800, 600);
        setResizable(false); //크기못바꾸게
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null);
        
        //lastPanel = new LastPanel();
        lastPanel = new LastPanel(button1, button2, button3);

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
        //게임으로 이동 버튼
        JButton gameButton = new JButton("게임으로 이동");
        gameButton.setBackground(Color.BLACK); 
        gameButton.setForeground(Color.WHITE); 
        gameButton.setFocusPainted(false);
        gameButton.setBorder(border);
      

        // 범행기록문서 버튼
        ImageIcon button1Icon = new ImageIcon("./assets/images/범행 기록 문서.png");
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
            	showDescriptionDialog(button1Icon,message1, "범행기록문서");
            }
        });
        // 인터뷰스크랩 버튼
        ImageIcon button2Icon = new ImageIcon("./assets/images/interview.png");
   
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
            	showDescriptionDialog(button2Icon,message2, "인터뷰스크랩");
            	//'게임으로 이동' 버튼 추가
            	gameButton.setBounds(300,200,150,30);
            	gameButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setVisible(false);                   
                        Main.getInstance().setVisible(true);
                    }
                });
            	
            	gameButton.setBounds(330, 250, 150, 30);
                backgroundPanel.add(gameButton);
            }
        });
        
        // 전기기사 자격증 버튼
        ImageIcon button3Icon = new ImageIcon("./assets/images/전기기사자격증_그림.jpg");
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
            	showDescriptionDialog(button3Icon,message3, "전기기사자격증");
            }
        });
        
        // 뒤로가기 버튼
        goBackButton.setBounds(50,40,90,30);
        backgroundPanel.add(goBackButton);
        

        // 범행기록 버튼
        button1.setBounds(140,440,50,50);
        backgroundPanel.add(button1);
    
        
        // 인터뷰스크랩 버튼
        button2.setBounds(200,440,50,50);
        backgroundPanel.add(button2);
       
        
        // 자격증 버튼
        button3.setBounds(585,395,50,50);
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
            new GuestRoom().setVisible(true);
        });
    }
}