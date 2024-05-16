package swea.d3;

import java.util.*;

/**
 * 등차수열 만들기
 */
public class S18662 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            double A = scan.nextDouble();
            double B = scan.nextDouble();
            double C = scan.nextDouble();

            double ab = B - A;
            double bc = C - B;

            if (ab == bc) {
                System.out.println("#" + c + " " + 0.0);
            } else {
                double d1 = Math.abs((2 * B) - A - C);
                double d2 = Math.abs(A + C - (2 * B)) / 2;
                System.out.println("#" + c + " " + Math.min(d1, d2));

                // b - (a + x) == c - b -> x = 2b - a - c
                // (b + x) - a == c - (b + x) -> 2x = c + a - 2b
                // (b - a) == (c + x) - b -> x = 2b - a - c
            }
        }
    }
}
