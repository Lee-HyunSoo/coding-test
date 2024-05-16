package swea.d3;

import java.util.*;

/**
 * 팰린드롬 문제
 */
public class S19003 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            scan.nextLine();

            String[] str = new String[n];
            for (int i = 0; i < n; i++) {
                str[i] = scan.nextLine();
            }

            int answer1 = 0, answer2 = 0;
            for (int i = 0; i < str.length; i++) {
                String r = new StringBuilder(str[i]).reverse().toString();
                if (str[i].equals(r)) {
                    answer1 = m;
                }
                for (int j = 0; j < str.length; j++) {
                    if (i == j) continue;
                    if (r.equals(str[j])) {
                        answer2 += m;
                    }
                }
            }
            System.out.println("#" + c + " " + (answer1 + answer2));
        }
    }
}
