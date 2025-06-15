public class threeroll {
    private static int roll =4;


    public static void resetroll() {
        roll = 5;
    }

    public static void nextroll() {
        roll--;
        if (roll <= -1) {
            System.out.println("더 이상 주사위를 굴릴 수 없습니다.");
            return;
        }
        System.out.println("남아있는 리롤횟수 " + roll + "회");
    }
    public static int getRoll() {
        return roll;
    }

}

