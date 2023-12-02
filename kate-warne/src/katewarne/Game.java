package katewarne;

import java.awt.BorderLayout;
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
	public static boolean getKey = false;
	public static Game getInstance() {
	       if (instance == null) {
	           instance = new Game();
	       }
	       return instance;
	   }
	private File path = new File(""); // Set your image path here
	private int[] cardRandom = new int[20];
	private int selectedTwoCardCheck;
	private int firstCardNumber;
	private int secondCardNumber;
	private int selectedImageNumber;
	private ImageIcon selectedImage;
	private JLabel labelConfirmedCheck; // Added this line
	private Image backgroundImage;

	private JPanel panel1;
	private JButton buttonStart;

	private JPanel panel2;
	private ImageIcon imageBack;
	private ImageIcon[] imageIcon = new ImageIcon[20];
	private JLabel[] labelImage = new JLabel[20];
	private JLabel labelSelectedFirst;
	private JLabel labelSelectedSecond;

	private Timer timerMix = new Timer(); // Added this line
	private Timer timerHide = new Timer(); // Added this line
	private Timer timerCardCheck; // Added this line
	private Key key;  // Key 클래스 인스턴스 추가
	public Game() {
		setTitle("케이트 와르네: 대저택 살인사건");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//원래 이 코드쓰면 화면이 가운데로 와야하는데... 이건 안되네요...?
		//setLocationRelativeTo(null); 
		

		String imagePath = "./assets/images/game/darkBackground.png";
		backgroundImage = new ImageIcon(imagePath).getImage();

		panel1 = new JPanel1();
		panel2 = new JPanel2();

		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.CENTER);

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

	class JPanel1 extends JPanel {
		public JPanel1() {
			setLayout(new GridLayout(1, 1));

			buttonStart = new JButton("게임 시작");

			add(buttonStart);

			buttonStart.addActionListener(new MyActionListenerNewGame());
		}
	}

	class JPanel2 extends JPanel {
		public JPanel2() {
			setLayout(new GridLayout(4, 5, 10, 30));

			mixNumber();
			setImage();
			setButtonFirstImage();
			setButtonName();

			for (int i = 0; i < 20; i++) {
				labelImage[i] = new JLabel(imageIcon[i]);
				labelImage[i].addMouseListener(new JjacMouseListener());
				add(labelImage[i]);
			}
		}
	}

	class MyActionListenerNewGame implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setButtonName();
			selectedTwoCardCheck = 0;

			timerMix.scheduleAtFixedRate(new TimerTask() {
				int i = 0;

				public void run() {
					mixNumber();
					setImage();
					setButtonResetImage();
					i = i + 1;
					if (i == 20)
						timerMix.cancel();
				}
			}, 0, 50);

			timerHide.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					hideButtonImage();
					timerHide.cancel();
				}
			}, 2000, 1);
		}
	}

	private class JjacMouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			labelConfirmedCheck = ((JLabel) e.getSource());
			if (labelConfirmedCheck.getName().equals("checked")) {
				selectedTwoCardCheck = 0;
			} else if (selectedTwoCardCheck == 0 || selectedTwoCardCheck == 1) {
				selectedTwoCardCheck += 1;

				if (selectedTwoCardCheck == 1) {
					labelSelectedFirst = ((JLabel) e.getSource());
					selectedImageNumber = Integer.parseInt(labelSelectedFirst.getName()) - 1;
					selectedImage = new ImageIcon(
							path + "./assets/images/game/" + cardRandom[selectedImageNumber] + ".png");
					labelSelectedFirst.setIcon(selectedImage);

					firstCardNumber = cardRandom[selectedImageNumber];
					if (cardRandom[selectedImageNumber] > 10)
						firstCardNumber = cardRandom[selectedImageNumber] - 10;
				}

				if (labelConfirmedCheck.getName().equals(labelSelectedFirst.getName())) {
					selectedTwoCardCheck = 1;
				} else if (selectedTwoCardCheck == 2) {
					labelSelectedSecond = ((JLabel) e.getSource());
					selectedImageNumber = Integer.parseInt(labelSelectedSecond.getName()) - 1;
					selectedImage = new ImageIcon(
							path + "./assets/images/game/" + cardRandom[selectedImageNumber] + ".png");
					labelSelectedSecond.setIcon(selectedImage);

					secondCardNumber = cardRandom[selectedImageNumber];
					if (cardRandom[selectedImageNumber] > 10)
						secondCardNumber = cardRandom[selectedImageNumber] - 10;

					if (firstCardNumber == secondCardNumber) {
						selectedTwoCardCheck = 0;
						labelSelectedFirst.setName("checked");
						labelSelectedSecond.setName("checked");

						int end = 0;
						for (int i = 0; i < 20; i++) {
							if ((labelImage[i].getName()).equals("checked")) {
								end = end + 1;
								if (end == 20) {
									dialogResult();
									getKey =true;
									LibraryRoom libraryRoomInstance = LibraryRoom.getInstance();
						            if (libraryRoomInstance != null) {
						                libraryRoomInstance.setKeyStatus(getKey);
						            }
									break;
								}
							}
						}

					} else {
						timerCardCheck = new Timer();
						timerCardCheck.scheduleAtFixedRate(new TimerTask() {
							public void run() {
								selectedTwoCardCheck = 0;
								labelSelectedFirst.setIcon(imageBack);
								labelSelectedSecond.setIcon(imageBack);
								timerCardCheck.cancel();
							}
						}, 300, 1);
					}
				}
			}
		}
	}

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

	void setImage() {
		imageBack = new ImageIcon(path + "./assets/images/game/0.png");
		for (int i = 0; i < 20; i++) {
			imageIcon[i] = new ImageIcon(path + "./assets/images/game/" + cardRandom[i] + ".png");
		}
	}

	void setButtonFirstImage() {
		for (int i = 0; i < 20; i++) {
			labelImage[i] = new JLabel(imageIcon[i]);
		}
	}

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

	void hideButtonImage() {
		for (int i = 0; i < 20; i++) {
			labelImage[i].setIcon(imageBack);
		}
	}

	void dialogResult() {
		//JOptionPane.showMessageDialog(this, "게임 완료!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
		key.setKeyImage(0, true); // 키를 증가시키는 함수 추가
		int option = JOptionPane.showOptionDialog(this,
                "게임 완료!", "Game over", JOptionPane.DEFAULT_OPTION,
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
