package swea.saffy.d2;

import java.util.*;

/**
 * 파리퇴치3
 */
public class D2Q1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            int[][] graph = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = scan.nextInt();
                }
            }

            int[] dx1 = {-1, 0, 0, 1};
            int[] dy1 = {0, -1, 1, 0};
            int[] dx2 = {1, 1, -1, -1};
            int[] dy2 = {-1, 1, -1, 1};

            int answer = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int sum1 = graph[i][j];
                    int sum2 = graph[i][j];
                    for (int g = 1; g < m; g++) {
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx1[k] * g;
                            int ny = j + dy1[k] * g;
                            if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                                sum1 += graph[nx][ny];
                            }
                            nx = i + dx2[k] * g;
                            ny = j + dy2[k] * g;
                            if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                                sum2 += graph[nx][ny];
                            }
                        }
                    }
                    int result = Math.max(sum1, sum2);
                    answer = Math.max(answer, result);
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
