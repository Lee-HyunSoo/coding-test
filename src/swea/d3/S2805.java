package swea.d3;

import java.util.Scanner;

/**
 * 농작물 수확하기
 *
 * 별 찍기와 유사
 */
public class S2805 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt();
            scan.nextLine();

            int[][] graph = new int[n][n];
            for (int i = 0; i < n; i++) {
                String input = scan.nextLine();
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Character.getNumericValue(input.charAt(j));
                }
            }

            int half = n / 2;
            int answer = 0;

            // 0 ~ half 까지
            // 2, 1, 0 짜리 공백 필요
            for (int i = 0; i <= half; i++) {
                int diff = i + 1;
                int j = 0;
                // 2 - 1, 2 - 2, 2 - 3
                for (; j <= half - diff; j++) {
                }
                // 2 ~ 3
                for (; j < half + diff; j++) {
                    answer += graph[i][j];
                }
                // 3 ~ n
                for (; j < n; j++) {
                }
            }

            for (int i = half + 1; i < n; i++) {
                int diff = i - half;
                int j = 0;
                for (; j < diff; j++) {
                }
                for (; j < n - diff; j++) {
                    answer += graph[i][j];
                }
                for (; j < n; j++) {
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}
