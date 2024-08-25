package baekjoon;

import java.util.*;

/**
 * Z
 *
 * 1. 분할 정복을 사용한다.
 * 2. 2^n 크기부터 시작해, (r,c) 의 위치를 찾을 때까지 사각형을 분할해 나간다.
 * 	2-1. 이 때 좌상단(1), 우상단(2), 좌하단(3), 우하단(4) 순번으로 재귀를 호출한다.
 * 3. 해당 사분면 내에 r, c 가 없다면, 지나가야하는 사분면 이기 때문에 사분면 크기만큼 더해준다.
 * 4. 현재 좌표가 r, c 라면, 여태 더한 값을 출력한다.
 */
public class M1074 {

    static int n, r, c;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        r = scan.nextInt();
        c = scan.nextInt();
        answer = 0;

        divConquer(0, 0, (int) Math.pow(2, n));
    }

    private static void divConquer(int row, int col, int size) {
        if (row == r && col == c) {
            // 4. 현재 좌표가 r, c 라면, 여태 더한 값을 출력한다.
            System.out.println(answer);
            return;
        }

        // 2. 2^n 크기부터 시작해, (r,c) 의 위치를 찾을 때까지 사각형을 분할해 나간다.
        if (row <= r && r < row + size && col <= c && c < col + size) {
            int half = size / 2;
            // 2-1. 이 때 좌상단(1), 우상단(2), 좌하단(3), 우하단(4) 순번으로 재귀를 호출한다.
            divConquer(row, col, half);
            divConquer(row, col + half, half);
            divConquer(row + half, col, half);
            divConquer(row + half, col + half, half);
        } else {
            // 3. 해당 사분면 내에 r, c 가 없다면, 지나가야하는 사분면 이기 때문에 사분면 크기만큼 더해준다.
            answer += (size * size);
        }
    }
}
