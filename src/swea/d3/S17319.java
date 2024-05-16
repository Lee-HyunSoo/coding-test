package swea.d3;

import java.util.*;

/**
 * 문자열문자열
 */
public class S17319 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            scan.nextLine();
            String word = scan.nextLine();

            String answer = "Yes";
            if (n % 2 != 0) {
                answer = "No";
            } else {
                String s1 = word.substring(0, n / 2);
                String s2 = word.substring(n / 2);

                if (!s1.equals(s2)) {
                    answer = "No";
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
