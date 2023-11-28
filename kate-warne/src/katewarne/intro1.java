package intro;

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

	private String textToShow = "Hi my name kate warne. " + "I am a detective. "
			+ "As society becomes more complex and technology develops, events become unknown. "
			+ "Events that society has turned away from, events that have been forgotten by people,,, "
			+ "But it's a nightmare for those involved. " + "To break nightmares, to welcome a new morning. "
			+ "It's the detective's job to do. " + "For someone's new morning to get out of the nightmare again today. "
			+ "I spend my morning as a nightmare....";

	private int currentIndex = 0;

	public intro1() {
		setTitle("Typewriter Text");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 16));
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.BLACK);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);

		// Set caret to the end, so it automatically scrolls to show new text
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		add(textArea);

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
		SwingUtilities.invokeLater(() -> new intro1());
	}
}
