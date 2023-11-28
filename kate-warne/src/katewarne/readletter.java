package katewarne;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.text.DefaultCaret;

public class readletter extends JFrame {
	private JTextArea textArea;

	private String textToShow = 
			"안녕하세요, 케이트 와르네 탐정님"
		    + "사건을 의뢰하기 위해 이렇게 편지를 보냅니다."
		    + "저는 현재 총 3권의 대작을 지필한 현재 이름을 알리고 있는 소설가 kim입니다."
		    + "제가 지내고 있는 집에 수상한 기운이 느껴져 이렇게 미리 편지를 적습니다."
		    + "만약 이 편지가 탐정님 손에 있다면 저에게 무슨 일이 있는 거겠죠..."
		    + "저희 집에는 총 두 명의 손님이 계시는데,"
		    + "한 분은 3년 째 같이 일하고 있는 변호사로 같이 지내면서 저의 법적 사항들을 관리하고 있습니다."
		    + "다른 한 분은 저의 차기작 집필과 자문을 위해 5일 전부터 대저택에 묵고 있던 자문가 손님 입니다."
		    + "아무래도 저의 집 분위기가 심상치 않아 이렇게 적어둡니다."
		    + "꼭 이 편지지가 제 책상 속에 고히 있었으면 좋겠네요..."
		    + "사건이 일어났다면... 해결해주시길 바랍니다."
		    + "이상. 소설가 kim 올림";

	private int currentIndex = 0;

	public readletter() {
		setTitle("Typewriter Text");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout());

		// Set background image
		String imagePath = "C:/Users/simpl/OneDrive/문서/카카오톡 받은 파일/KakaoTalk_20231110_000207744.jpg";
		JLabel backgroundLabel = new JLabel(new ImageIcon(imagePath));
		add(backgroundLabel, BorderLayout.CENTER);

		textArea = new JTextArea();
		textArea.setFont(new Font("Malgun Gothic", Font.PLAIN, 16));
		textArea.setForeground(Color.BLACK); // Set text color to black
		textArea.setOpaque(false); // Make text area transparent
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);

		// Set caret to the end, so it automatically scrolls to show new text
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		// Add text area on top of the background image
		backgroundLabel.setLayout(new BorderLayout());
		backgroundLabel.add(textArea);

		Timer timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentIndex < textToShow.length()) {
					textArea.append(String.valueOf(textToShow.charAt(currentIndex)));
					currentIndex++;

					if (textArea.getLineCount() >= textArea.getHeight() / textArea.getFont().getSize()) {
						// If the text exceeds the visible area, add a newline
						textArea.append("\n");
					}
				} else {
					((Timer) e.getSource()).stop(); // Stop the timer when all text is displayed
				}
			}
		});

		timer.start();

		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new readletter());
	}
}
