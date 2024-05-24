package swea.d3;

import java.util.*;

/**
 * 과자 분배
 */
public class S10032 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            int k = scan.nextInt();

            if (n % k == 0) {
                System.out.println("#" + c + " " + 0);
            } else {
                System.out.println("#" + c + " " + 1);
            }
        }
    }
}
