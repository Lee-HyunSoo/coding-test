package baekjoon;

import java.util.*;

/**
 * 캐슬 디펜스
 *
 * 궁수는 거리 d 이하의 적 중 가장 가까운적을 쏜다. 그런 적이 여러명일 때는 가장 왼쪽의 적을 쏜다.
 * 조합을 돌려서 궁수의 col 좌표를 특정
 * x = 현재 row ~ row - d + 1
 * y = 궁수의 col 좌표까지 (x, y) 를 구해서 (row+1, 궁수 col) 과의 거리 잼
 * 그 중 거리가 더 작으면 -> 좌표 값 갱신
 * 최소와 같은 값이 나올땐 좌표 갱신 x -> 가장 왼쪽의 적이 아니기 때문
 */
public class M17135 {

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m, d;
    static int[][] graph;
    static int[] dx = {0, -1, 0};
    static int[] dy = {-1, 0, 1};
    static int[] combi;

    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        n = scan.nextInt();
        m = scan.nextInt();
        d = scan.nextInt();
        graph = new int[n][m];
        combi = new int[3];
        answer = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                graph[row][col] = scan.nextInt();
            }
        }

        combination(0, 0);
        System.out.println(answer);
    }

    private static void combination(int level, int idx) {
        if (level == 3) {
            defense();
            return;
        }

        for (int i = idx; i < m; i++) {
            combi[level] = i;
            combination(level + 1, i + 1);
        }
    }

    private static void defense() {
        int[][] copy = copy();
        int count = 0;
        for (int row = n - 1; row >= 0; row--) {
            for (int col : combi) {
                Pair result = bfs(new Pair(row, col), copy);
                if (result != null) {
                    copy[result.x][result.y] = 0;
                    System.out.println(result.x + " " + result.y);
                    count++;
                }
            }
        }
        System.out.println();
        answer = Math.max(answer, count);
    }

    private static Pair bfs(Pair start, int[][] copy) {
        if (copy[start.x][start.y] == 1) {
            return start;
        }

        Queue<Pair> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[n][m];
        q.offer(new Pair(start.x, start.y));
        visit[start.x][start.y] = true;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Pair p = q.poll();
                for (int i = 0; i < 3; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        continue;
                    }
                    if (visit[nx][ny]) {
                        continue;
                    }

                    int dist = getDist(p.x, p.y, nx, ny);
                    if (dist <= d) {
                        if (copy[nx][ny] == 1) {
                            return new Pair(nx, ny);
                        } else {
                            visit[nx][ny] = true;
                            q.offer(new Pair(nx, ny));
                        }
                    }
                }
            }
        }
        return null;
    }

    private static int getDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static int[][] copy() {
        int[][] copy = new int[n][m];
        for (int idx = 0; idx < n; idx++) {
            copy[idx] = graph[idx].clone();
        }
        return copy;
    }
}
