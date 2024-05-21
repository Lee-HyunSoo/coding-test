package swea.d3;

import java.util.*;

/**
 * 오목 판정
 */
public class S11315 {

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static int[][] graph;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            n = scan.nextInt();
            scan.nextLine();
            graph = new int[n][n];

            for (int i = 0; i < n; i++) {
                String str = scan.nextLine();
                for (int j = 0; j < str.length(); j++) {
                    if (str.charAt(j) == '.') {
                        graph[i][j] = 0;
                    } else {
                        graph[i][j] = 1;
                    }
                }
            }

            String answer = "NO";
            for (int i = 0; i < n; i++) {
                boolean flag = false;
                for (int j = 0; j < n; j++) {
                    if (graph[i][j] == 1) {
                        if (count(i, j)) {
                            answer = "YES";
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag) {
                    break;
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }

    public static boolean count(int x, int y) {
        if (bfs(x, y, new int[]{0, 0}, new int[]{1, -1})) {
            return true;
        }
        if (bfs(x, y, new int[]{1, -1}, new int[]{0, 0})) {
            return true;
        }
        if (bfs(x, y, new int[]{1, -1}, new int[]{1, -1})) {
            return true;
        }
        if (bfs(x, y, new int[]{1, -1}, new int[]{-1, 1})) {
            return true;
        }
        return false;
    }

    public static boolean bfs(int x, int y, int[] dx, int[] dy) {
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visit = new boolean[n][n];
        q.offer(new Pair(x, y));
        visit[x][y] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                Pair p = q.poll();

                for (int j = 0; j < 2; j++) {
                    int nx = p.x + dx[j];
                    int ny = p.y + dy[j];

                    if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                        if (!visit[nx][ny] && graph[nx][ny] == 1) {
                            visit[nx][ny] = true;
                            q.offer(new Pair(nx, ny));
                        }
                    }
                }
            }
            cnt++;
            if (cnt == 5) break;
        }
        return cnt >= 5;
    }
}
