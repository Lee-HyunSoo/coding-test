package swea.saffy.d4;

import java.util.Scanner;

/**
 * 수영대회 결승전
 */
public class D4Q1 {

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] graph;
    static boolean[][] visit;
    static int n, time;
    static int a, b, c, d;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int cs = 1; cs <= t; cs++) {
            n = scan.nextInt();
            time = 0;
            graph = new int[n][n];
            visit = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = scan.nextInt();
                }
            }
            a = scan.nextInt();
            b = scan.nextInt();
            c = scan.nextInt();
            d = scan.nextInt();

            dfs();
        }
    }

    static void dfs() {

    }
}

/*
0 1 (2) 3 4 (5) 6 7 (8) 9 10 (11) 12 13 (14)
3 6 9 12 15 18 21

 */