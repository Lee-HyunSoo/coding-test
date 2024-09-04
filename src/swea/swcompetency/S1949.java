package swea.swcompetency;

import java.util.*;

/**
 * 등산로 조성
 *
 * 1. 문제 정리
 *  1-1. 가장 높은 봉우리에서 시작
 *  1-2. 높은 지형에서 내려오며 상하좌우 연결 가능, 더 높은 곳으론 못간다.
 *  1-3. 딱 한 곳을 정해서 k 만큼 지형을 깎을 수 있음
 *
 * 2. 봉우리를 깎는다.
 * 	2-1. 그래프를 돌며 한 칸을 0~k 만큼 깎는다.
 * 	2-2. 깎은 봉우리가 기존에 구했던 최고 봉우리였다면 continue
 * 	2-3. 이동하며 최장경로를 찾는다.
 *
 * 3. 너비우선탐색을 한다.
 * 	3-1. 다음 위치가 자신과 같거나 높으면 continue
 */
public class S1949 {

    static class Pair {
        int x, y;
        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Scanner scan = new Scanner(System.in);
    static int n, k;
    static int[][] graph;
    static List<Pair> highs;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int maxMove;

    public static void main(String[] args) {
        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            init();
            cut();
            System.out.println("#" + tc + " " + maxMove);
        }
    }

    private static void init() {
        n = scan.nextInt();
        k = scan.nextInt();
        graph = new int[n][n];
        highs = new ArrayList<>();
        maxMove = 0;

        int maxHigh = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                graph[row][col] = scan.nextInt();
                maxHigh = Math.max(maxHigh, graph[row][col]);
            }
        }

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (graph[row][col] == maxHigh) {
                    highs.add(new Pair(row, col));
                }
            }
        }
    }

    private static void cut() {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // 2-1. 그래프를 돌며 한 칸을 0~k 만큼 깎는다.
                for (int depth = 0; depth <= k; depth++) {
                    graph[row][col] -= depth;
                    for (int idx = 0; idx < highs.size(); idx++) {
                        Pair high = highs.get(idx);
                        // 2-2. 깎은 봉우리가 기존에 구했던 최고 봉우리였다면 continue
                        if (high.x == row && high.y == col) {
                            continue;
                        }
                        // 2-3. 이동하며 최장경로를 찾는다.
                        maxMove = Math.max(maxMove, move(high));
                    }
                    graph[row][col] += depth;
                }
            }
        }
    }

    private static int move(Pair start) {
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(start);

        int count = 0;
        while(!q.isEmpty()) {
            int len = q.size();
            count++;
            while(len-- > 0) {
                Pair p = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                        continue;
                    }
                    // 3-1. 다음 위치가 자신과 같거나 높으면 continue
                    if (graph[nx][ny] >= graph[p.x][p.y]) {
                        continue;
                    }
                    q.offer(new Pair(nx, ny));
                }
            }
        }
        return count;
    }
}