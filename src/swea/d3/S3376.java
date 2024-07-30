package swea.d3;

import java.util.*;

/**
 * 파도반 수열
 *
 * 1 - 1
 * 2 - 1
 * 3 - 1
 * 4 - 2
 * 5 - 2
 * 6 - 3
 * 7 - 4
 * 8 - 5
 * 9 - 7
 * 10 - 9
 * 11 - 12
 *
 * 1 ~ 4까지 입력
 * 5 = 4 + 0
 * 6 = 5 + 1
 * 7 = 6 + 1
 * 8 = 7 + 1
 * 9 = 8 + 2
 * 10 = 9 + 2
 * 11 = 10 + 3
 * 12 = 11 + 4 -> dp[i] = dp[i - 1] + dp[i - 5]
 *
 * 숫자가 커질 가능성 -> long 사용
 */
public class S3376 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt();

            long[] dp = new long[101];
            dp[1] = 1;
            dp[2] = 1;
            dp[3] = 1;
            dp[4] = 2;

            for (int i = 5; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 5];
            }
            System.out.println("#" + tc + " " + dp[n]);
        }
    }
}