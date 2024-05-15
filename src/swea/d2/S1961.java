package swea.d2;

import java.util.Scanner;

/**
 * 숫자 배열 회전
 */
public class S1961 {

    static String[][] answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();

        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            answer = new String[n + 1][4];

            int[][] graph = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    graph[i][j] = scan.nextInt();
                }
            }

            int row = 1;
            int[][] rotation1 = change(n, graph);
            calc(n, row++, rotation1);

            int[][] rotation2 = change(n, rotation1);
            calc(n, row++, rotation2);

            int[][] rotation3 = change(n, rotation2);
            calc(n, row, rotation3);

            System.out.println("#" + c);
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= 3; j++) {
                    System.out.print(answer[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    public static int[][] change(int n, int[][] graph) {
        int[][] rotation = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = n; j >= 1; j--) {
                rotation[i][j] = graph[n - j + 1][i];
            }
        }
        return rotation;
    }

    public static void calc(int n, int row, int[][] rotation) {
        for (int i = 1; i <= n; i++) {
            String result = "";
            for (int j = 1; j <= n; j++) {
                result += String.valueOf(rotation[i][j]);
            }
            answer[i][row] = result;
        }
    }
}
