package swea.d3;

import java.util.*;

/**
 * N = x^3 이 되는 x 를 구해라
 *  -- N = Math.pow(x, 3)
 *  -- Math.pow 보다 x * x * x  가 속도가 더 빠르다.
 *  -- 1 <= N <= 10^18 이므로 x 의 범위는 1 ~ 10^6
 */
public class S5688 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();

        for (int tc = 1; tc <= t; tc++) {
            long n = scan.nextLong();

            long answer = -1;
            for (long x = 1; x <= 1000000; x++) {
                if (x * x * x == n) {
                    answer = x;
                    break;
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}