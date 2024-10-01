package swea.d3;

import java.util.Scanner;

/**
 * 0/1 Knapsack
 *
 * 1~n 번까지 번호가 부여 된 물건
 * 최대 k 만큼 넣을 수 있는 가방
 *
 * 1~n 까지의 물건은 각각 부피 v 와 가치 c 를 가지고 있다
 * 물건을 몇개 골라 넣어서 가치의 합을 최대화
 * 단, 부피 합이 k 이하
 *
 * k 에서 시작, k ... x ... 1
 * dp[x] 의 최대 무게 = Math.max(dp[x], (dp[x - v] + v)
 * 이를 통해 x + 1까지 위치의 가치를 구함
 *
 * 만약 입력 된 부피가 상한 부피 이하라면, 입력 된 가치와 현재 위치의 가치 비교
 * dp[v] = Math.max(dp[v], c)
 */
public class S3282 {

    static Scanner scan = new Scanner(System.in);
    static int n, k;
    static int[] weight, values;
    static int[][] dp;

    public static void main(String[] args) {
        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            init();
            knapsack();
            System.out.println("#" + tc + " " + dp[n][k]);
        }
    }

    private static void knapsack() {
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= k; col++) {
                if (weight[row] > col) {
                    // row 위치의 가치가 현재 가치보다 크다면
                    dp[row][col] = dp[row - 1][col];
                } else {
                    // 이전까지의 가치 + 추가하려는 가치와 이전까지의 가치 합을 비교한다.
                    dp[row][col] = Math.max(
                            dp[row - 1][col - weight[row]] + values[row],
                            dp[row - 1][col]);
                }
            }
        }
    }

    private static void init() {
        n = scan.nextInt();
        k = scan.nextInt();
        weight = new int[n + 1];
        values = new int[n + 1];
        dp = new int[n + 1][k + 1];

        for (int idx = 1; idx <= n; idx++) {
            weight[idx] = scan.nextInt();
            values[idx] = scan.nextInt();
        }
    }
}
