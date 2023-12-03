package katewarne;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
	
	private static Game instance;
	public static Game getInstance() {
	       if (instance == null) {
	           instance = new Game();
	       }
	       return instance;
	   }
	public static boolean getKey = false;
	private File path = new File(""); // Set your image path here
	private int[] cardRandom = new int[20]; // 카드 랜덤 20개
	private int selectedTwoCardCheck;
	private int firstCardNumber; // 첫 번째 카드 넘버
	private int secondCardNumber; // 두 번째 카드 넘버
	private int selectedImageNumber;
	private ImageIcon selectedImage;
	private JLabel labelConfirmedCheck;
	private Image backgroundImage;

	private JPanel panel1;
	private JButton buttonStart;
	private JPanel panel2;
	private ImageIcon imageBack;
	private ImageIcon[] imageIcon = new ImageIcon[20];
	private JLabel[] labelImage = new JLabel[20];
	private JLabel labelSelectedFirst; // 첫 번째 클릭
	private JLabel labelSelectedSecond; // 두 번째 클릭

	private Timer timerMix = new Timer(); // 카드 섞는 시간
	private Timer timerHide = new Timer(); // 카드 뒤집을 제한 시간
	private Timer timerCardCheck;

	public Game() {
		setTitle("카드 짝 맞추기 게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLocationRelativeTo(null);
		String imagePath = "./assets/images/game/darkBackground.png";
		backgroundImage = new ImageIcon(imagePath).getImage();

		panel1 = new JPanel1();
		panel2 = new JPanel2();

		add(panel1, BorderLayout.NORTH); // 위쪽
		add(panel2, BorderLayout.CENTER); // 아래

		setSize(800, 600);
		setVisible(true);
		// 뒤로가기 버튼
        JButton goBackButton = new JButton("게임 종료하기");
        goBackButton.setBackground(Color.BLACK);
        goBackButton.setForeground(Color.WHITE);
        goBackButton.setFocusPainted(false);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(goBackButton);
        add(buttonPanel, BorderLayout.PAGE_END);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LibraryRoom.getInstance().setVisible(true);

            }
        }); 
	}

	// panel1에는 게임 시작 버튼 만들기
	class JPanel1 extends JPanel {
		private static final long serialVersionUID = 1L; // 경고 떠서 추가

		public JPanel1() {
			setLayout(new GridLayout(1, 1));

			buttonStart = new JButton("게임 시작");
			add(buttonStart);

			buttonStart.addActionListener(new MyActionListenerNewGame());
		}

		// 배경
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}

	// panel2에는 카드 뒤집기 게임 만들기
	class JPanel2 extends JPanel {
		private static final long serialVersionUID = 1L; // 경고 떠서 추가

		public JPanel2() {
			setLayout(new GridLayout(4, 5, 10, 30)); // 4*5 배열

			mixNumber();
			setImage(); // 이미지 세팅
			setButtonFirstImage();
			setButtonName();

			for (int i = 0; i < 20; i++) {
				labelImage[i] = new JLabel(imageIcon[i]); // 20개의 라벨 생성
				labelImage[i].addMouseListener(new JjacMouseListener()); // 반응리스너
				add(labelImage[i]); // 라벨에 넣기
			}
		}

		// 배경
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}

	// 반응리스너 (게임 시작 버튼 -> 카드 섞기 실행)
	class MyActionListenerNewGame implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setButtonName();
			selectedTwoCardCheck = 0;

			// 카드 섞는 타이머 생성
			// 카드 섞는 타이머 생성
			timerMix.scheduleAtFixedRate(new TimerTask() {
				int i = 0;

				public void run() {
					mixNumber(); // 카드 섞기
					setImage(); // 이미지 설정
					setButtonResetImage(); // 버튼 이미지 초기화
					i = i + 1;
					if (i == 20) { // 20번 동안 반복
						timerMix.cancel();

						// 타이머 취소 후 카드 가리는 타이머 생성
						timerHide.scheduleAtFixedRate(new TimerTask() {
							public void run() {
								SwingUtilities.invokeLater(() -> hideButtonImage());
								timerHide.cancel(); // 타이머 취소
							}
						}, 2000, 1); // 2초 뒤에 카드 가리기
					}
				}
			}, 0, 50);

		}
	}

	// 카드 맞추기
	// 반응 리스너 (카드 선택 -> 매칭 확인 -> 매칭 카드 수 확인 -> 종료)
	private class JjacMouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			labelConfirmedCheck = ((JLabel) e.getSource()); // 마우스 클릭 확인

			if (labelConfirmedCheck.getName().equals("checked")) { // 카드가 이미 매칭이 된 카드라면
				selectedTwoCardCheck = 0; // 카운트 하지 않음
			} else if (selectedTwoCardCheck == 0 || selectedTwoCardCheck == 1) { // 이미 맞춘 카드거나 하나 선택
				selectedTwoCardCheck += 1; // 선택한 카드 수+1

				// 하나 선택한 경우
				if (selectedTwoCardCheck == 1) {
					labelSelectedFirst = ((JLabel) e.getSource()); // 라벨 저장
					selectedImageNumber = Integer.parseInt(labelSelectedFirst.getName()) - 1; // 인덱스 배열이라 -1 (카드 고유 넘버)

					selectedImage = new ImageIcon(
							path + "./assets/images/game/" + cardRandom[selectedImageNumber] + ".png");
					labelSelectedFirst.setIcon(selectedImage); // 첫 번째 선택 이미지 설정

					firstCardNumber = cardRandom[selectedImageNumber];
					if (cardRandom[selectedImageNumber] > 10)
						firstCardNumber = cardRandom[selectedImageNumber] - 10; // 실제 번호로

					// 이미지 크기 고정
					Image img = selectedImage.getImage();
					Image newImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
					selectedImage = new ImageIcon(newImg);
					labelSelectedFirst.setIcon(selectedImage);
				}
				// 첫 번째 카드랑 똑같은 거 고른 경우
				if (labelConfirmedCheck.getName().equals(labelSelectedFirst.getName())) {
					selectedTwoCardCheck = 1; // 그냥 그대로
				}
				// 서로 다른 두 카드를 선택한 경우
				else if (selectedTwoCardCheck == 2) {
					// 첫 번째 과정이랑 동일
					labelSelectedSecond = ((JLabel) e.getSource());
					selectedImageNumber = Integer.parseInt(labelSelectedSecond.getName()) - 1;
					selectedImage = new ImageIcon(
							path + "./assets/images/game/" + cardRandom[selectedImageNumber] + ".png");
					labelSelectedSecond.setIcon(selectedImage);

					secondCardNumber = cardRandom[selectedImageNumber];
					if (cardRandom[selectedImageNumber] > 10)
						secondCardNumber = cardRandom[selectedImageNumber] - 10;

					// 이미지 크기 조정
					Image img = selectedImage.getImage();
					Image newImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
					selectedImage = new ImageIcon(newImg);
					labelSelectedSecond.setIcon(selectedImage);

					// 두 카드 번호가 동일하다면
					if (firstCardNumber == secondCardNumber) {
						selectedTwoCardCheck = 0;
						labelSelectedFirst.setName("checked"); // 매칭된 카드라고 표시
						labelSelectedSecond.setName("checked"); // 매칭된 카드라고 표시

						// 이미지 크기 조정
						labelSelectedSecond.setIcon(selectedImage);

						int end = 0;
						for (int i = 0; i < 20; i++) {
							// 체크된 카드 수 세기
							if ((labelImage[i].getName()).equals("checked")) {
								end = end + 1;
								// 20개가 되면 게임 종료
								if (end == 20) {
									getKey =true;
									LibraryRoom libraryRoomInstance = LibraryRoom.getInstance();
					                if (libraryRoomInstance != null) {
					                    libraryRoomInstance.setKeyStatus(getKey);
					                }
									dialogResult(); // 게임 완료 출력 창
									break;
								}
							}
						}

					}
					// 선택한 두 카드가 동일하지 않다면
					else {
						timerCardCheck = new Timer();
						timerCardCheck.scheduleAtFixedRate(new TimerTask() {
							public void run() {
								selectedTwoCardCheck = 0; // 카드 선택 수 최기화
								labelSelectedFirst.setIcon(imageBack); // 다시 카드 뒤집기
								labelSelectedSecond.setIcon(imageBack); // 다시 카드 뒤집기
								timerCardCheck.cancel();
							}
						}, 300, 1); // 3초 정도 소요
					}
				}
			}
		}
	}

	// 숫자 랜덤으로 섞기
	void mixNumber() {
		int i = 0;
		int rand;
		while (true) {
			rand = (int) (Math.random() * 20 + 1);
			cardRandom[i] = rand;

			aa: for (int j = 0; j < 20; j++) {
				if (j == i)
					break aa;
				if (cardRandom[j] == rand) {
					i = i - 1;
				}
			}
			i = i + 1;
			if (i == -1)
				i = 0;
			if (i == 20)
				break;
		}
	}

	JLabel imgLabel = new JLabel();

	// 이미지 띄우기
	void setImage() {
		imageBack = new ImageIcon(path + "./assets/images/game/0.png");

		for (int i = 0; i < 20; i++) {
			imageIcon[i] = new ImageIcon(path + "./assets/images/game/" + cardRandom[i] + ".png");

			Image img = imageIcon[i].getImage();
			Image newImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			imageIcon[i] = new ImageIcon(newImg);
		}
	}

	// 첫 번째 버튼
	void setButtonFirstImage() {
		for (int i = 0; i < 20; i++) {
			labelImage[i] = new JLabel(imageIcon[i]);

			// 이미지 크기를 조절하여 새로운 이미지를 생성
			Image img = imageIcon[i].getImage();
			Image newImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			ImageIcon scaledIcon = new ImageIcon(newImg);

			// 새로 조절된 이미지로 JLabel을 갱신
			labelImage[i].setIcon(scaledIcon);
		}
	}

	// 두 번째 버튼
	void setButtonResetImage() {
		for (int i = 0; i < 20; i++) {
			labelImage[i].setIcon(imageIcon[i]);
		}
	}

	void setButtonName() {
		for (int i = 0; i < 20; i++) {
			labelImage[i].setName(Integer.toString(i + 1));
		}
	}

	// 숨기기
	void hideButtonImage() {
		for (int i = 0; i < 20; i++) {
			labelImage[i].setIcon(imageBack);
		}
	}

	// 게임 완료 창 띄우기
	void dialogResult() {
		int option = JOptionPane.showOptionDialog(this,
                "게임완료", "게임 종료", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, null, null);
		if (option == JOptionPane.OK_OPTION) {
        	setVisible(false);
	        LibraryRoom.getInstance().setVisible(true);
	    }
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            Game.getInstance().setVisible(true);
        });
	}
}