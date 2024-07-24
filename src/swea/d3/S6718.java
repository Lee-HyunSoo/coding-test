package swea.d3;

import java.util.*;

/**
 * 희성이의 원근법
 *
 * d = 어떤 물체와의 거리를 m 단위로 표현한 수
 * 문제 조건 - km (1000m = 1km)
 *  -- 조건 * 1000 과 d 를 비교
 */
public class S6718 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int distance = scan.nextInt();

            int answer = 0;
            if (distance < 100) {
                answer = 0;
            } else if (distance < 1_000) {
                answer = 1;
            } else if (distance < 10_000) {
                answer = 2;
            } else if (distance < 100_000) {
                answer = 3;
            } else if (distance < 1_000_000) {
                answer = 4;
            } else {
                answer = 5;
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}