package swea.d2;

import java.util.Scanner;

/**
 * 달팽이 숫자
 */
public class S1954 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();

            int[][] graph = new int[n][n];
            int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
            int[] dy = {1, 0, -1, 0};

            int x = 0, y = 0, idx = 0, num = 1;
            graph[x][y] = num;
            while (true) {
                boolean flag = false;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                        if (graph[nx][ny] == 0) {
                            flag = true;
                            break;
                        }
                    }
                }
                if (!flag) {
                    break;
                }

                if (0 <= x + dx[idx] && x + dx[idx] < n && 0 <= y + dy[idx] && y + dy[idx] < n) {
                    x = x + dx[idx];
                    y = y + dy[idx];

                    if (graph[x][y] == 0) {
                        graph[x][y] = ++num;
                    } else {
                        x -= dx[idx];
                        y -= dy[idx];
                        idx++;

                        if (idx == 4) {
                            idx = 0;
                        }
                    }
                } else {
                    idx++;
                    if (idx == 4) {
                        idx = 0;
                    }
                }
            }

            System.out.println("#" + c);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(graph[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
