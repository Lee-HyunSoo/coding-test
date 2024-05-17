package swea.d3;

import java.util.*;

/**
 * 격자판 칠하기
 */
public class S14413 {

    static char[][] graph;
    static int n, m;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            n = scan.nextInt();
            m = scan.nextInt();
            scan.nextLine();

            // # : 검정 . : 흰색
            graph = new char[n][m];
            for (int i = 0; i < n; i++) {
                String str = scan.nextLine();
                for (int j = 0; j < m; j++) {
                    graph[i][j] = str.charAt(j);
                }
            }

            boolean flag = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (graph[i][j] == '#') {
                        flag = checked(i, j, '#', '.');
                    } else if (graph[i][j] == '.') {
                        flag = checked(i, j, '.', '#');
                    }

                    if (flag) {
                        break;
                    }
                }

                if (flag) {
                    break;
                }
            }
            if (flag) {
                System.out.println("#" + c + " impossible");
            } else {
                System.out.println("#" + c + " possible");
            }
        }
    }

    public static boolean checked(int i, int j, char c1, char c2) {
        int num = j % 2;
        for (int x = 0; x < m; x++) {
            if ((x % 2 != num && graph[i][x] == c1) || (x % 2 == num && graph[i][x] == c2)) {
                return true;
            }
            if (x % 2 == num && graph[i][x] == '?') {
                graph[i][x] = c1;
            }
        }
        num = i % 2;
        for (int y = 0; y < n; y++) {
            if ((y % 2 != num && graph[y][j] == c1) || (y % 2 == num && graph[y][j] == c2)) {
                return true;
            }
            if (y % 2 == num && graph[y][j] == '?') {
                graph[y][j] = c1;
            }
        }
        return false;
    }
}
