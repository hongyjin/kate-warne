
package katewarne;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AnagramFrame extends JFrame {

    private static AnagramFrame instance;

    public AnagramFrame() {
        setSize(800, 630);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLocationRelativeTo(null);

        Anagram anagramPanel = Anagram.getInstance();
        getContentPane().add(anagramPanel);
        anagramPanel.setVisible(true);
        setVisible(true);
        
        
        JButton goBackButton = new JButton("게임 종료하기");
        goBackButton.setBackground(Color.BLACK);
        goBackButton.setForeground(Color.WHITE);
        goBackButton.setFocusPainted(false);
        Border border = BorderFactory.createLineBorder(Color.WHITE, 2);
        goBackButton.setBounds(50,10,90,30);
        
        anagramPanel.add(goBackButton);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LawyerRoom.getInstance().setVisible(true);
            }
        }); 
        
    }

    public static AnagramFrame getInstance() {
        if (instance == null) {
            instance = new AnagramFrame();
        }
        return instance;
    }

    public static void main(String[] args) {
    	 SwingUtilities.invokeLater(() -> {
    		 AnagramFrame.getInstance().setVisible(true);
         });
     }
}


