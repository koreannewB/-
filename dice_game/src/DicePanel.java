import java.awt.*;
import java.awt.event.MouseAdapter;     
import java.awt.event.MouseEvent;
import javax.swing.*;

public class DicePanel extends JPanel {
    private JButton[] diceButtons = new JButton[5];
    private Player p1;
    private Player p2;
    private int playerTurn;
    public boolean mixClicked = false;
    private ScorePanel scorePanel;
    public DicePanel(Player p1, Player p2) {
        this.playerTurn = GameManager.getPlayerTurn();
       
        this.p1 = p1;
        this.p2 = p2;
        setLayout(null);
        setPreferredSize(new Dimension(1000, 500));
        
        // �룯�뜃由� 雅뚯눘沅쀯옙�맄 占쎌뵠沃섎챷占쏙옙 占쎄퐬占쎌젟
            for (int j = 0; j < diceButtons.length; j++) {
        ImageIcon icon = loadScaledIcon("/dice0.png", 80, 80);
        diceButtons[j] = new JButton(icon);
        diceButtons[j].setSize(80, 80);
        diceButtons[j].setLocation(40 + 110 * j, 40);
        final int idx = j; // 占쎌뿺占쎈뼄 占쎌굢占쎈뮉 占쎄땀�겫占� 占쎄깻占쎌삋占쎈뮞占쎈퓠占쎄퐣 占쎄텢占쎌뒠占쎈릭占쎌젻筌롳옙 final 占쎌굢占쎈뮉 effectively final占쎌뵠占쎈선占쎈튊 占쎈맙
        setPlayerTurn(GameManager.getPlayerTurn());
        diceButtons[j].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                boolean currentHold;

                if (playerTurn == 1) {
                    currentHold = p1.diceHold[idx];
                    p1.diceHold[idx] = !currentHold; // 占쎈꽅疫뀐옙
                } else {
                    currentHold = p2.diceHold[idx];
                    p2.diceHold[idx] = !currentHold; // 占쎈꽅疫뀐옙
                }

                // 占쎈�믭옙紐®뵳�됱쨮 ��⑥쥙�젟 占쎈연�겫占� 占쎈ご占쎈뻻
                 boolean isHeld = (playerTurn == 1) ? p1.diceHold[idx] : p2.diceHold[idx];
        if (isHeld) {
            diceButtons[idx].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        } else {
            diceButtons[idx].setBorder(UIManager.getBorder("Button.border"));
        }
    }
    });
        add(diceButtons[j]);
    }
        
        // 占쎄퐥疫뀐옙 甕곌쑵�뱣
        JButton mixButton = new JButton("섞기");
        mixButton.setSize(135, 40);
        mixButton.setLocation(600, 80);
        mixButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                threeroll.nextroll();

        if (threeroll.getRoll() <= 0) {
            System.out.println("더 이상 주사위를 굴릴 수 없습니다.");
            return;
        }
        int[] a = new int[5];

        for (int j = 0; j < diceButtons.length; j++) {
            boolean isHeld = (playerTurn == 1) ? p1.diceHold[j] : p2.diceHold[j];
                if (isHeld) {
                // 고정된 주사위는 기존 값과 이미지 유지
                int value = (playerTurn == 1) ? p1.dice[j] : p2.dice[j];
                a[j] = value; 
                diceButtons[j].setIcon(loadScaledIcon("/dice" + value + ".png", 80, 80));
                continue;
            }

            int value = (int) (Math.random() * 6) + 1;
            a[j] = value; 
            diceButtons[j].setIcon(loadScaledIcon("/dice" + value + ".png", 80, 80));
            if (playerTurn == 1) {
                p1.dice[j] = value;
            } else {
                p2.dice[j] = value;
            }
        }

        // 플레이어 객체에 주사위 값 반영
        if (playerTurn == 1) {
            p1.ppdice(a[0], a[1], a[2], a[3], a[4]);
        } else {
            p2.ppdice(a[0], a[1], a[2], a[3], a[4]);
        }

        mixClicked = true;

        remove(scorePanel);
        scorePanel = new ScorePanel(p1, p2, GameManager.getPlayerTurn());
        scorePanel.setBounds(50, 170, 850, 800);
        add(scorePanel);
        revalidate();
        repaint();
    }
});
        add(mixButton);

        // 占쎌젎占쎈땾占쎈솇 占쎈솭占쎄섯 �빊遺쏙옙占� (�룯�뜃由� 1占쎌돳筌랃옙)
        scorePanel = new ScorePanel(p1, p2, playerTurn);
        scorePanel.setBounds(50, 170, 850, 800);
        add(scorePanel);
    }

 
    
    public void setPlayerTurn(int playerTurn) {
        if (this.playerTurn != playerTurn) {
        if (this.playerTurn == 1 && playerTurn == 2) {
            for (int i = 0; i < diceButtons.length; i++) {
                p1.diceHold[i] = false;
                p1.dice[i] = 0;
                diceButtons[i].setBorder(UIManager.getBorder("Button.border"));
                diceButtons[i].setIcon(loadScaledIcon("/dice0.png", 80, 80));
            }
        } else if (this.playerTurn == 2 && playerTurn == 1) {
            for (int i = 0; i < diceButtons.length; i++) {
                p2.diceHold[i] = false;
                p2.dice[i] = 0;
                diceButtons[i].setBorder(UIManager.getBorder("Button.border"));
                diceButtons[i].setIcon(loadScaledIcon("/dice0.png", 80, 80));
            }
        }
    }
    this.playerTurn = playerTurn;
}
        
    
    public boolean isMixClicked() {
        return mixClicked;
    }
    
    private ImageIcon loadScaledIcon(String path, int width, int height) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL == null) {
            System.err.println("사진 오류" + path);
            return new ImageIcon(); // fallback
        }
        ImageIcon icon = new ImageIcon(imgURL);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}