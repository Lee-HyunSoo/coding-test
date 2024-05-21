package swea.d3;

import java.util.*;

/**
 * 팔씨름
 */
public class S13547 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String ox = scan.nextLine();

            int cnt = 0;
            for (char ch : ox.toCharArray()) {
                if (ch == 'o') {
                    cnt++;
                }
            }
            cnt += 15 - ox.length();

            String answer = "YES";
            if (cnt < 8) {
                answer = "NO";
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
