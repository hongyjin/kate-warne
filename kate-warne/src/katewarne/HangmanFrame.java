package katewarne;
import javax.swing.*;
import java.awt.*;
public class HangmanFrame extends JFrame {

    public HangmanFrame() {
        setLayout(new BorderLayout());

        HangMan hangmanPanel = new HangMan();

        add(hangmanPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HangmanFrame());
    }
}
