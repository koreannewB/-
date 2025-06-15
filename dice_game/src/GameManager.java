public class GameManager {
    private static int playerTurn = 1;
    private static Player p1; // 버그: p1, p2 선언이 누락되어 있었음
    private static Player p2;
    
    public static int getPlayerTurn() {
        return playerTurn;
    }

    public static void setPlayerTurn(int turn) {
        playerTurn = turn;
    }
    public static void SetPlayers(Player player1, Player player2) {
        p1 = player1;
        p2 = player2;
    }
    public static void toggleTurn() {
        playerTurn = (playerTurn == 1) ? 2 : 1;
        System.out.println("턴이 전환되었습니다. 현재 플레이어: " + playerTurn);
        
    }
}
