package swea.d3;

import java.util.*;

/**
 * 유효숫자 표기
 */
public class S9658 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String n = scan.nextLine();
            int len = n.length() - 1;

            double num = (double) Integer.parseInt(n.substring(0, 3)) / 100;
            num = Math.round(num * 10) / 10.0;

            if (num >= 10) {
                System.out.println("#" + c + " " + num / 10 + "*10^" + (len + 1));
            } else {
                System.out.println("#" + c + " " + num + "*10^" + len);
            }
        }
    }
}
