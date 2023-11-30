package katewarne;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class bathRoomChat extends JFrame {
    private JLabel hImageLabel;
    private JLabel textLabel1;
    private JLabel textLabel2;
    private JLabel chatButton;
    private boolean isFirstClick = true;
    private BathroomPanel bathroomPanel; // BathroomPanel을 참조하기 위한 필드

    public bathRoomChat() {
        setTitle("케이트 와르네: 대저택 살인사건");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        bathroomPanel = new BathroomPanel();
        bathroomPanel.setVisible(false);
        bathroomPanel.setBounds(0, 0, 800, 600);
        add(bathroomPanel);

        hImageLabel = new JLabel();
        hImageLabel.setBounds(0, 0, 800, 650);
        add(hImageLabel);
        addTextLabels();
        
        addChatButton("./assets/images/chatButton.png", 650, 525);
        addChatBoxImage("./assets/images/chatBoxImage.png", 78, 390);
        addImageLabel("./assets/images/BathroomImage.png", 0, 0);

        
        setVisible(true);
    }

    private void addTextLabels() {
        // 캐릭터 이름
        textLabel1 = new JLabel("케이트 와르네");
        textLabel1.setForeground(Color.WHITE);
        textLabel1.setFont(new Font("Malgun Gothic", Font.BOLD, 18));
        textLabel1.setBounds(120, 450, 600, 30);
        add(textLabel1);

        // 대화 내용
        textLabel2 = new JLabel("사건은 작가 저택의 화장실에서 발생했군..");
        textLabel2.setForeground(Color.WHITE);
        textLabel2.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
        textLabel2.setBounds(120, 490, 600, 30);
        add(textLabel2);
    }

    private void addImageLabel(String imagePath, int x, int y) {
        JLabel label = new JLabel();
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();
        Image sizedImage = image.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        ImageIcon sizedIcon = new ImageIcon(sizedImage);
        label.setIcon(sizedIcon);
        label.setBounds(x, y, 800, 600);
        add(label);
    }

    private void addChatBoxImage(String imagePath, int x, int y) {
        JLabel chatBoxLabel = new JLabel();
        ImageIcon chatBoxIcon = new ImageIcon(imagePath);
        Image chatBoxImage = chatBoxIcon.getImage();
        Image sizedChatBoxImage = chatBoxImage.getScaledInstance(650, 200, Image.SCALE_SMOOTH);
        ImageIcon sizedChatBoxIcon = new ImageIcon(sizedChatBoxImage);
        chatBoxLabel.setIcon(sizedChatBoxIcon);
        chatBoxLabel.setBounds(x, y, 650, 200);
        add(chatBoxLabel);
    }

    private void addChatButton(String imagePath, int x, int y) {
        chatButton = new JLabel();
        ImageIcon buttonIcon = new ImageIcon(imagePath);
        Image buttonImage = buttonIcon.getImage();
        Image sizedButtonImage = buttonImage.getScaledInstance(25, 15, Image.SCALE_SMOOTH);
        ImageIcon sizedButtonIcon = new ImageIcon(sizedButtonImage);
        chatButton.setIcon(sizedButtonIcon);
        chatButton.setBounds(x, y, 100, 50);
        chatButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // chatButton 클릭
                if (isFirstClick) {
                    textLabel2.setText("화장실에서 수상한 물건들을 찾아보자.");
                    isFirstClick = false;
                } else {
                    openBathroomPanel(); // BathroomPanel 열기
                }
            }
        });
        add(chatButton);
    }
    
    private void addFullHouseButton(String imagePath, int x, int y) {
        JLabel fullHouseButton = new JLabel();
        ImageIcon buttonIcon = new ImageIcon(imagePath);
        Image buttonImage = buttonIcon.getImage();
        Image sizedButtonImage = buttonImage.getScaledInstance(150, 50, Image.SCALE_SMOOTH);
        ImageIcon sizedButtonIcon = new ImageIcon(sizedButtonImage);
        fullHouseButton.setIcon(sizedButtonIcon);
        fullHouseButton.setBounds(x, y, 150, 50);
        fullHouseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleFullHouseButtonClick();
            }
        });
        add(fullHouseButton);
    }

    private void handleFullHouseButtonClick() {
    	setVisible(false); // 현재 창 감추기
        new MainFrame().setVisible(true);
    }

    private void openBathroomPanel() {
        // 패널 전환
        bathroomPanel.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new bathRoomChat();
        });
    }


    class BathroomPanel extends JPanel {
        private JLabel hImageLabel;
        private JLabel bathExplanationLabel;
        private JLabel speakerExplanationLabel;
        private JLabel bathSaltsExplanationLabel;

        public BathroomPanel() {
            setLayout(null);

            bathExplanationLabel = new JLabel();
            bathExplanationLabel.setBounds(0, 0, 800, 600);
            add(bathExplanationLabel);

            speakerExplanationLabel = new JLabel();
            speakerExplanationLabel.setBounds(0, 0, 800, 600);
            add(speakerExplanationLabel);

            bathSaltsExplanationLabel = new JLabel();
            bathSaltsExplanationLabel.setBounds(0, 0, 800, 600);
            add(bathSaltsExplanationLabel);

            hImageLabel = new JLabel();
            hImageLabel.setBounds(0, 0, 800, 600);
            add(hImageLabel);

            // 욕조 호버를 위한 패널
            JPanel plHoverPanel = createBathHoverPanel();
            plHoverPanel.setBounds(110, 490, 400, 400);
            add(plHoverPanel);
            plHoverPanel.setOpaque(false);

            // 스피커 호버를 위한 패널
            JPanel spHoverPanel = createSpeakerHoverPanel();
            spHoverPanel.setBounds(210, 330, 40, 60);
            add(spHoverPanel);
            spHoverPanel.setOpaque(false);

            // 입욕제 호버를 위한 패널
            JPanel bsHoverPanel = createBathSaltsHoverPanel();
            bsHoverPanel.setBounds(620, 440, 40, 60);
            add(bsHoverPanel);
            bsHoverPanel.setOpaque(false);
           
            
            addFullHouseButton("./assets/images/fullHouseButton.png", 600, 20); // 대저택 이동 버튼
            addImageLabel("./assets/images/BathroomImage.png", 0, 0); // 욕실 배경 이미지
        }

        private JPanel createBathHoverPanel() {
            JPanel hoverPanel = new JPanel();

            hoverPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    bathHoverFunction();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hoverOutFunction();
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    // 클릭 시 욕조 설명창 띄우기
                    showBathExplanation();
                }
            });

            return hoverPanel;
        }

        private JPanel createSpeakerHoverPanel() {
            JPanel hoverPanel = new JPanel();

            hoverPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    // 마우스 호버했을 때
                    speakerHoverFunction();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // 마우스 호버 나갔을 때
                    hoverOutFunction();
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    // 클릭 시 스피커 설명창 띄우기
                    showSpeakerExplanation();
                }
            });

            return hoverPanel;
        }

        // 욕조 설명창
        private void showBathExplanation() {
            ImageIcon imageIcon = new ImageIcon("./assets/images/bathExplanation.png");
            Image image = imageIcon.getImage();
            Image sizedImage = image.getScaledInstance(600, 490, Image.SCALE_SMOOTH);
            ImageIcon sizedIcon = new ImageIcon(sizedImage);

            bathExplanationLabel.setIcon(sizedIcon);
            bathExplanationLabel.setLocation(105, -10);
            bathExplanationLabel.setVisible(true);

            bathExplanationLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    hideBathExplanation();
                }
            });
        }

        // 설명창을 다시 누르면 닫히도록
        private void hideBathExplanation() {
            bathExplanationLabel.setIcon(null);
            bathExplanationLabel.setVisible(false);
        }

        // 스피커 설명창
        private void showSpeakerExplanation() {
            ImageIcon imageIcon = new ImageIcon("./assets/images/speakerExplanation.png");
            Image image = imageIcon.getImage();
            Image sizedImage = image.getScaledInstance(600, 490, Image.SCALE_SMOOTH);
            ImageIcon sizedIcon = new ImageIcon(sizedImage);

            speakerExplanationLabel.setIcon(sizedIcon);
            speakerExplanationLabel.setLocation(105, -10);
            speakerExplanationLabel.setVisible(true);

            speakerExplanationLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    hideSpeakerExplanation();
                }
            });
        }

        // 설명창을 다시 누르면 닫히도록
        private void hideSpeakerExplanation() {
            speakerExplanationLabel.setIcon(null);
            speakerExplanationLabel.setVisible(false);
        }

        // 입욕제 설명창
        private void showBathSaltsExplanation() {
            ImageIcon imageIcon = new ImageIcon("./assets/images/bathSaltsExplanation.png");
            Image image = imageIcon.getImage();
            Image sizedImage = image.getScaledInstance(600, 490, Image.SCALE_SMOOTH);
            ImageIcon sizedIcon = new ImageIcon(sizedImage);

            bathSaltsExplanationLabel.setIcon(sizedIcon);
            bathSaltsExplanationLabel.setLocation(105, -10);
            bathSaltsExplanationLabel.setVisible(true);

            bathSaltsExplanationLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    hideBathSaltsExplanation();
                }
            });
        }

        // 설명창을 다시 누르면 닫히도록
        private void hideBathSaltsExplanation() {
            bathSaltsExplanationLabel.setIcon(null);
            bathSaltsExplanationLabel.setVisible(false);
        }

        private JPanel createBathSaltsHoverPanel() {
            JPanel hoverPanel = new JPanel();

            hoverPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    bathSaltsHoverFunction();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hoverOutFunction();
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    // 클릭 시 실행할 동작
                    showBathSaltsExplanation();
                }
            });

            return hoverPanel;
        }

        private void bathHoverFunction() {
            ImageIcon imageIcon = new ImageIcon("./assets/images/bathImageBolder.png");
            Image image = imageIcon.getImage();
            Image sizedImage = image.getScaledInstance(650, 550, Image.SCALE_SMOOTH);
            ImageIcon sizedIcon = new ImageIcon(sizedImage);

            hImageLabel.setIcon(sizedIcon);
            hImageLabel.setLocation(30, 137);
        }

        private void speakerHoverFunction() {
            ImageIcon imageIcon = new ImageIcon("./assets/images/speakerImageBolder.png");
            Image image = imageIcon.getImage();
            Image sizedImage = image.getScaledInstance(360, 320, Image.SCALE_SMOOTH);
            ImageIcon sizedIcon = new ImageIcon(sizedImage);

            hImageLabel.setIcon(sizedIcon);
            hImageLabel.setLocation(60, 60);
        }

        private void bathSaltsHoverFunction() {
            ImageIcon imageIcon = new ImageIcon("./assets/images/bathSaltsImageBolder.png");
            Image image = imageIcon.getImage();
            Image sizedImage = image.getScaledInstance(360, 320, Image.SCALE_SMOOTH);
            ImageIcon sizedIcon = new ImageIcon(sizedImage);

            hImageLabel.setIcon(sizedIcon);
            hImageLabel.setLocation(470, 172);
        }

        private void hoverOutFunction() {
            hImageLabel.setIcon(null);
        }

        private void addImageLabel(String imagePath, int x, int y) {
            JLabel label = new JLabel();
            ImageIcon imageIcon = new ImageIcon(imagePath);
            Image image = imageIcon.getImage();
            Image sizedImage = image.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
            ImageIcon sizedIcon = new ImageIcon(sizedImage);
            label.setIcon(sizedIcon);
            label.setBounds(x, y, 800, 600);
            add(label);
        }
    }
}