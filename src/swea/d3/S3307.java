package swea.d3;

import java.util.Scanner;

/**
 * 최장 증가 부분 수열
 *
 * dp 사용, 현재 위치에서 이전 idx 탐색
 * 현재 위치보다 이전 idx 값이 작고, dp[이전 idx] 값 중 최대 값을 구함
 *  -- 이전의 최대값을 구해야 가장 최장 수열을 구할 수 있음
 *
 * dp[i] = max + 1
 * answer = Math.max(answer, dp[i])
 */
public class S3307 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt();

            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scan.nextInt();
            }

            int[] dp = new int[n];
            dp[0] = 1;

            int answer = 0;
            for (int i = 1; i < n; i++) {
                int max = 0;
                for (int j = i - 1; j >= 0; j--) {
                    if (arr[i] > arr[j] && max < dp[j]) {
                        max = dp[j];
                    }
                }
                dp[i] = max + 1;
                answer = Math.max(answer, dp[i]);
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}
