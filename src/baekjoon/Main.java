package baekjoon;

import java.util.*;

/**
 * ABCDE
 *
 *
 */
public class Main {

    static int n, m;
    static int[][] graph;
    static boolean[] visit;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        graph = new int[n][n];
        visit = new boolean[n];
        answer = 0;

        for (int idx = 0; idx < m; idx++) {
            int from = scan.nextInt();
            int to = scan.nextInt();
            graph[from][to] = 1;
            graph[to][from] = 1;
        }


        visit[0] = true;
        for (int col = 0; col < n; col++) {
            if (!visit[col] && graph[0][col] == 1) {
                dfs(col, 0);
            }
        }
        System.out.println(answer);
    }

    private static void dfs(int row, int to) {
        if (row == to) {
            answer = 1;
            return;
        }

        visit[row] = true;
        for (int col = 0; col < n; col++) {
            if (graph[row][col] == 1) {
                dfs(col, to);
            }
        }
        visit[row] = false;
    }
}
