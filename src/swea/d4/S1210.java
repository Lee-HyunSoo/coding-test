package swea.d4;

import java.util.*;

/**
 * [S/W 문제해결 기본] 2일차 - Ladder1
 *
 * row 가 0 인 col 들이 시작점
 * 시작점 -> DFS -> (n, n) 으로 갈 수 있는 col 값
 *  -- 위로 갈 필요 없음 : 델타 배열은 3개만
 *  -- 좌우로 이동가능하면 아래로 내려가는 것 보다 좌우가 우선순위
 *  -- 아래로 내려가는 것 : DFS
 *  -- 좌우 탐색 -> 사다리게임은 양쪽 다 열린 경우가 없음
 */
public class S1210 {

    static int[][] graph;
    static boolean[][] visit;
    static int answer;
    static int[] dy = {0, 0, 1};
    static int[] dx = {-1, 1, 0};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        for (int tc = 1; tc <= 10; tc++) {
            int t = scan.nextInt();
            graph = new int[100][100];
            visit = new boolean[100][100];
            answer = -1;

            for (int row = 0; row < 100; row++) {
                for (int col = 0; col < 100; col++) {
                    graph[row][col] = scan.nextInt();
                }
            }

            for (int col = 0; col < 100; col++) {
                if (graph[0][col] != 0) {
                    ladder(0, col, col);

                    if (answer != -1) {
                        break;
                    }
                }
            }
            System.out.println("#" + t + " " + answer);
        }
    }

    private static void ladder(int row, int col, int start) {
        // 도착한 지점이 2라면 저장 후 return
        if (graph[row][col] == 2) {
            answer = start;
            return;
        }

        for (int idx = 0; idx < 3; idx++) {
            int ny = row + dy[idx];
            int nx = col + dx[idx];

            // 이동 가능한 지점이라면, 재귀
            // 사다리는 좌 우 동시 이동 가능한 지점이 없기 때문에 좌 -> 우 -> 하 순으로 탐색
            if (0 <= nx && nx < 100 && 0 <= ny && ny < 100) {
                if (!visit[ny][nx] && graph[ny][nx] != 0) {
                    visit[ny][nx] = true;
                    ladder(ny, nx, start);
                    visit[ny][nx] = false;
                    return;
                }
            }
        }
    }
}
