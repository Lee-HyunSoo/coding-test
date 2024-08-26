package swea.d4;

import java.util.*;

/**
 * 파핑파핑 지뢰찾기
 *
 * 1. 문제 정리
 * 	1-1. 지뢰가 없는 칸 클릭 시 8방향에 대해 지뢰가 몇개 있는지 표시
 * 	1-2. 해당 칸이 0이라면, 주변 8칸 각각에 대해 8방향 탐색
 * 	1-3. 최소 몇번의 클릭으로 . 을 다 없앨 수 있는가?
 *
 * 2. bfs 를 사용해 . 인 칸을 채워나간다.
 * 	2-1. 이 때, 주변이 모두 0인 칸이 한번 클릭에 가장 많이 . 을 없앨 수 있다.
 * 	2-2. 때문에 우선 주변이 모두 0 인 것을 찾아 bfs 를 돌린다.
 * 	2-3. 이 후 남은 것을 bfs 로 다시 체크해준다.
 */
public class S1868 {

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static char[][] graph;
    static boolean[][] visit;
    static List<Pair> adj;
    static int[] dx = {0, 0, -1, 1, -1, -1, 1, 1};
    static int[] dy = {-1, 1, 0, 0, -1, 1, -1, 1};
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = scan.nextInt();
            scan.nextLine();
            graph = new char[n][n];
            visit = new boolean[n][n];
            answer = 0;
            for (int row = 0; row < n; row++) {
                String input = scan.nextLine();
                for (int col = 0; col < n; col++) {
                    graph[row][col] = input.charAt(col);
                }
            }

            // 2-1. 이 때, 주변이 모두 0인 칸이 한번 클릭에 가장 많이 . 을 없앨 수 있다.
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (!visit[row][col] && graph[row][col] != '*') {
                        // 2-2. 때문에 우선 주변이 모두 0 인 것을 찾아 bfs 를 돌린다.
                        int count = countBomb(new Pair(row, col));
                        if (count == 0) {
                            click(new Pair(row, col));
                            answer++;
                        }
                    }
                }
            }

            // 2-3. 이 후 남은 것을 bfs 로 다시 체크해준다.
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (!visit[row][col] && graph[row][col] == '.') {
                        click(new Pair(row, col));
                        answer++;
                    }
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void click(Pair start) {
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(start);
        visit[start.x][start.y] = true;

        while (!q.isEmpty()) {
            int len = q.size();

            for (int i = 0; i < len; i++) {
                Pair p = q.poll();
                int count = countBomb(p);

                if (count == 0) {
                    for (int j = 0; j < 8; j++) {
                        int nx = p.x + dx[j];
                        int ny = p.y + dy[j];

                        if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                            if (!visit[nx][ny] && graph[nx][ny] != '*') {
                                visit[nx][ny] = true;
                                q.offer(new Pair(nx, ny));
                            }
                        }
                    }
                }
            }
        }
    }

    private static int countBomb(Pair p) {
        int count = 0;
        for (int j = 0; j < 8; j++) {
            int nx = p.x + dx[j];
            int ny = p.y + dy[j];

            if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                if (graph[nx][ny] == '*') {
                    count++;
                }
            }
        }
        return count;
    }
}
