package swea.d3;

import java.util.*;

/**
 * 홀수일까 짝수일까
 *
 * n % 2 - String input
 */
public class S5549 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int tc = 1; tc <= t; tc++) {
            String input = scan.nextLine();
            int len = input.length() - 1;

            if (Character.getNumericValue(input.charAt(len)) % 2 == 0) {
                System.out.println("#" + tc + " Even");
            } else {
                System.out.println("#" + tc + " Odd");
            }
        }
    }
}