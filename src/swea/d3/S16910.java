package swea.d3;

import java.util.*;

/**
 * 원 안의 점
 */
public class S16910 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();

            int answer = 0;
            for (int i = -n; i <= n; i++) {
                for (int j = -n; j <= n; j++) {
                    if (Math.pow(i, 2) + Math.pow(j, 2) <= Math.pow(n, 2)) {
                        answer++;
                    }
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
