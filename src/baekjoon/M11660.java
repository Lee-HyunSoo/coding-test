package baekjoon;

import java.util.Scanner;

/**
 * 구간 합 구하기 5
 *
 * 1. 구간합 공식
 * 	1-1. (x, y) = (x-1, y) + (x, y-1) - (x-1, y-1)
 * 	1-2. 구간합을 단순 배열 내 숫자가 아니라, 해당 구간을 기준으로 계산
 *
 * 2. 구해야되는 구간이 주어졌을 때
 * 	2-1. (2, 2), (3, 4) 가 주어졌다면 -> (x1, y1), (x2, y2)
 *  2-2. (3, 1), (1, 4) 가 필요 -> (x2, y1 - 1), (x1 - 1, y2)
 *
 * 3. 해당 구간의 연산
 *  3-1. 전체 합 -> sum[x2][y2]
 *  3-2. 빼야하는 구간 -> sum[x2][y1 - 1], sum[x1 - 1][y2]
 *  3-3. 겹치게 뺀 구간은 다시 더해준다. -> sum[x1 - 1][y1 - 1]
 */
public class M11660 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();

        int[][] arr = new int[n + 1][n + 1];
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                arr[row][col] = scan.nextInt();
                // 1. 구간합 공식 : 전체합 - 빼야하는 구간 + 빼야하는 구간 중 겹친구간
                arr[row][col] += (arr[row - 1][col] + arr[row][col - 1] - arr[row - 1][col - 1]);
            }
        }

        for (int row = 1; row <= m; row++) {
            // 2. 구해야되는 구간이 주어졌을 때 : 필요한 좌표를 구한다.
            int x1 = scan.nextInt();
            int y1 = scan.nextInt();
            int x2 = scan.nextInt();
            int y2 = scan.nextInt();

            // 3. 해당 구간의 연산 : 전체합 - 빼야하는 구간 + 빼야하는 구간 중 겹친구간
            int answer = arr[x2][y2] - arr[x2][y1 - 1] - arr[x1 - 1][y2] + arr[x1 - 1][y1 - 1];
            System.out.println(answer);
        }
    }
}