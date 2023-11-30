package katewarne;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HangmanFrame extends JFrame {

    private static HangmanFrame instance;

    public HangmanFrame() {
        HangMan hangmanPanel = new HangMan();
        
        setLayout(new BorderLayout());
        add(hangmanPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        // 뒤로가기 버튼
        JButton goBackButton = new JButton("게임 종료하기");
        goBackButton.setBackground(Color.BLACK);
        goBackButton.setForeground(Color.WHITE);
        goBackButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(new Color(20, 4, 4));
        buttonPanel.add(goBackButton);
        hangmanPanel.add(buttonPanel, BorderLayout.PAGE_END);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LawyerRoom.getInstance().setVisible(true);
                
            }
        }); 
    }

    public static HangmanFrame getInstance() {
        if (instance == null) {
            instance = new HangmanFrame();
        }
        return instance;
    }

    public static void main(String[] args) {
    	 SwingUtilities.invokeLater(() -> {
    		 HangmanFrame.getInstance().setVisible(true);
         });
     }
}

