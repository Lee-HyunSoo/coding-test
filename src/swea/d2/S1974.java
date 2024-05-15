package swea.d2;

import java.util.*;

/**
 * 스도쿠 검증
 */
public class S1974 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int[][] graph = new int[9][9];

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    graph[i][j] = scan.nextInt();
                }
            }

            int answer = 1;
            for (int i = 0; i < 9; i++) {
                int row = 0;
                int col = 0;
                for (int j = 0; j < 9; j++) {
                    row += graph[i][j];
                    col += graph[j][i];
                }

                if (row != 45 || col != 45) {
                    answer = 0;
                    break;
                }
            }

            for (int i = 0; i < 9; i += 3) {
                for (int j = 0; j < 9; j += 3) {
                    int sum = 0;
                    for (int x = i; x < i + 3; x++) {
                        for (int y = j; y < j + 3; y++) {
                            sum += graph[x][y];
                        }
                    }

                    if (sum != 45) {
                        answer = 0;
                        break;
                    }
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
