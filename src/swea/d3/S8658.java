package swea.d3;

import java.util.*;

/**
 * Summation
 */
public class S8658 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String[] split = scan.nextLine().split(" ");

            int max = 0;
            int min = Integer.MAX_VALUE;
            for (String s : split) {
                int sum = 0;
                for (char ch : s.toCharArray()) {
                    sum += Character.getNumericValue(ch);
                }
                max = Math.max(max, sum);
                min = Math.min(min, sum);
            }
            System.out.println("#" + c + " " + max + " " + min);
        }
    }
}
