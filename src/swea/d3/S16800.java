package swea.d3;

import java.util.*;

/**
 * 구구단 걷기
 */
public class S16800 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            long n = scan.nextLong();

            long answer = Long.MAX_VALUE;
            for (long i = 1; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    long sum = (n / i) - 1 + (i - 1);
                    answer = Math.min(answer, sum);
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
