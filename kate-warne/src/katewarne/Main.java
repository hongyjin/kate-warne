package katewarne;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Main extends JFrame {
    private static Main instance;
   
    // 더블 버퍼링을 활용하기 위한 변수 선언
 	private Image screenImage;
 	private Graphics screenGraphic;
 	//사용할 이미지
 	private Image brick = new ImageIcon("./assets/images/brick2.png").getImage();
 	private Image brick5 = new ImageIcon("./assets/images/brick52.png").getImage();
 	private Image brick8 = new ImageIcon("./assets/images/brick82.jpg").getImage();
 	private Image ladder = new ImageIcon("./assets/images/ladder.png").getImage();
 	private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            //backgroundImage = new ImageIcon("C:\\Users\\user\\Downloads\\자바 일러스트 모음\\게임배경2.jpg").getImage();
        	backgroundImage = new ImageIcon("./assets/images/게임배경2.jpg").getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            // 투명도 조절
            float alpha = 0.8f; //(0.0f: 완전 투명, 1.0f: 불투명)
            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
            g2d.setComposite(alphaComposite);
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            // 다시 원래의 투명도로 
            alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
            g2d.setComposite(alphaComposite);
            //벽돌 그리기
            g.drawImage(brick5, 0, 470, null);
    		g.drawImage(brick5, 470, 470, null);
    		g.drawImage(brick5, 600, 290, null);
    		g.drawImage(brick5, 0, 150, null);
    		g.drawImage(brick5, 400, 150, null);
    		g.drawImage(ladder, 200, 165, null);
    
    		//아이템,용의자 그리기
    		stage.drawItems(g);
    		stage.drawSuspect(g);
    		
    		//반복해서 탐정 그리기
    		g.drawImage(detec.getDetective(),detec.getPos_X(),detec.getPos_Y(),120,80,null);
    		this.repaint();
        }
    }
 	
 	 private class StartPanel extends JPanel {
         private Image startImage;

         public StartPanel() {
             //startImage = new ImageIcon("C:\\Users\\user\\Downloads\\자바 일러스트 모음\\용의자를피해라.png").getImage();
        	 startImage = new ImageIcon("./assets/images/용의자를피해라.png").getImage();
             addMouseListener(new MouseAdapter() {
                 @Override
                 public void mouseClicked(MouseEvent e) {
                     removeStartPanel(); // 이미지 클릭 시 패널 제거
                 }
             });
         }

         @Override
         protected void paintComponent(Graphics g) {
             super.paintComponent(g);
             g.drawImage(startImage, 0, 0, getWidth(), getHeight(), this);
         }
     }
 	 
 	 private BackgroundPanel backgroundPanel;
     private StartPanel startPanel;
 	//스테이지 생성
 	public static Stage stage = new Stage();
 	
 	//탐정 캐릭터 생성
 	public static Detective detec = new Detective();
 	// 용의자 생성
 	public static Suspect s1 = new Guest(10,110,0,270);
 	public static Suspect s2 = new Guest(470,430, 470, 720);
 	public static Suspect s3 = new Lawyer(400,110,420,660);
 	
 	
    public static Main getInstance() {
        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }
    public static void switchToGuestRoom() {
    	getInstance().setVisible(false);
        GuestRoom.getInstance().setVisible(true);
    }
    private void removeStartPanel() {
        getContentPane().remove(startPanel);
        revalidate();
        repaint();
    }
    
    
   
    //생성자
    public Main() {
        setTitle("벽돌과 사다리 게임");
        setSize(800, 600);
        setResizable(false); //크기못바꾸게
        //일단 종료하면 종료되도록
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        backgroundPanel.setLayout(null);
        
        // 맨 앞에 StartPanel 띄우기
        startPanel = new StartPanel();
        add(startPanel);
        startPanel.setBounds(0, 0, 800, 600);
        
        // 게임종료버튼
        JButton goBackButton = new JButton("게임 종료하기");
        goBackButton.setBackground(Color.BLACK);
        goBackButton.setForeground(Color.WHITE);
        goBackButton.setFocusPainted(false);
        Border border = BorderFactory.createLineBorder(Color.WHITE, 2);
        goBackButton.setBorder(border);
        goBackButton.setBounds(50, 40, 90, 30);
        backgroundPanel.add(goBackButton);
        
        //게임종료버튼 클릭
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setVisible(false);
                //GuestRoom.getInstance().setVisible(true);
                switchToGuestRoom();
            }
        }); 
        
        
        //탐정 초기 설정
        detec.setPosition(30, 400);
        
        //키 리스너 생성
        addKeyListener(new KeyListener());
        setFocusable(true);
        
        //각 객체의 스레드 실행
        stage.startStage();
        s1.start();
        s2.start();
        s3.start();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main.getInstance().setVisible(true);
        });
    }
}