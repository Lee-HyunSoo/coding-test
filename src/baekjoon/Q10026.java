package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 적록색약
 */
public class Q10026 {

    static int n;
    static boolean[][] visit;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        n = scan.nextInt();
        scan.nextLine();

        int[][] graph1 = new int[n][n];
        int[][] graph2 = new int[n][n];
        for (int i = 0; i < n; i++) {
            String input = scan.nextLine();
            // r : 1 g : 2 b : 3
            for (int j = 0; j < n; j++) {
                if (input.charAt(j) == 'R') {
                    graph1[i][j] = 1;
                    graph2[i][j] = 1;
                } else if (input.charAt(j) == 'G') {
                    graph1[i][j] = 2;
                    graph2[i][j] = 1;
                } else if (input.charAt(j) == 'B') {
                    graph1[i][j] = 3;
                    graph2[i][j] = 3;
                }
            }
        }

        visit = new boolean[n][n];
        int answer1 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j]) {
                    bfs(i, j, graph1);
                    answer1++;
                }
            }
        }

        visit = new boolean[n][n];
        int answer2 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j]) {
                    bfs(i, j, graph2);
                    answer2++;
                }
            }
        }
        System.out.println(answer1 + " " + answer2);
    }

    public static void bfs(int x, int y, int[][] graph) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(x, y));
        visit[x][y] = true;
        int rgb = graph[x][y];

        while (!q.isEmpty()) {
            int len = q.size();

            for (int i = 0; i < len; i++) {
                Pair p = q.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = p.x + dx[j];
                    int ny = p.y + dy[j];

                    if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                        if (!visit[nx][ny] && graph[nx][ny] == rgb) {
                            visit[nx][ny] = true;
                            q.offer(new Pair(nx, ny));
                        }
                    }
                }
            }
        }
    }
}
