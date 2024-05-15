package swea.d2;

import java.util.Scanner;

/**
 * 파리 퇴치
 */
public class S2001 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();

        for (int idx = 1; idx <= t; idx++) {
            int n = scan.nextInt();
            int m = scan.nextInt();

            int[][] arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = scan.nextInt();
                }
            }

            int[][] sum = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    sum[i][j] = arr[i - 1][j - 1] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
                }
            }

            int answer = Integer.MIN_VALUE;
            for (int i = m; i <= n; i++) {
                for (int j = m; j <= n; j++) {
                    int hap = sum[i][j] - sum[i - m][j] - sum[i][j - m] + sum[i - m][j - m];
                    answer = Math.max(answer, hap);
                }
            }

            System.out.println("#" + idx + " " + answer);
        }
    }
}
