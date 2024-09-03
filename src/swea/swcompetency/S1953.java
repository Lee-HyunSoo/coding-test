package swea.swcompetency;

import java.util.*;

/**
 * 탈주범 검거
 *
 * 1. 문제 정리
 * 	1-1. 탈주범은 시간 당 1의 거리를 움직일 수 있다. (상하좌우)
 * 	1-2. 이 때 파이프의 위치에 따라 갈 수 있는 장소가 한정된다.
 * 	1-3. 1: 상하좌우, 2: 상하, 3: 좌우, 4: 상우, 5: 하우, 6: 하좌, 7: 상좌
 *
 * 2. BFS
 * 	2-1. 상, 하, 좌, 우  한 칸을 탐색하는 메서드를 만든다.
 * 	2-2. UDLR: 상하좌우 탐색
 * 	2-3. UD: 상하 탐색
 * 	2-4. LR: 좌우 탐색
 * 	2-5. UR: 상우 탐색
 * 	2-6. DR: 하우 탐색
 * 	2-7. DL: 하좌 탐색
 * 	2-8. UL: 상좌 탐색
 *
 * 3. 시작점을 포함하기 위해 첫 count 를 1로 설정한다.
 * 4. 시작점을 시간에 포함하기 위해 time 을 1부터 시작한다.
 */
public class S1953 {

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Scanner scan = new Scanner(System.in);
    static int n, m, r, c, l;
    static int[][] graph;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int totalCount;

    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;

    static final int BLANK = 0;
    static final int UDLR = 1;
    static final int UD = 2;
    static final int LR = 3;
    static final int UR = 4;
    static final int DR = 5;
    static final int DL = 6;
    static final int UL = 7;

    public static void main(String[] args) {
        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            init();
            move();
            System.out.println("#" + tc + " " + totalCount);
        }
    }

    private static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        r = scan.nextInt();
        c = scan.nextInt();
        l = scan.nextInt();
        graph = new int[n][m];
        visit = new boolean[n][m];
        // 3. 시작점을 포함하기 위해 첫 count 를 1로 설정한다.
        totalCount = 1;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                graph[row][col] = scan.nextInt();
            }
        }
    }

    private static void move() {
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(r, c));
        visit[r][c] = true;

        // 4. 시작점을 시간에 포함하기 위해 time 을 1부터 시작한다.
        int time = 1;
        while(!q.isEmpty()) {
            int len = q.size();
            if (time == l) {
                break;
            }

            while(len-- > 0) {
                Pair p = q.poll();

                if (graph[p.x][p.y] == UDLR) {
                    // 2-2. UDLR: 상하좌우 탐색
                    up(q, p.x, p.y);
                    down(q, p.x, p.y);
                    left(q, p.x, p.y);
                    right(q, p.x, p.y);
                } else if (graph[p.x][p.y] == UD) {
                    // 2-3. UD: 상하 탐색
                    up(q, p.x, p.y);
                    down(q, p.x, p.y);
                } else if (graph[p.x][p.y] == LR) {
                    // 2-4. LR: 좌우 탐색
                    left(q, p.x, p.y);
                    right(q, p.x, p.y);
                } else if (graph[p.x][p.y] == UR) {
                    // 2-5. UR: 상우 탐색
                    up(q, p.x, p.y);
                    right(q, p.x, p.y);
                } else if (graph[p.x][p.y] == DR) {
                    // 2-6. DR: 하우 탐색
                    down(q, p.x, p.y);
                    right(q, p.x, p.y);
                } else if (graph[p.x][p.y] == DL) {
                    // 2-7. DL: 하좌 탐색
                    down(q, p.x, p.y);
                    left(q, p.x, p.y);
                } else if (graph[p.x][p.y] == UL) {
                    // 2-8. UL: 상좌 탐색
                    up(q, p.x, p.y);
                    left(q, p.x, p.y);
                }
            }
            time++;
        }
    }

    // 2-1. 상, 하, 좌, 우  한 칸을 탐색하는 메서드를 만든다.
    private static void up(Queue<Pair> q, int x, int y) {
        int nx = x + dx[UP];
        int ny = y + dy[UP];

        if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
            return;
        }
        if (visit[nx][ny]) {
            return;
        }
        if (graph[nx][ny] == BLANK
                || graph[nx][ny] == LR
                || graph[nx][ny] == UR
                || graph[nx][ny] == UL) {
            return;
        }
        enQueue(q, nx, ny);
    }

    private static void down(Queue<Pair> q, int x, int y) {
        int nx = x + dx[DOWN];
        int ny = y + dy[DOWN];

        if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
            return;
        }
        if (visit[nx][ny]) {
            return;
        }
        if (graph[nx][ny] == BLANK
                || graph[nx][ny] == LR
                || graph[nx][ny] == DR
                || graph[nx][ny] == DL) {
            return;
        }
        enQueue(q, nx, ny);
    }

    private static void left(Queue<Pair> q, int x, int y) {
        int nx = x + dx[LEFT];
        int ny = y + dy[LEFT];

        if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
            return;
        }
        if (visit[nx][ny]) {
            return;
        }
        if (graph[nx][ny] == BLANK
                || graph[nx][ny] == UD
                || graph[nx][ny] == DL
                || graph[nx][ny] == UL) {
            return;
        }
        enQueue(q, nx, ny);
    }

    private static void right(Queue<Pair> q, int x, int y) {
        int nx = x + dx[RIGHT];
        int ny = y + dy[RIGHT];

        if (nx < 0 || nx > n || ny < 0 || ny >= m) {
            return;
        }
        if (visit[nx][ny]) {
            return;
        }
        if (graph[nx][ny] == BLANK
                || graph[nx][ny] == UD
                || graph[nx][ny] == UR
                || graph[nx][ny] == DR) {
            return;
        }
        enQueue(q, nx, ny);
    }

    private static void enQueue(Queue<Pair> q, int x, int y) {
        visit[x][y] = true;
        q.offer(new Pair(x, y));
        totalCount++;
    }
}
