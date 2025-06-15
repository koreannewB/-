import java.awt.*;
import java.awt.event.MouseAdapter;     
import java.awt.event.MouseEvent;
import javax.swing.*;

public class ScorePanel extends JPanel {
    private Player p1;
    private Player p2;
    private int playerTurn;
    private java.util.List<JButton> p1Buttons = new java.util.ArrayList<>();
    private java.util.List<JButton> p2Buttons = new java.util.ArrayList<>();
    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
        System.out.println("현재 플레이어 턴: " + playerTurn);
    }
    public ScorePanel(Player p1, Player p2, int playerTurn) {
        this.p1 = p1;
        this.p2 = p2;
        this.playerTurn = playerTurn;
        setLayout(null);
        setPreferredSize(new Dimension(800, 800));

        JLabel scoreLabel = new JLabel("점수판");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 30));
        scoreLabel.setBounds(150, 20, 100, 30);
        add(scoreLabel);

        String[] scoreNames = {
            "Aces", "Twos", "Threes", "Fours", "Fives", "Sixes",
            "Three-of-a-kind", "Four-of-A-kind", "Full House",
            "Small Straight", "Large Straight", "Chance", "Yagtzee"
        };
        JLabel p1ScoreLabel, p2ScoreLabel;
        p1ScoreLabel = new JLabel("p1score: " + p1.getUsedScoreSum());
        p1ScoreLabel.setFont(new Font("Arial", Font.BOLD, 25));
        p1ScoreLabel.setBounds(700, 10, 150, 30);
        add(p1ScoreLabel);

        p2ScoreLabel = new JLabel("p2score: " + p2.getUsedScoreSum());
        p2ScoreLabel.setFont(new Font("Arial", Font.BOLD, 25));
        p2ScoreLabel.setBounds(700, 40, 150, 30);
        add(p2ScoreLabel);

        for (int i = 0; i < scoreNames.length; i++) {
            JLabel label = new JLabel(scoreNames[i]);
            label.setFont(new Font("Arial", Font.PLAIN, 20));
            final int idx = i;
            int p1score = (p1 != null && p1.scoreList != null && p1.scoreList.length > i) ? p1.scoreList[i] : 0;
           
            JButton scoreButton = new JButton(String.valueOf(p1score));
            scoreButton.setFont(new Font("Arial", Font.PLAIN, 18));
            scoreButton.setBackground(Color.WHITE);

            scoreButton.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {

                    if (playerTurn != 1 || p1.scoreUsed[idx]) return;

                    p1.scoreUsed[idx] = true;
                    scoreButton.setEnabled(false);
                    scoreButton.setBackground(Color.LIGHT_GRAY);
                    scoreButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    threeroll.resetroll();
                    p1ScoreLabel.setText("p1score: " + p1.getUsedScoreSum());
                    System.out.println("점수 기입: 2플레이어 턴으로 변경");

                    // 버튼 상태 전환
                    for (JButton btn : p1Buttons) {
                        btn.setEnabled(false);
                        btn.setBackground(Color.LIGHT_GRAY);
                    }
                    for (JButton btn : p2Buttons) {
                        btn.setEnabled(true);
                        btn.setBackground(Color.WHITE);
                    }

                    // 턴 전환
                     GameManager.toggleTurn();
                }
            });

            if (i < 6) {
                label.setBounds(50, 70 + i * 30, 110, 30);
                scoreButton.setBounds(160, 70 + i * 30, 60, 30);
            } else {
                label.setBounds(360, 70 + (i - 6) * 30, 180, 30);
                scoreButton.setBounds(540, 70 + (i - 6) * 30, 60, 30);
            }
            add(label);
            add(scoreButton);
            p1Buttons.add(scoreButton);
        }

        // p2 점수 버튼
        for (int i = 0; i < scoreNames.length; i++) {
            final int idx = i;
            int p2score = (p2 != null && p2.scoreList != null && p2.scoreList.length > i) ? p2.scoreList[i] : 0;
           
            JButton scoreButton = new JButton(String.valueOf(p2score));
            scoreButton.setFont(new Font("Arial", Font.PLAIN, 18));
            scoreButton.setBackground(Color.LIGHT_GRAY); // 초기엔 비활성 상태

            scoreButton.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (playerTurn != 2 || p2.scoreUsed[idx]) return;

                    p2.scoreUsed[idx] = true;
                    scoreButton.setEnabled(false);
                    scoreButton.setBackground(Color.LIGHT_GRAY);
                    scoreButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    threeroll.resetroll();
                    System.out.println("점수 기입: 1플레이어 턴으로 변경");
                     p2ScoreLabel.setText("p2score: " + p2.getUsedScoreSum());
                    for (JButton btn : p2Buttons) {
                        btn.setEnabled(false);
                        btn.setBackground(Color.LIGHT_GRAY);
                    }
                    for (JButton btn : p1Buttons) {
                        if (!p1.scoreUsed[p1Buttons.indexOf(btn)]) {
                            btn.setEnabled(true);
                            btn.setBackground(Color.WHITE);
                        }
                    }

                    // 턴 전환
                
                     GameManager.toggleTurn();
                }
            });

            if (i < 6) {
                scoreButton.setBounds(220, 70 + i * 30, 60, 30);
            } else {
                scoreButton.setBounds(600, 70 + (i - 6) * 30, 60, 30);
            }

            add(scoreButton);
            p2Buttons.add(scoreButton);
        }

        // 초기 버튼 상태 조정
        updateButtonStates();
    }

        private void updateButtonStates() {
        // 1P 버튼 상태
        for (int i = 0; i < p1Buttons.size(); i++) {
            JButton btn = p1Buttons.get(i);
            if (playerTurn == 1 && !p1.scoreUsed[i]) {
                btn.setEnabled(true);
                btn.setBackground(Color.WHITE);
            } else {
                btn.setEnabled(false);
                btn.setBackground(Color.LIGHT_GRAY);
            }
        }
        // 2P 버튼 상태
        for (int i = 0; i < p2Buttons.size(); i++) {
            JButton btn = p2Buttons.get(i);
            if (playerTurn == 2 && !p2.scoreUsed[i]) {
                btn.setEnabled(true);
                btn.setBackground(Color.WHITE);
            } else {
                btn.setEnabled(false);
                btn.setBackground(Color.LIGHT_GRAY);
            }
        }
    }
}