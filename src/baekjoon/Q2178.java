package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 미로 탐색
 */
public class Q2178 {

    static int[][] graph;
    static boolean[][] visit;

    static class Pair {
        int x, y;

        public Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int m = scan.nextInt();
        scan.nextLine();
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        graph = new int[n + 1][m + 1];
        visit = new boolean[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String input = scan.nextLine();
            for (int j = 1; j <= m; j++) {
                graph[i][j] = Character.getNumericValue(input.charAt(j - 1));
            }
        }

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(1, 1));
        visit[1][1] = true;
        while (!q.isEmpty()) {
            int len = q.size();
            boolean flag = false;
            for (int i = 0; i < len; i++) {
                Pair p = q.poll();

                if (p.x == n && p.y == m) {
                    flag = true;
                    break;
                }

                for (int j = 0; j < 4; j++) {
                    int nx = p.x + dx[j];
                    int ny = p.y + dy[j];

                    if (0 < nx && nx <= n && 0 < ny && ny <= m) {
                        if (!visit[nx][ny] && graph[nx][ny] == 1) {
                            visit[nx][ny] = true;
                            graph[nx][ny] = graph[p.x][p.y] + 1;
                            q.offer(new Pair(nx, ny));
                        }
                    }
                }
            }

            if (flag) {
                break;
            }
        }
        System.out.println(graph[n][m]);
    }
}
