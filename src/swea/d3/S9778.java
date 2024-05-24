package swea.d3;

import java.util.*;

/**
 * 카드 게임
 */
public class S9778 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            int[] card = new int[12];

            for (int i = 2; i <= 11; i++) {
                if (i == 10) {
                    card[i] = 16;
                } else {
                    card[i] = 4;
                }
            }

            int sum = 0;
            for (int i = 0; i < n; i++) {
                int draw = scan.nextInt();
                sum += draw;
                card[draw]--;
            }

            int result = 0;
            for (int i = 2; i <= 11; i++) {
                if (sum + i <= 21) {
                    result += card[i];
                } else {
                    result -= card[i];
                }
            }

            String answer = "STOP";
            if (result > 0) {
                answer = "GAZUA";
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
