package swea.d3;

import java.util.*;

/**
 * 장애물 경주 난이도
 *
 * 배열의 크기-1 까지 루프를 돌며 i, i+1번 index 비교
 */
public class S6730 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt();
            int[] obstacles = new int[n];
            for (int i = 0; i < n; i++) {
                obstacles[i] = scan.nextInt();
            }

            int up = 0, down = 0;
            for (int i = 0; i < n - 1; i++) {
                if (obstacles[i] < obstacles[i + 1]) {
                    up = Math.max(up, obstacles[i + 1] - obstacles[i]);
                } else {
                    down = Math.max(down, obstacles[i] - obstacles[i + 1]);
                }
            }
            System.out.println("#" + tc + " " + up + " " + down);
        }
    }
}