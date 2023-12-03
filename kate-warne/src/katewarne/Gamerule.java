package katewarne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gamerule {
    private JFrame instructionFrame;

    public Gamerule() {
        instructionFrame = new JFrame("게임 설명");
        instructionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon instructionIcon = new ImageIcon("./assets/images/game/KakaoTalk_20231127_131657674.png");
        Image instructionImage = instructionIcon.getImage().getScaledInstance(765, 550, Image.SCALE_SMOOTH);
        ImageIcon resizedInstructionIcon = new ImageIcon(instructionImage);
        JLabel instructionLabel = new JLabel(resizedInstructionIcon);
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionFrame.add(instructionLabel);

        instructionFrame.setSize(800, 600);
        instructionFrame.setVisible(true);

        // Wait for mouse click on instructionFrame
        instructionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                instructionFrame.setVisible(false);
                startGame();
            }
        });
    }

    private void startGame() {
        SwingUtilities.invokeLater(() -> {
            JFrame gameFrame = new JFrame("케이트 와르네: 대저택 살인사건");
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Game game = new Game();
            gameFrame.add(game);

            gameFrame.setSize(800, 600);
            gameFrame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Gamerule());
    }
}
