package swea.d3;

import java.util.*;

/**
 * 무한 문자열
 */
public class S15758 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String str = scan.nextLine();
            String[] s = str.split(" ");

            String s1 = "", s2 = "";
            for (int i = 0; i < s[1].length(); i++) {
                s1 += s[0];
            }
            for (int i = 0; i < s[0].length(); i++) {
                s2 += s[1];
            }

            if (s1.equals(s2)) {
                System.out.println("#" + c + " yes");
            } else {
                System.out.println("#" + c + " no");
            }
        }
    }
}
