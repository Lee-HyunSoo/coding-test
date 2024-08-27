package swea.swcompetency;

import java.util.*;

/**
 * 디저트 카페
 *
 * 1. 문제 정리
 * 	1-1. n*n 모양 배열, 팔고있는 디저트의 종류를 숫자로 구분, 대각선 이동
 * 	1-2. 대각선으로 이동해서 사각형 모양을 그리며 출발한 지점으로 돌아와야 한다.
 * 	1-3. 같은 종류의 디저트는 먹을 수 없다.
 * 	1-4. 하나의 카페에서 디저트를 먹을 수도 없다.
 * 	1-5. 같은길을 되돌아갈 수 없다.
 *
 * 2. 종료 조건
 *  2-1. 우상향 상태이고, 시작좌표면 최대값 갱신 후 return
 * 	2-2. 좌표가 그래프 밖이면 return
 * 	2-3. 이미 방문한 점이면 return
 * 	2-4. 이미 먹은 디저트면 return
 *
 * 3. 자신이 왔던 경로와, 다음 경로만 탐색한다.
 *  3-1. 방향 정보를 같이 파라미터로 넘겨, 왔던 방향이나 다음방향만 탐색
 *  3-2. 이 때 우상향 방향이면 그 다음이 없기 때문에 우상향 이전 방향들만 다음 방향을 탐색한다.
 */
public class S2105 {

    static int n;
    static int[][] graph;
    static boolean[][] visit;
    static Set<Integer> set;
    static int[] dx = {1, 1, -1, -1};
    static int[] dy = {1, -1, -1, 1};
    static int answer;
    static int startX, startY;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = scan.nextInt();
            graph = new int[n][n];
            visit = new boolean[n][n];
            set = new HashSet<>();
            answer = 0;
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    graph[row][col] = scan.nextInt();
                }
            }

            for (int row = 0; row < n - 2; row++) {
                for (int col = 1; col < n - 1; col++) {
                    startX = row; startY = col;
                    dessert(row, col, 0, 0);
                }
            }
            answer = (answer == 0) ? -1 : answer;
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void dessert(int x, int y, int prev, int count) {
        // 2-1. 우상향 상태이고, 시작좌표면 최대값 갱신 후 return
        if (prev == 3 && x == startX && y == startY) {
            answer = Math.max(answer, count);
            return;
        }
        // 2-2. 좌표가 그래프 밖이면 return
        if (x < 0 || x >= n || y < 0 || y >= n) {
            return;
        }
        // 2-3. 이미 방문한 점이면 return
        if (visit[x][y]) {
            return;
        }
        // 2-4. 이미 먹은 디저트면 return
        if (set.contains(graph[x][y])) {
            return;
        }

        visit[x][y] = true;
        set.add(graph[x][y]);
        // 3-1. 방향 정보를 같이 파라미터로 넘겨, 왔던 방향이나 다음방향만 탐색
        dessert(x + dx[prev], y + dy[prev], prev, count + 1);
        // 3-2. 이 때 우상향 방향이면 그 다음이 없기 때문에 우상향 이전 방향들만 다음 방향을 탐색한다.
        if (prev < 3) {
            dessert(x + dx[prev + 1], y + dy[prev + 1], prev + 1, count + 1);
        }
        visit[x][y] = false;
        set.remove(graph[x][y]);
    }
}
