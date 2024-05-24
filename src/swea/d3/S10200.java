package swea.d3;

import java.util.Scanner;

/**
 * 구독자 전쟁
 */
public class S10200 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            int a = scan.nextInt();
            int b = scan.nextInt();

            int max = Math.min(a, b);
            int min;
            if (a + b < n) {
                min = 0;
            } else {
                min = a + b - n;
            }
            System.out.println("#" + c + " " + max + " " + min);
        }
    }
}
