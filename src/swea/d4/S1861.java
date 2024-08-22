package swea.d4;

import java.util.*;

/**
 * 정사각형 방
 *
 * 1. 문제 정리
 * 	1-1. graph[row][col] 의 위치에 1 이상 n^2 이하의 수가 적혀있고, 숫자는 방마다 다르다.
 * 	1-2. 현재 있는 방에서 상하좌우에 있는 다른 방으로 이동할 수 있다.
 * 	1-3. 이동하려면 이동하려는 방이 존재해야하고, 이동하려는 방에 적힌 숫자가 현재 방의 수보다 정확히 1 커야한다.
 * 	1-4. 처음에 어떤 수가 적힌 방에 있어야 가장 많은 이동을 할 수 있을까?
 *
 * 2. 모든 좌표를 탐색한다.
 * 	2-1. 출발하는 방도 방의 개수에 포함한다.
 * 	2-2. (0,0) 부터 dfs 로 탐색하며 이동한다. 이동 후 더이상 진행이 불가능해 return 시 현재까지의 count 를 들고  return
 * 	2-3. 최대 값을 갱신한다.
 * 	2-4. 최대값 갱신 시 시작좌표가 기존 시작좌표보다 작으면 갱신한다.
 */
public class S1861 {

    static int n;
    static int[][] graph;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int start;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = scan.nextInt();
            graph = new int[n][n];
            start = 0;
            answer = 0;

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    graph[row][col] = scan.nextInt();
                }
            }

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    // 2-1. 출발하는 방도 방의 개수에 포함한다.
                    room(row, col, 1);
                }
            }
            System.out.println("#" + tc + " " + start + " " + answer);
        }
    }

    private static int room(int row, int col, int count) {
        for (int i = 0; i < 4; i++) {
            int nx = row + dx[i];
            int ny = col + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                continue;
            }
            if (graph[nx][ny] != graph[row][col] + 1) {
                continue;
            }

            // 2-2. (0,0) 부터 dfs 로 탐색하며 이동한다. 이동 후 더이상 진행이 불가능해 return 시 현재까지의 count 를 들고  return
            count = room(nx, ny, count + 1);

            if (count > answer) {
                // 2-3. 최대 값을 갱신한다.
                answer = count;
                start = graph[row][col];
            } else if (count == answer && start > graph[row][col]) {
                // 2-4. 최대값 갱신 시 시작좌표가 기존 시작좌표보다 작으면 갱신한다.
                start = graph[row][col];
            }
        }
        return count;
    }
}
