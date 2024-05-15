package swea.d2;

import java.util.*;

/**
 * 간단한 소인수분해
 */
public class S1945 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();

            int a = 0, b = 0, cc = 0, d = 0, e = 0;
            while (n > 1) {
                if (n % 11 == 0) {
                    n /= 11;
                    e++;
                } else if (n % 7 == 0) {
                    n /= 7;
                    d++;
                } else if (n % 5 == 0) {
                    n /= 5;
                    cc++;
                } else if (n % 3 == 0) {
                    n /= 3;
                    b++;
                } else if (n % 2 == 0) {
                    n /= 2;
                    a++;
                }
            }
            System.out.println("#" + c + " " + a + " " + b + " " + cc + " " + d + " " + e);
        }
    }
}
