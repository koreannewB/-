

public class Player{
    public int[] dice = new int[5]; // 5개의 주사위, 각 주사위는 [값, 상태]로 구성 0미정 1선택택
    public boolean[] diceHold = new boolean[5]; // 주사위 고정 여부, true면 고정, false면 변경 가능 
    public int score = 0; // 플레이어의 점수
    public int[] scoreList = new int[13]; // 점수 목록, 0~12까지의 점수
    public boolean[] scoreUsed = new boolean[13]; // 점수 사용 여부, true면 사용됨, false면 사용 안됨
   int[] count = new int[7];
    /*
     *  0. 1의 갯수
     *  1. 2의 갯수
     *  2. 3의 갯수
     *  3. 4의 갯수
     *  4. 5의 갯수
     *  5. 6의 갯수
     *  6. 3개의 동일한 숫자
     *  7. 4개의 동일한 숫자
     *  8. 풀 하우스 (3개의 동일한 숫자 + 2개의 동일한 숫자) 고정 25점
     *  9. 스몰스트레이트 (1,2,3,4 또는 2,3,4,5) 고정 30점
     *  10. 라지스트레이트 (1,2,3,4,5 또는) 고정 40점
     *  11. 초이스 (5개의 주사위눈의 총합)
     *  12. 야추 (5개의 동일한 숫자) 고정 50점
     */
    public int recall = 0;
    public void ppdice(int dice1, int dice2, int dice3, int dice4, int dice5) {
        this.dice[0] = dice1;    
        this.dice[1] = dice2;
        this.dice[2] = dice3;
        this.dice[3] = dice4;
        this.dice[4] = dice5;
        System.out.println("주사위 값이 설정되었습니다: " + dice1 + ", " + dice2 + ", " + dice3 + ", " + dice4 + ", " + dice5);
        
        Aces(); // Aces 점수 계산
        Twos(); // Twos 점수 계산
        Threes(); // Threes 점수 계산
        Fours(); // Fours 점수 계산
        Fives(); // Fives 점수 계산
        Sixes(); // Sixes 점수 계산
        ThreeOfAKind(); // Three of a Kind 점수 계산
        FourOfAKind(); // Four of a Kind 점수 계산
        FullHouse(); // Full House 점수 계산
        SmallStraight(); // Small Straight 점수 계산
        LargeStraight(); // Large Straight 점수 계산
        Choice(); // Choice 점수 계산
        Yatzy(); // Yatzy 점수 계산

        for (int i = 0; i < 5; i++) {
        int value = dice[i];
        if (value >= 1 && value <= 6) {
            count[value]++;
        }
    }
        
    }

    public void Aces() {
        if(scoreUsed[0] == true) {
            return;
        }
        scoreList[0] = 0;
        for (int i = 0; i < 5; i++) {
            if (dice[i] == 1) {
                scoreList[0] += 1;
            }
        }
        this.scoreList[0] = scoreList[0];
    }

    public void Twos() {
         if(scoreUsed[1] == true) {
            return;
        }
        scoreList[1] = 0;
        for (int i = 0; i < 5; i++) {
            if (dice[i] == 2) {
                scoreList[1] += 2;
            }
        }
    }

    public void Threes() {
         if(scoreUsed[2] == true) {
            return;
        }
        scoreList[2] = 0;
        for (int i = 0; i < 5; i++) {
            if (dice[i] == 3) {
                scoreList[2] += 3;
            }
        }
    }

    public void Fours() {
         if(scoreUsed[3] == true) {
            return;
        }
        scoreList[3] = 0;
        for (int i = 0; i < 5; i++) {
            if (dice[i] == 4) {
                scoreList[3] += 4;
            }
        }
    }

    public void Fives() {
         if(scoreUsed[4] == true) {
            return;
        }
        scoreList[4] = 0;
        for (int i = 0; i < 5; i++) {
            if (dice[i] == 5) {
                scoreList[4] += 5;
            }
        }
    }

