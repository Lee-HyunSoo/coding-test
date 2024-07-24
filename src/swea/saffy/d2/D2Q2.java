package swea.saffy.d2;

import java.util.Scanner;

/**
 * 숫자 배열 회전
 */
public class D2Q2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();

            int[][] graph = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = scan.nextInt();
                }
            }

            String[][] answer = new String[n][3];
            for (int k = 0; k < 3; k++) {
                graph = calc(n, graph);
                for (int i = 0; i < n; i++) {
                    String tmp = "";
                    for (int j = 0; j < n; j++) {
                        tmp += String.valueOf(graph[i][j]);
                    }
                    answer[i][k] = tmp;
                }
            }

            System.out.println("#" + c);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(answer[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    static int[][] calc(int n, int[][] graph) {
        int[][] result = new int[n][n];
        for (int x = 0; x < n; x++) {
            for (int y = n - 1; y >= 0; y--) {
                result[x][y] = graph[n - 1 - y][x];
            }
        }
        return result;
    }
}

/*
(0,0) -> (0,2)
(1,0) -> (0,1)
(2,0) -> (0,0)
(0,1) -> (1,2)
(1,1) -> (1,1)
(2,1) -> (1,0)
(0,2) -> (2,2)
(1,2) -> (2,1)
(2,2) -> (2,0)
 */