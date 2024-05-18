package swea.d3;

import java.util.*;

/**
 * 1차원 정원
 */
public class S14178 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            int d = scan.nextInt();
            int w = d * 2 + 1;

            int answer = 0;
            int i = d;
            for (; i < n; i += w) {
                answer++;
            }

            if (i - w + d < n - 1) {
                answer++;
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
