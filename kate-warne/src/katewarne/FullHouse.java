package katewarne;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FullHouse {
    public static void main(String[] args) {
        new MainFrame(); 
    }
}

class MainFrame extends JFrame {
	private static MainFrame instance;
	
    public class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            //backgroundImage = new ImageIcon("C:\\Users\\user\\git\\kate-warne\\kate-warne\\assets\\images\\mainRoomImage.jpg").getImage();
            backgroundImage = new ImageIcon("./assets/images/mainRoomImage.jpg").getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public MainFrame() {
        setTitle("전체 방 구조");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        
        backgroundPanel.setLayout(null);
        
        // 최손님 버튼
        JButton goToGuestRoomButton = new JButton("최손님 방");
        goToGuestRoomButton.setBackground(Color.BLACK); 
        goToGuestRoomButton.setForeground(Color.WHITE); 
        goToGuestRoomButton.setFocusPainted(false);
        Border border = BorderFactory.createLineBorder(Color.WHITE, 2);
        goToGuestRoomButton.setBorder(border);
        goToGuestRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 버튼 클릭 시 GuestRoom으로 이동
            	setVisible(false); // 현재 창 감추기
                new GuestRoom().setVisible(true); // GuestRoom 창 열기
            }
        });
        // 홍작가 버튼
        JButton goToLibraryRoomButton = new JButton("홍작가 방");
        goToLibraryRoomButton.setBackground(Color.BLACK); 
        goToLibraryRoomButton.setForeground(Color.WHITE); 
        goToLibraryRoomButton.setFocusPainted(false);
        goToLibraryRoomButton.setBorder(border);
        goToLibraryRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 버튼 클릭 시 GuestRoom으로 이동
            	setVisible(false); // 현재 창 감추기
                new LibraryRoom().setVisible(true); 
            }
        });
        // 김변호 버튼
        JButton goToLaywerRoomButton = new JButton("김변호 방");
        goToLaywerRoomButton.setBackground(Color.BLACK); 
        goToLaywerRoomButton.setForeground(Color.WHITE); 
        goToLaywerRoomButton.setFocusPainted(false);
        goToLaywerRoomButton.setBorder(border);
        goToLaywerRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 버튼 클릭 시 GuestRoom으로 이동
            	setVisible(false); // 현재 창 감추기
                new LaywerRoom().setVisible(true); 
            }
        });
        
        //범인선택 버튼
        JButton goToChoice = new JButton("최종선택");
        goToChoice.setBackground(Color.PINK); 
        goToChoice.setForeground(Color.BLACK); 
        goToChoice.setFocusPainted(false);
        Border border2 = BorderFactory.createLineBorder(Color.WHITE, 3);
        goToChoice.setBorder(border2);
        goToChoice.setBounds(650,520,100,30);
        backgroundPanel.add(goToChoice);
        goToChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false); // 현재 창 감추기
                new Choice().setVisible(true); 
            }
        });

        // 홍작가 버튼 위치 및 정렬 설정
        goToLibraryRoomButton.setBounds(220,420,75,30);
        backgroundPanel.add(goToLibraryRoomButton);

        // 최손님 버튼 위치 및 정렬 설정
        goToGuestRoomButton.setBounds(360,170,75,30);
        backgroundPanel.add(goToGuestRoomButton);
      
        
        // 김변호 버튼 위치 및 정렬 설정
        goToLaywerRoomButton.setBounds(495,420,75,30);
        backgroundPanel.add(goToLaywerRoomButton);
       
        setLocationRelativeTo(null);
        setVisible(true); 
    }
    
    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }
}
