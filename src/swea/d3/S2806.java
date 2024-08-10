package swea.d3;

import java.util.*;

/**
 * N-Queen
 *
 * dfs 로 한줄씩 접근, 해당 점에서 위로 3방향 탐색
 * 탐색 후 놓을 수 있는 지점이면 둠
 * 마지막 행까지 탐색, count 가 n 개면 answer + 1
 * 아니면 answer = 0 으로 초기화
 */
public class S2806 {

    static int n;
    static int[][] graph;
    static boolean[] visit;
    static int[] dRow = {-1, -1, -1}; // row
    static int[] dCol = {0, -1, 1}; // col
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = scan.nextInt();
            graph = new int[n][n];
            visit = new boolean[n];
            answer = 0;

            dfs(0, 0);
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void dfs(int row, int count) {
        // 마지막 행에 다다랐다면
        if (row == n) {
            // count 가 n 개면 answer + 1
            if (count == n) {
                answer++;
            }
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!visit[row] && graph[row][col] == 0) {
                // 현재 위치에서 위로 세방향 탐색
                boolean flag = false;
                for (int idx = 0; idx < 3; idx++) {
                    if (!find(row, col, dRow[idx], dCol[idx])) {
                        flag = true;
                        break;
                    }
                }
                // 모두 탐색했는데 flag 가 변하지 않았다면
                if (!flag) {
                    visit[row] = true;
                    graph[row][col] = 1;
                    dfs(row + 1, count + 1);
                    visit[row] = false;
                    graph[row][col] = 0;
                }
            }
        }
    }

    private static boolean find(int x, int y, int dRow, int dCol) {
        int nRow = x + dRow;
        int nCol = y + dCol;

        // 이동한 좌표가 그래프 내부라면
        while (0 <= nRow && nRow < n && 0 <= nCol && nCol < n) {
            // 이동한 곳이 이미 queen 이 놓여있는 곳이라면 false
            if (graph[nRow][nCol] == 1) {
                return false;
            }
            // 다음 좌표로 이동
            nRow += dRow;
            nCol += dCol;
        }
        return true;
    }
}
