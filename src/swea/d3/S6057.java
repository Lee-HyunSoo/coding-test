package swea.d3;

import java.util.*;

/**
 * 그래프의 삼각형
 *
 * 정점 n 개 (1 ~ n), 간선 m 개
 * i 번 정점 ~ j 번 정점 / j 번 정점 ~ k 번 정점 / k 번 정점 ~ i 번 정점
 * 이 사이에 모두 간선이 있는 (i, j, k) 를 삼각형 이라 함 (i < j < k)
 *
 * 1 2 -> 1번 정점과 2번 정점 사이에 간선이 있다. && 2번 정점과 1번 정점 사이에 간선이 있다.
 * 2 3 -> 2 ~ 3 && 3 ~ 2 사이에 간선이 있다.
 * 1 3 -> 1 ~ 3 && 3 ~ 1 사이에 간선이 있다.
 * 	-- 이차원 배열을 이용해 양방향 그래프 사용
 * 	-- 1 -> 2를 갈 수 있고, 2 -> 3을 갈 수 있고, 3 -> 1을 갈 수 있으면 삼각형 성립
 *
 *  1. 이차원 배열 탐색
 *  2. graph[x][y] 가 1이면 x = 시작위치, y 는 다음위치
 *  3. x < y 인 경우만 dfs 시작
 */
public class S6057 {

    static int n, m;
    static int[][] graph;
    static boolean[] visit;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = scan.nextInt();
            m = scan.nextInt();

            graph = new int[n + 1][n + 1];
            visit = new boolean[n + 1];
            answer = 0;
            for (int idx = 0; idx < m; idx++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                graph[x][y] = 1;
                graph[y][x] = 1;

            }

            for (int row = 1; row <= n; row++) {
                for (int col = 1; col <= n; col++) {
                    if (graph[row][col] == 1 && row < col) {
                        dfs(row, col, 1);
                    }
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void dfs(int start, int row, int count) {
        if (count == 2) {
            if (graph[row][start] == 1) {
                answer++;
            }
            return;
        }

        for (int col = 1; col <= n; col++) {
            if (graph[row][col] == 1 && row < col) {
                dfs(start, col, count + 1);
            }
        }
    }
}