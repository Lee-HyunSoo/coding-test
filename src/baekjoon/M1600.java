package baekjoon;

import java.util.*;

/**
 * 말이 되고픈 원숭이
 *
 * 1. 문제 정리
 * 	1-1. 말은 체스판의 나이트와 동일하게 움직인다.
 * 	1-2. 원숭이는 k 번만 말 처럼 움직일 수 있고, 이후에는 상하좌우로 움직인다.
 * 	1-3. 말은 장애물을 뛰어 넘을 수 있다.
 * 	1-4. (0,0) -> (n,n) 으로 갈 때 최소한의 동작으로 갈 수 있는 방법
 *
 * 2. bfs
 * 	2-1. 좌표, 남은 나이트 사용 횟수, 현재까지 움직인 횟수를 저장하는 클래스를 선언한다.
 * 	2-2. 상하좌우 델타배열, 나이트 움직임 델타배열을 선언한다.
 * 	2-3. 3차원 visit 배열을 사용한다.
 * 		2-3-1. visit[x][y][남은 나이트 사용횟수]
 * 	2-4. 상하좌우를 탐색하고, 방문체크를 한다.
 * 	2-5. 만약 나이트 사용 횟수가 남았다면, 8방향 탐색을 한다.
 * 		2-5-1. 갈 수 있는 각각의 좌표의 visit 에 남은 나이트의 개수도 표기
 * 		2-5-2. 이렇게 해야 해당 위치에 나이트를 사용하지 않고 도달하는 경우도 확인할 수 있다.
 *
 * 3. 목표지점에 도달하면 return
 *
 * 4. 도달하지 못하면 -1 을 return
 */
public class M1600 {

    // 2-1. 좌표, 남은 나이트 사용 횟수, 현재까지 움직인 횟수를 저장하는 클래스를 선언한다.
    static class Pair {
        int x, y, k, dist;
        Pair (int x, int y, int k, int dist) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.dist = dist;
        }
    }

    static Scanner scan = new Scanner(System.in);
    static int k, w, h;
    static int[][] graph;
    static boolean[][][] visit;

    // 2-2. 상하좌우 델타배열, 나이트 움직임 델타배열을 선언한다.
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] dxHorse = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dyHorse = {-1, 1, -2, 2, -2, 2, -1, 1};

    public static void main(String[] args) {
        init();
        System.out.println(move());
    }

    private static void init() {
        k = scan.nextInt();
        w = scan.nextInt();
        h = scan.nextInt();
        graph = new int[h][w];
        // 2-3. 3차원 visit 배열을 사용한다.
        // 2-3-1. visit[x][y][남은 나이트 사용횟수]
        visit = new boolean[h][w][k + 1];

        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                graph[row][col] = scan.nextInt();
            }
        }
    }

    private static int move() {
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(0, 0, k, 0));
        visit[0][0][k] = true;

        while(!q.isEmpty()) {
            int len = q.size();
            while(len-- > 0) {
                Pair p = q.poll();
                // 3. 목표지점에 도달하면 return
                if(p.x == h - 1 && p.y == w - 1) {
                    return p.dist;
                }
                // 2-4. 상하좌우를 탐색하고, 방문체크를 한다.
                for (int d = 0; d < 4; d++) {
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];

                    if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
                        continue;
                    }
                    if (visit[nx][ny][p.k] || graph[nx][ny] == 1) {
                        continue;
                    }
                    visit[nx][ny][p.k] = true;
                    q.offer(new Pair(nx, ny, p.k, p.dist + 1));
                }
                // 2-5. 만약 나이트 사용 횟수가 남았다면, 8방향 탐색을 한다.
                if (p.k > 0) {
                    for (int d = 0; d < 8; d++) {
                        int nx = p.x + dxHorse[d];
                        int ny = p.y + dyHorse[d];

                        if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
                            continue;
                        }
                        if (visit[nx][ny][p.k - 1] || graph[nx][ny] == 1) {
                            continue;
                        }
                        // 2-5-1. 갈 수 있는 각각의 좌표의 visit 에 남은 나이트의 개수도 표기
                        // 2-5-2. 이렇게 해야 해당 위치에 나이트를 사용하지 않고 도달하는 경우도 확인할 수 있다.
                        visit[nx][ny][p.k - 1] = true;
                        q.offer(new Pair(nx, ny, p.k - 1, p.dist + 1));
                    }
                }
            }
        }
        // 4. 도달하지 못하면 -1 을 return
        return -1;
    }
}
