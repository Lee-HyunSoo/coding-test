package swea.d3;

import java.util.*;

/**
 * 기차 사이의 파리
 *
 * D : 두 기차 전면부 사이의 거리
 * A : 기차 A 의 속력
 * B : 기차 B 의 속력
 * F : 파리의 속력
 *
 *  -- x * A + x * B >= D -> 충돌
 *  -- x = D / (A + B)
 *  -- F * D / (A + B) -> 파리 이동거리
 */
public class S6019 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int d = scan.nextInt();
            int a = scan.nextInt();
            int b = scan.nextInt();
            int f = scan.nextInt();

            double result = (double) d * f / (a + b);
            System.out.println("#" + tc + " " + result);
        }
    }
}
