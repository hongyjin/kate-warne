package intro;

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

	private String textToShow = "Hello, Detective Kate Warne. " + "I am writing to request a case. "
			+ "I'm Kim, a novelist who has written a total of three masterpieces. "
			+ "I write a letter in advance because I feel suspicious about the house I'm staying in. "
			+ "f this letter is in your hands, something will happen to me... " + "We have two guests in total, "
			+ "One of them has been working with me for three years and has been managing my legal affairs. "
			+ "The other is a prison guard guest who has been staying at my mansion for five days to write and consult my next film. "
			+ "I write it down like this because the atmosphere in my house is unusual. "
			+ "I hope this letter paper is still on my desk... " + "If the incident happened... Please solve it. "
			+ "That's all. From novelist Kim";

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
		textArea.setFont(new Font("Courier New", Font.PLAIN, 16));
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
