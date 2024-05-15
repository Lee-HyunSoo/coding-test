package baekjoon;

import java.util.Scanner;

/**
 * 연결 요소의 개수
 */
public class Q11724 {

    static int[][] graph;
    static boolean[] visit;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int m = scan.nextInt();

        graph = new int[n + 1][n + 1];
        visit = new boolean[n + 1]; // 1 2 3 4 5 6

        for (int i = 0; i < m; i++) {
            int u = scan.nextInt();
            int v = scan.nextInt();

            graph[u][v] = 1;
            graph[v][u] = 1;
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (!visit[i]) {
                dfs(n, i);
                answer++;
            }
        }
        System.out.println(answer);
    }

    public static void dfs(int n, int start) {
        visit[start] = true;
        for (int i = 1; i <= n; i++) {
            if (!visit[i] && graph[start][i] == 1) {
                dfs(n, i);
            }
        }
    }
}
