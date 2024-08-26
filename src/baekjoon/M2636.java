package baekjoon;

import java.util.*;

/**
 * 치즈
 *
 * 1. 문제 정리
 *  1-1. 매 시간 치즈의 테두리를 녹인다.
 *  1-2. 총 녹는 시간과 다 녹기 한시간 전 남은 개수
 *
 * 2. 치즈가 아닌 부분을 탐색한다.
 * 	2-1. 탐색 전 남은 치즈의 개수를 갱신한다.
 * 	2-2. 탐색 중 1을 만나면 해당 부분을 모서리로 판단, 0으로 바꾼다.
 * 	2-3. 모든 영역이 0이되면 종료한다.
 */
public class M2636 {

    static class Pair {
        int x, y;

        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int r, c;
    static int[][] graph;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int total;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        r = scan.nextInt();
        c = scan.nextInt();
        graph = new int[r][c];
        visit = new boolean[r][c];
        total = 0;
        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                graph[row][col] = scan.nextInt();
                if (graph[row][col] == 1) {
                    total++;
                }
            }
        }

        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {

            }
        }
    }

    private static void bfs(Pair start) {
        Queue<Pair> q = new ArrayDeque<>();
        visit[start.x][start.y] = true;
        q.offer(start);

        while(!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0) {
                Pair p = q.poll();

                for (int idx = 0; idx < 4; idx++) {
                    int nx = p.x + dx[idx];
                    int ny = p.y + dy[idx];

                    if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                        continue;
                    }
                    if (visit[nx][ny]) {
                        continue;
                    }

                    if (graph[nx][ny] == 0) {
                        visit[nx][ny] = true;
                        q.offer(new Pair(nx, ny));
                    } else if (graph[nx][ny] == 1) {
                        graph[nx][ny] = 0;
                    }
                }
            }
        }
    }
}

