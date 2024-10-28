package baekjoon;

import java.util.*;

/**
 * 구간 합 구하기 5
 *
 * 1. 문제 정리
 * 	1-1. N×N개의 수가 N×N 크기의 표에 채워져 있다. (x1, y1)부터 (x2, y2)까지 합을 구하는 프로그램을 작성하시오.
 *
 * 2. (n+1, n+1) 크기의 누적합 배열을 만든다.
 *  2-1. (x, y) 그래프 값
 *  2-2. + (x-1, y) 누적합 값
 *  2-3. + (x, y-1) 누적합 값
 *  2-4. - (x-1, y-1) 누적합 값으로 연산 후 채운다.
 *
 * 3. (x2, y2) 로 총 합을 구하고, (x2, y1 - 1), (x1 - 1, y2) 에 해당하는 누적합 값을 뺀다.
 *
 * 4. 공통으로 빠진 (x1 - 1, y1 - 1) 에 해당하는 누적합 값을 더한다.
 *
 */
public class M11660 {

    static Scanner scan = new Scanner(System.in);
    static int n, m;
    static int[][] graph;
    static int[][] pSum;


    public static void main(String[] args) {
        init();

        for (int row = 0; row < m; row++) {
            int x1 = scan.nextInt();
            int y1 = scan.nextInt();
            int x2 = scan.nextInt();
            int y2 = scan.nextInt();

            int dx = x1 - 1;
            int dy = y1 - 1;
            // 3. (x2, y2) 로 총 합을 구하고, (x2, y1 - 1), (x1 - 1, y2) 에 해당하는 누적합 값을 뺀다.
            // 4. 공통으로 빠진 (x1 - 1, y1 - 1) 에 해당하는 누적합 값을 더한다.
            int result = pSum[x2][y2] - pSum[x2][dy] - pSum[dx][y2] + pSum[dx][dy];
            System.out.println(result);
        }
    }

    private static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        graph = new int[n][n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                graph[row][col] = scan.nextInt();
            }
        }
        // 2. (n+1, n+1) 크기의 누적합 배열을 만든다.
        pSum = new int[n + 1][n + 1];
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                // 2-1. (x, y) 그래프 값
                // 2-2. + (x-1, y) 누적합 값
                // 2-3. + (x, y-1) 누적합 값
                // 2-4. - (x-1, y-1) 누적합 값으로 연산 후 채운다.
                pSum[row][col] = graph[row - 1][col - 1]
                        + pSum[row - 1][col]
                        + pSum[row][col - 1]
                        - pSum[row - 1][col - 1];
            }
        }
    }
}