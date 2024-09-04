package baekjoon;

import java.util.*;

/**
 * RGB 거리
 *
 * 1. 문제 정리
 * 	1-1. n 개의 집
 * 	1-2. 집을 파랑, 초록, 빨강 중 하나로 칠해야 한다.
 * 	1-3. 칠하는 규칙
 * 		1-3-1. 1번 집의 색은 2번 집과 같지 않아야한다.
 * 		1-3-2. n 번집의 색은 n-1 번 집의 색과 같지 않아야한다.
 * 		1-3-3. i(2 <= i <= n-1) 번 집은  i-1, i+1 번 집의 색과 같지 않아야 한다.
 *	1-4. 규칙을 만족하며 모든 집을 칠하는 비용의 최솟값
 *
 * 2. dp[집 idx][색깔] = 비용
 * 	2-1. dp[0][색깔] 은 초기화를 위해 graph[0][색깔] 을 입력해둔다.
 * 	2-2. 반복문을 1부터 시작한다.
 * 	2-3. 현재 고른 색 == min(이전까지의 누적 집 중 현재고른색이 아닌 것 + 현재 색의 비용)
 * 	2-4. 마지막 idx 의 r, g, b 중 최소의 값을 찾는다.
 */
public class M1149 {

    static Scanner scan = new Scanner(System.in);
    static int n;
    static int[][] graph;

    static final int RED = 0;
    static final int GREEN = 1;
    static final int BLUE = 2;

    public static void main(String[] args) {
        init();
        System.out.println(rgbRoad());
    }

    private static void init() {
        n = scan.nextInt();
        graph = new int[n][3];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < 3; col++) {
                graph[row][col] = scan.nextInt();
            }
        }
    }

    private static int rgbRoad() {
        // 2. dp[집 idx][색깔] = 비용
        int[][] dp = new int[n][3];
        // 2-1. dp[0][색깔] 은 초기화를 위해 graph[0][색깔] 을 입력해둔다.
        for (int color = 0; color < 3; color++) {
            dp[0][color] = graph[0][color];
        }

        // 2-2. 반복문을 1부터 시작한다.
        for (int idx = 1; idx < n; idx++) {
            // 2-3. 현재 고른 색 == min(이전까지의 누적 집 중 현재고른색이 아닌 것 + 현재 색의 비용)
            dp[idx][RED] = Math.min(dp[idx - 1][GREEN] + graph[idx][RED], dp[idx - 1][BLUE] + graph[idx][RED]);
            dp[idx][GREEN] = Math.min(dp[idx - 1][RED] + graph[idx][GREEN], dp[idx - 1][BLUE] + graph[idx][GREEN]);
            dp[idx][BLUE] = Math.min(dp[idx - 1][RED] + graph[idx][BLUE], dp[idx - 1][GREEN] + graph[idx][BLUE]);
        }

        // 2-4. 마지막 idx 의 r, g, b 중 최소의 값을 찾는다.
        int red = dp[n - 1][RED];
        int green = dp[n - 1][GREEN];
        int blue = dp[n - 1][BLUE];
        return Math.min(red, Math.min(green, blue));
    }
}
