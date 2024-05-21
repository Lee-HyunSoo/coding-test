package swea.d3;

import java.util.*;

/**
 * 정사각형 판정
 */
public class S13732 {

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static int[][] graph;
    static boolean[][] visit;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            // . : 빈칸 # : 막힘
            n = scan.nextInt();
            scan.nextLine();

            graph = new int[n][n];
            visit = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                String str = scan.nextLine();
                for (int j = 0; j < n; j++) {
                    if (str.charAt(j) == '.') {
                        graph[i][j] = 0;
                    } else if (str.charAt(j) == '#'){
                        graph[i][j] = 1;
                    }
                }
            }

            int cnt = 0;
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visit[i][j] && graph[i][j] == 1) {
                        cnt++;
                        if (!flag && bfs(i, j)) {
                            flag = true;
                        }
                    }
                }
            }

            String answer = "no";
            if (cnt == 1 && flag) {
                answer = "yes";
            }
            System.out.println("#" + c + " " + answer);
        }
    }

    public static boolean bfs(int x, int y) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(x, y));
        visit[x][y] = true;

        int[] dx = {0, -1, 1, 0};
        int[] dy = {1, 0, 0, -1};
        int cnt = 0;
        while (!q.isEmpty()) {
            Pair p = q.poll();
            for (int j = 0; j < 4; j++) {
                int nx = p.x + dx[j];
                int ny = p.y + dy[j];

                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (!visit[nx][ny] && graph[nx][ny] == 1) {
                        visit[nx][ny] = true;
                        q.offer(new Pair(nx, ny));
                    }
                }
            }
            cnt++;
        }

        for (int i = 1; i <= 20; i++) {
            if (Math.sqrt(cnt) == i) {
                return true;
            }
        }
        return false;
    }
}

/*
1
4
#...
..##
.###
..##
-> no
 */
