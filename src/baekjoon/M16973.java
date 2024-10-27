package baekjoon;

import java.util.*;

/**
 * 직사각형 탈출
 *
 * 1. 문제 정리
 *  1-1. 크기가 N×M인 격자판에 크기가 H×W인 직사각형이 놓여 있다.
 *  1-2. 격자판의 가장 왼쪽 위 칸은 (1, 1), 가장 오른쪽 아래 칸은 (N, M)이다.
 *  1-3. 직사각형의 가장 왼쪽 위칸은 (Sr, Sc)에 있을 때,
 *  1-4. 이 직사각형의 가장 왼쪽 위칸을 (Fr, Fc)로 이동시키기 위한 최소 이동 횟수를 구해보자.
 *
 * 2. BFS 로 블럭을 이동시킨다.
 * 3. 이 때 시작 좌표를 주축으로 사용한다.
 * 4. 시작좌표 ~ 시작좌표 + h - 1 / 시작좌표 + 시작좌표 + w - 1 이 사각형의 범위이다.
 * 	4-1. 이 범위가 보드 밖으로 나가면 continue
 * 5. 시작 좌표를 기준으로 방문한 사각형이면 continue
 * 6. 사각형 범위 중 벽이 있으면 continue
 * 	6-1. 이를 위해 벽 좌표를 처음에 등록해놓고, 사각형 범위안에 있는지 탐색한다.
 */
public class M16973 {

    static class Pair {
        int x, y;
        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Scanner scan = new Scanner(System.in);
    static int n, m;
    static int[][] board;
    static List<Pair> walls;
    static int h, w, startX, startY, endX, endY;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};


    public static void main(String[] args) {
        init();
        // 2. BFS 로 블럭을 이동시킨다.
        System.out.println(moveBlock());
    }

    private static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        board = new int[n + 1][m + 1];
        walls = new ArrayList<>();
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= m; col++) {
                board[row][col] = scan.nextInt();
                if (board[row][col] == 1) {
                    walls.add(new Pair(row, col));
                }
            }
        }
        h = scan.nextInt();
        w = scan.nextInt();
        startX = scan.nextInt();
        startY = scan.nextInt();
        endX = scan.nextInt();
        endY = scan.nextInt();
    }

    private static int moveBlock() {
        Queue<Pair> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[n + 1][m + 1];
        // 3. 이 때 시작 좌표를 주축으로 사용한다.
        q.offer(new Pair(startX, startY));
        visit[startX][startY] = true;

        int count = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0) {
                Pair p = q.poll();

                // 목표지점 도착 시 return
                if (p.x == endX && p.y == endY) {
                    return count;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];

                    // 4. 시작좌표 ~ 시작좌표 + h - 1 / 시작좌표 + 시작좌표 + w - 1 이 사각형의 범위이다.
                    // 4-1. 이 범위가 보드 밖으로 나가면 continue
                    if (nx < 1 || nx + h - 1 > n || ny < 1 || ny + w - 1 > m) {
                        continue;
                    }

                    // 5. 시작 좌표를 기준으로 방문한 사각형이면 continue
                    if (visit[nx][ny]) {
                        continue;
                    }

                    // 6. 사각형 범위 중 벽이 있으면 continue
                    if (isWall(nx, ny)) {
                        continue;
                    }
                    visit[nx][ny] = true;
                    q.offer(new Pair(nx, ny));
                }
            }
            count++;
        }
        return -1;
    }

    private static boolean isWall(int nx, int ny) {
        // 6-1. 이를 위해 벽 좌표를 처음에 등록해놓고, 사각형 범위안에 있는지 탐색한다.
        for (Pair wall : walls) {
            // 사각형 범위
            if (nx <= wall.x && wall.x <= nx + h - 1
                    &&  ny <= wall.y && wall.y <= ny + w - 1) {
                return true;
            }
        }
        return false;
    }
}
