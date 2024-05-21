package swea.d3;

import java.util.*;

/**
 * 구구단2
 */
public class S12221 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int a = scan.nextInt();
            int b = scan.nextInt();

            if (a < 10 && b < 10) {
                System.out.println("#" + c + " " + a * b);
            } else {
                System.out.println("#" + c + " " + -1);
            }
        }
    }
}
