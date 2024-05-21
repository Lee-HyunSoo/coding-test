package swea.d3;

import java.util.*;

/**
 * 합성수 방정식
 */
public class S16002 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();

            for (int y = 4; y <= 1000000000; y++) {
                int x = y + n;
                if (!isPrime(y) && !isPrime(x)) {
                    System.out.println("#" + c + " " + x + " " + y);
                    break;
                }
            }
        }
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
