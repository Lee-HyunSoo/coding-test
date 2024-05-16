package swea.d3;

import java.util.*;

/**
 * 회문의 회문
 */
public class S20019 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String s = scan.nextLine();
            int n = (s.length() - 1) / 2;

            String r = new StringBuilder(s).reverse().toString();
            String a = "";
            for (int i = 0; i < n; i++) {
                a += s.charAt(i);
            }
            String b = "";
            for (int i = s.length() - n; i < s.length() ; i++) {
                b += s.charAt(i);
            }

            if (s.equals(r) && a.equals(b)) {
                System.out.println("#" + c + " YES");
            } else {
                System.out.println("#" + c + " NO");
            }
        }
    }
}
