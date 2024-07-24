package swea.d3;

import java.util.*;

/**
 * 동철이의 프로그래밍 대회
 */
public class S6913 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt(); // 사람
            int m = scan.nextInt(); // 문제 수

            int[] scores = new int[n];
            int maxScore = 0;
            for (int row = 0; row < n; row++) {
                int sum = 0;
                for (int col = 0; col < m; col++) {
                    sum += scan.nextInt();
                }
                scores[row] = sum;
                maxScore = Math.max(maxScore, sum);
            }

            int count = 0;
            for (int row = 0; row < n; row++) {
                if (scores[row] == maxScore) {
                    count++;
                }
            }
            System.out.println("#" + tc + " " + count + " " + maxScore);
        }
    }
}