package swea.d3;

import java.util.*;

/**
 * 몬스터 사냥
 */
public class S11387 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            long d = scan.nextLong();
            long l = scan.nextLong();
            long n = scan.nextLong();

            long answer = 0;
            for (long i = 0; i < n; i++) {
                answer += (d + d * i * l / 100);
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
