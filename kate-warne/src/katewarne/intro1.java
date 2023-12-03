package katewarne;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.text.DefaultCaret;

public class intro1 extends JFrame {
	private JTextArea textArea;

	private String textToShow = "나는 케이트 와르네. 바로 탐정이지\n" + "사회가 복잡해지고 기술이 발전할수록 사건들은 미지해진다.\n"
			+ "사회가 외면한 사건들, 사람들에게 잊혀진 사건들,,,\n" + "하지만 당사자들에겐 평생을 남을 악몽이겠지\n" + "악몽을 깰 수 있도록, 새로운 아침을 맞이할 수 있도록\n"
			+ "하는 것이 바로 탐정의 역할이다.\n" + "오늘도 악몽에서 벗어날 누군가의 새로운 아침을 위해 " + "나는 나의 아침을 악몽으로 보낸다...";

	private int currentIndex = 0;

	public intro1() {
		setTitle("케이트 와르네: 대저택 살인사건");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		textArea = new JTextArea();
		textArea.setFont(new Font("Malgun Gothic", Font.PLAIN, 16));
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.BLACK);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);

		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		add(textArea);

		// 글자 다 보여주면 화면 이동
		Timer timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentIndex < textToShow.length()) {
					textArea.append(String.valueOf(textToShow.charAt(currentIndex)));
					currentIndex++;

					if (textArea.getLineCount() >= textArea.getHeight() / textArea.getFont().getSize()) {

						textArea.append("\n");
					}
				} else {
					((Timer) e.getSource()).stop();

					dispose();
					new findletter();
				}
			}
		});

		timer.start();

		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new intro1());
	}
}