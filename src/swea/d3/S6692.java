package swea.d3;

import java.util.*;

/**
 * 다솔이의 월급 상자
 *
 * pi 의 확률로 xi 만원이 들어있는 상자
 *  -- 0.3 확률로 100만원, 0.7 확률로 1만원
 *  -- 0.3 * 100 + 0.7 * 1
 *
 */
public class S6692 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt();

            double answer = 0;
            for (int idx = 0; idx < n; idx++) {
                double p = scan.nextDouble();
                int x = scan.nextInt();
                answer += (p * x);
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}
