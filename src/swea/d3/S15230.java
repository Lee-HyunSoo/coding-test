package swea.d3;

import java.util.*;

/**
 * 알파벳 공부
 */
public class S15230 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String s = scan.nextLine();

            int answer = 0, idx = 0;
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (idx < s.length() && ch == s.charAt(idx++)) {
                    answer++;
                } else {
                    break;
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
