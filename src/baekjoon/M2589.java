package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 보물섬
 */
public class M2589 {

    static int n, m;
    static int[][] graph;
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
        m = scan.nextInt();
        scan.nextLine();

        graph = new int[n][m];

        for (int i = 0; i < n; i++) {
            String input = scan.nextLine();
            for (int j = 0; j < m; j++) {
                if (input.charAt(j) == 'L') {
                    graph[i][j] = 1; // 육지
                } else if (input.charAt(j) == 'W') {
                    graph[i][j] = 0; // 바다
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visit = new boolean[n][m];
                if (graph[i][j] == 1) {
                    answer = Math.max(answer, bfs(i, j));
                }
            }
        }
        System.out.println(answer);
    }

    public static int bfs(int x, int y) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(x, y));
        visit[x][y] = true;

        int answer = -1; // 시작점부터 큐에서 뽑기 때문, 시작점을 0으로 만들기 위해
        while (!q.isEmpty()) {
            int len = q.size();

            for (int i = 0; i < len; i++) {
                Pair p = q.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = p.x + dx[j];
                    int ny = p.y + dy[j];

                    if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                        if (!visit[nx][ny] && graph[nx][ny] == 1) {
                            visit[nx][ny] = true;
                            q.offer(new Pair(nx, ny));
                        }
                    }
                }
            }
            answer++;
        }
        return answer;
    }
}
