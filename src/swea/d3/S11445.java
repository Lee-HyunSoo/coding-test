package swea.d3;

import java.util.*;

/**
 * 무한 사전
 */
public class S11445 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String p = scan.nextLine();
            String q = scan.nextLine();
            int min = Math.min(p.length(), q.length());
            int max = Math.max(p.length(), q.length());

            int i = 0;
            for (; i < min; i++) {
                if (p.charAt(i) != q.charAt(i)) {
                    break;
                }
            }

            String answer = "N";
            if (max - i > 1) {
                answer = "Y";
            } else if (max - i == 1) {
                if (p.length() < q.length()) {
                    if (q.charAt(i) != 'a') {
                        answer = "Y";
                    }
                } else {
                    if (p.charAt(i) != 'a') {
                        answer = "Y";
                    }
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
