package swea.d2;

import java.util.*;

/**
 * 어디에 단어가 들어갈 수 있을까
 */
public class S1979 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            int k = scan.nextInt();

            int[][] graph = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = scan.nextInt();
                }
            }

            int answer = 0;
            for (int i = 0; i < n; i++) {
                int idx = 0;
                while (idx + k <= n) {
                    boolean flag1 = false;
                    for (int j = idx; j < idx + k; j++) {
                        if (graph[i][j] != 1) {
                            flag1 = true;
                            break;
                        }
                    }

                    if (!flag1) {
                        int y1 = idx - 1;
                        int y2 = idx + k;

                        if (y1 >= 0 && graph[i][y1] == 0 && y2 < n && graph[i][y2] == 0) {
                            answer++;
                        } else if (y1 == -1 && y2 < n && graph[i][y2] == 0) {
                            answer++;
                        } else if (y2 == n && y1 >= 0 && graph[i][y1] == 0) {
                            answer++;
                        }
                    }

                    boolean flag2 = false;
                    for (int j = idx; j < idx + k; j++) {
                        if (graph[j][i] != 1) {
                            flag2 = true;
                            break;
                        }
                    }

                    if (!flag2) {
                        int x1 = idx - 1;
                        int x2 = idx + k;

                        if (x1 >= 0 && graph[x1][i] == 0 && x2 < n && graph[x2][i] == 0) {
                            answer++;
                        } else if (x1 == -1 && x2 < n && graph[x2][i] == 0) {
                            answer++;
                        } else if (x2 == n && x1 >= 0 && graph[x1][i] == 0) {
                            answer++;
                        }
                    }
                    idx++;
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
