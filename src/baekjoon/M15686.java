package baekjoon;

import java.util.*;

/**
 * 치킨 배달
 *
 * 1. 문제 정리
 * 	1-1. 치킨거리 : |r1-r2| + |c1-c2|
 * 	1-2. 좌표는 1,1 부터 시작한다.
 * 	1-3. 0 : 빈칸, 1 : 집, 2 : 치킨집
 * 	1-4. 도시의 치킨거리 : 집 ~ 치킨집 사이의 모든 치킨거리의 합
 * 	1-5. m 개만큼 치킨집을 남겼을 때, 도시의 치킨거리의 최소값
 *
 * 2. 입력 시 0, 1 은 그대로 입력하고 치킨집의 좌표는 따로 배열에 저장한다.
 * 3. 치킨집들 중 m 개를 뽑는다. (조합)
 * 4. 0, 1 만 입력한 배열을 복사해와 뽑은 치킨집의 위치를 찍는다.
 * 5. 해당 배열로 bfs 를 통해 집 ~ 치킨집과의 최소 거리를 구한다.
 * 6. 구한 최소거리들을 더하고, 최소값으로 갱신한다.
 */
public class M15686 {

    static class Pair {
        int x, y;

        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m;
    static int[][] graph;
    static List<Pair> pairs;
    static Pair[] combi;
    static int[][] copy;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        graph = new int[n + 1][n + 1];
        pairs = new ArrayList<>();
        combi = new Pair[m];
        answer = Integer.MAX_VALUE;

        // 2. 입력 시 0, 1 은 그대로 입력하고 치킨집의 좌표는 따로 배열에 저장한다.
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                int input = scan.nextInt();
                if (input == 2) {
                    pairs.add(new Pair(row, col));
                } else {
                    graph[row][col] = input;
                }
            }
        }

        // 3. 치킨집들 중 m 개를 뽑는다. (조합)
        combination(0, 0);
        System.out.println(answer);
    }

    private static void combination(int level, int idx) {
        if (level == m) {
            // 4. 0, 1 만 입력한 배열을 복사해와 뽑은 치킨집의 위치를 찍는다.
            copy = copy();
            for (Pair p : combi) {
                copy[p.x][p.y] = 2;
            }

            int total = 0;
            for (int row = 1; row <= n; row++) {
                for (int col = 1; col <= n; col++) {
                    // 5. 해당 배열로 bfs 를 통해 집 ~ 치킨집과의 최소 거리를 구한다.
                    if (copy[row][col] == 1) {
                        Pair chick = bfs(row, col);
                        total += getDist(row, col, chick.x, chick.y);
                    }
                }
            }
            // 6. 구한 최소거리들을 더하고, 최소값으로 갱신한다.
            answer = Math.min(answer, total);
            return;
        }

        for (int i = idx; i < pairs.size(); i++) {
            combi[level] = pairs.get(i);
            combination(level + 1, i + 1);
        }
    }

    private static Pair bfs(int row, int col) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        boolean[][] visit = new boolean[n + 1][n + 1];
        Queue<Pair> q = new ArrayDeque<>();
        visit[row][col] = true;
        q.offer(new Pair(row, col));

        while(!q.isEmpty()) {
            int len = q.size();
            while(len-- > 0) {
                Pair p = q.poll();

                if (copy[p.x][p.y] == 2) {
                    return p;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];

                    if (nx < 1 || nx > n || ny < 1 || ny > n) {
                        continue;
                    }
                    if (visit[nx][ny]) {
                        continue;
                    }

                    visit[nx][ny] = true;
                    q.offer(new Pair(nx, ny));
                }
            }
        }
        return null;
    }

    private static int getDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static int[][] copy() {
        int[][] copy = new int[n + 1][n + 1];
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                copy[row][col] = graph[row][col];
            }
        }
        return copy;
    }
}
