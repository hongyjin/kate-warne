package katewarne;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class gamestart extends JFrame {
	private Image backgroundImage;
	private Image buttonImage;

	private int buttonWidth = 500;
	private int buttonHeight = 200;

	private boolean isMouseOverButton = false;

	public gamestart() {
		// JFrame 설정
		setTitle("케이트 와르네: 대저택 살인사건");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 배경 이미지 로드
		String imagePath = "./assets/images/KakaoTalk_20231112_002414941_01.png";
		backgroundImage = new ImageIcon(imagePath).getImage();

		// 이미지 버튼의 배경 이미지 로드
		String buttonImagePath = "./assets/images/KakaoTalk_20231112_002414941.png";
		buttonImage = new ImageIcon(buttonImagePath).getImage();

		// JFrame에 컨텐츠 팬 추가
		setContentPane(new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				drawBackground(g, this);
				drawButton(g, this);
			}
		});

		// 이미지 버튼에 마우스 이벤트 처리
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int mouseX = e.getX();
				int mouseY = e.getY();

				int buttonX = 150;
				int buttonY = 390;

				// 클릭한 좌표가 버튼 영역 안에 있는지 확인
				if (mouseX >= buttonX && mouseX <= buttonX + buttonWidth && mouseY >= buttonY
						&& mouseY <= buttonY + buttonHeight) {
					// 버튼을 클릭한 경우
					// Intro1 클래스로 이동
					new intro1();
					dispose(); // 현재 화면 닫기
				}
			}
		});

		// 마우스가 버튼 위에 올라갔을 때의 이벤트 처리
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int mouseX = e.getX();
				int mouseY = e.getY();

				int buttonX = 150;
				int buttonY = 390;

				// 마우스가 버튼 영역 안에 있는지 확인
				isMouseOverButton = (mouseX >= buttonX && mouseX <= buttonX + buttonWidth && mouseY >= buttonY
						&& mouseY <= buttonY + buttonHeight);

				repaint(); // 화면을 다시 그려서 변경된 상태를 반영
			}
		});

		// JFrame 표시 설정
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void drawBackground(Graphics g, ImageObserver observer) {
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), observer);
	}

	private void drawButton(Graphics g, ImageObserver observer) {
		// 이미지 버튼 그리기
		int buttonX = 150;
		int buttonY = 390;

		if (isMouseOverButton) {
			// 마우스가 버튼 위에 있으면 크기를 키움
			g.drawImage(buttonImage, buttonX, buttonY, buttonWidth + 20, buttonHeight + 20, observer);
		} else {
			g.drawImage(buttonImage, buttonX, buttonY, buttonWidth, buttonHeight, observer);
		}
	}

	public static void main(String[] args) {
		new gamestart();
	}
}