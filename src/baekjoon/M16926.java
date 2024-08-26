package baekjoon;

import java.util.*;

/**
 * 배열 돌리기 1
 *
 * 1. 문제 정리
 *  1-1. 배열 한칸씩 돌리기
 *  1-2. 사이드부터 하나씩 줄여가면서
 *
 * 2. 새 배열을 만들고, 기존 배열의 값을 돌려서 넣는다.
 * 3. 가로, 세로 중 짧은 쪽을 반으로 나눈 것이 돌리는 차수가 된다. (4행 5열일 때 -> 가장 외각, 가운데 이렇게 2번돌리면 다돌아감)
 * 4. 해당 차수의 행 최대 길이, 열 최대 길이를 구한다.
 * 5. 최대값과 최소값을 사용해 상 하 좌 우로 배열을 돌린다.
 * 6. 돌린 이후 기존 배열에 값을 복사한다.
 */
public class M16926 {

    static int n, m, r;
    static int[][] graph;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        r = scan.nextInt();
        graph = new int[n][m];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                graph[row][col] = scan.nextInt();
            }
        }
        rotate();
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                System.out.print(graph[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static void rotate() {
        for (int idx = 0; idx < r; idx++) {
            // 2. 새 배열을 만들고, 기존 배열의 값을 돌려서 넣는다.
            int[][] arr = new int[n][m];
            // 3. 가로, 세로 중 짧은 쪽을 반으로 나눈 것이 돌리는 차수가 된다. (4행 5열일 때 -> 가장 외각, 가운데 이렇게 2번돌리면 다돌아감)
            for (int min = 0; min < Math.min(n, m) / 2; min++) {
                // 4. 해당 차수의 행 최대 길이, 열 최대 길이를 구한다.
                int rowMax = n - 1 - min;
                int colMax = m - 1 - min;

                // 5. 최대값과 최소값을 사용해 상 하 좌 우로 배열을 돌린다.
                // down
                for (int row = rowMax; row > min; row--) {
                    arr[row][min] = graph[row - 1][min];
                }
                // right
                for (int col = min + 1; col <= colMax; col++) {
                    arr[rowMax][col] = graph[rowMax][col - 1];
                }
                // up
                for (int row = min; row < rowMax; row++) {
                    arr[row][colMax] = graph[row + 1][colMax];
                }
                // left
                for (int col = min; col < colMax; col++) {
                    arr[min][col] = graph[min][col + 1];
                }
            }
            // 6. 돌린 이후 기존 배열에 값을 복사한다.
            graph = arr;
        }
    }
}
