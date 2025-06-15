import javax.swing.*;
public class Dice extends JFrame {
    public DicePanel panel;

    public Dice(Player p1, Player p2) {
        setTitle("야추 게임");
        setSize(1000, 500);
        createMenu();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        panel = new DicePanel(p1, p2); // 패널을 필드에 저장
        panel.setBounds(0, 0, 1000, 500); // 위치 설정
        add(panel);

        setVisible(true);
    }
    private void createMenu() {
        JMenuBar mBar = new JMenuBar();
        JMenu menu = new JMenu("도움말");

        // Rule.Basicrules()는 void이므로, 규칙 내용을 문자열로 반환하는 메서드를 새로 만듭니다.
        JMenuItem ruleItem = new JMenuItem("게임 규칙 보기");
        JMenuItem ruleItem2 = new JMenuItem("주사위 조합");
        ruleItem.addActionListener(e -> {
        // 규칙 내용을 다이얼로그로 출력
        JOptionPane.showMessageDialog(this, Rule.getBasicRulesText(), "야추 게임 규칙", JOptionPane.INFORMATION_MESSAGE);
        
    });
        ruleItem2.addActionListener(e -> {
            // 규칙 내용을 다이얼로그로 출력
            JOptionPane.showMessageDialog(this, Rule.getdiceRulesText(), "야추 게임 규칙", JOptionPane.INFORMATION_MESSAGE);
            
        });

    
    menu.add(ruleItem);
    menu.add(ruleItem2);
    mBar.add(menu);
    setJMenuBar(mBar);
}
    public static int progress(int t) {
        int playerturn = t;
        System.out.println("게임이 시작되었습니다. 현재 플레이어: " + playerturn);
        return playerturn;
    }

    public static void main(String[] args) {
        Player p1 = new Player();
        Player p2 = new Player();
        GameManager.setPlayerTurn(1);
        GameManager.SetPlayers(p1, p2);
        Dice diceWindow = new Dice(p1, p2); // Dice 인스턴스를 변수에 저장
        progress(GameManager.getPlayerTurn());
        while (true) {

            

            // 주사위 섞기 버튼이 눌릴 때까지 대기
            diceWindow.panel.mixClicked = false; // mixClicked 플래그 초기화
            while (!diceWindow.panel.isMixClicked()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            diceWindow.panel.setPlayerTurn(GameManager.getPlayerTurn());
            // 플레이어 턴 전환
        
            // 다음 턴을 위해 패널에 새로운 턴 정보 전달 (필요하다면 DicePanel에 setter 추가)
            
        }
    }
}