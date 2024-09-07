package baekjoon;

import java.util.Scanner;

/**
 * 다리 놓기
 *
 * 1. 문제 정리
 *  1-1. n 개의 왼쪽 점, m 개의 오른쪽 점
 *  1-2. n 을 모두 m 에다 연결
 *  1-3. 연결할 때 겹치면 안됨
 *
 * 2. dp
 *  2-1. 메모이제이션 활용
 *  2-2. left 가 다 연결되지 않았는데 right 의 끝에 도달하면 return 0
 *  2-3. left 가 다 연결되면 return 1
 *  2-4. 기존에 이미 연결한 정보가 있으면 해당 정보 return
 *  2-5. 연결한 것, 다음 것에 연결한 것의 합 return
 */
public class M1010 {

    static Scanner scan = new Scanner(System.in);
    static int n, m;
    static int[][] dp;

    public static void main(String[] args) {
        int t = scan.nextInt();

        for (int i = 0; i < t; i++) {
            init();
            System.out.println(recur(0, 0));
        }
    }

    private static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        dp = new int[n][m];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                dp[row][col] = -1;
            }
        }
    }

    private static int recur(int left, int right) {
        // 2-2. left 가 다 연결되지 않았는데 right 의 끝에 도달하면 return 0
        if (left < n && right == m) {
            return 0;
        }

        // 2-3. left 가 다 연결되면 return 1
        if (left == n) {
            return 1;
        }

        // 2-4. 기존에 이미 연결한 정보가 있으면 해당 정보 return
        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        // 2-5. 연결한 것, 다음 것에 연결한 것의 합 return
        // dp[left][right] = dp[left + 1][right + 1] + dp[left][right + 1];
        return dp[left][right] = recur(left + 1, right + 1) + recur(left, right + 1);
    }
}