    public void Sixes() {
         if(scoreUsed[5] == true) {
            return;
        }
        scoreList[5] = 0;
        for (int i = 0; i < 5; i++) {
            if (dice[i] == 6) {
                scoreList[5] += 6;
            }
        }
    }

    public void ThreeOfAKind() {
         if(scoreUsed[6] == true) {
            return;
        }
        boolean found = false;
        for (int i = 1; i <= 6; i++) {
        if (count[i] >= 3) {
            found = true;
            break;
        }
        if (found) {
        int sum = 0;
        for (int t = 0; t < 5; t++) {
            scoreList[6] += dice[t];
            }
        } else {
            scoreList[6] = 0; // 3개의 동일한 숫자가 없으면 점수는 0
        };
     }
        // 구현 필요
    }

    public void FourOfAKind() {
         if(scoreUsed[7] == true) {
            return;
        }
        boolean found = false;
        for (int i = 1; i <= 6; i++) {
        if (count[i] >= 4) {
            found = true;
            break;
        }
        if (found) {
        int sum = 0;
        for (int t = 0; t < 5; t++) {
            scoreList[7] += dice[t];
            }
        } else {
            scoreList[7] = 0; // 3개의 동일한 숫자가 없으면 점수는 0
        };
     }
    }

    public void FullHouse(){
    boolean hasThree = false;
    boolean hasTwo = false;
         if(scoreUsed[8] == true) {
            return;
        }
    for (int i = 1; i <= 6; i++) {
        if (count[i] == 3) {
            hasThree = true;
        } else if (count[i] == 2) {
            hasTwo = true;
        }
    }

    if (hasThree && hasTwo) {
        scoreList[8] = 25; // 고정 점수
    } else {
        scoreList[8] = 0;
    }
}

    public void SmallStraight(){
    if(scoreUsed[9] == true) {
                return;
            }
      boolean[] exists = new boolean[7]; // 1~6까지의 주사위 값 존재 여부

    for (int i = 0; i < 5; i++) {
        int val = dice[i];
        if (val >= 1 && val <= 6) {
            exists[val] = true;
        }
    }

    // 가능한 4-연속 스트레이트 검사
    if ((exists[1] && exists[2] && exists[3] && exists[4]) ||
        (exists[2] && exists[3] && exists[4] && exists[5]) ||
        (exists[3] && exists[4] && exists[5] && exists[6])) {
        scoreList[9] = 30;
        
    } else {
        scoreList[9] = 0;
        
    }
    }

    public void LargeStraight() {
         if(scoreUsed[10] == true) {
            return;
        }
        boolean[] exists = new boolean[7];

    for (int i = 0; i < 5; i++) {
        int val = dice[i];
        if (val >= 1 && val <= 6) {
            exists[val] = true;
        }
    }

    if ((exists[1] && exists[2] && exists[3] && exists[4] && exists[5]) ||
        (exists[2] && exists[3] && exists[4] && exists[5] && exists[6])) {
        scoreList[10] = 40;
       
    } else {
        scoreList[10]= 0;
        
    }
    }

    public void Choice() {
         if(scoreUsed[11] == true) {
            return;
        }
         int total = 0;
    for (int i = 0; i < 5; i++) {
        total += dice[i];
    }
    scoreList[11] = total;
    }

    public void Yatzy() {
    if(scoreUsed[12] == true) {
            return;
        }
        int val = dice[0];
    boolean allSame = true;

    for (int i = 1; i < 5; i++) {
        if (dice[i] != val) {
            allSame = false;
            break;
        }
    }

    if (allSame) {
        scoreList[12] = 50;  
    } else {
        scoreList[12] = 0;  
    }
    }
    public int getUsedScoreSum() {
        int sum = 0;
        for (int i = 0; i < scoreList.length; i++) {
            if (scoreUsed[i]) {
                sum += scoreList[i];
            }
        }
        return sum;
    }
}