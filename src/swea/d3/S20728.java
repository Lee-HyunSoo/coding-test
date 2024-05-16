package swea.d3;

import java.util.*;

/**
 * 공평한 분배2
 */
public class S20728 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            int k = scan.nextInt();

            int[] candy = new int[n];
            for (int i = 0; i < n; i++) {
                candy[i] = scan.nextInt();
            }
            Arrays.sort(candy);

            int answer = Integer.MAX_VALUE;
            for (int i = 0; i <= n - k; i++) {
                int max = 0;
                int min = Integer.MAX_VALUE;
                for (int j = i; j < i + k; j++) {
                    if (candy[j] > max) {
                        max = candy[j];
                    }
                    if (candy[j] < min) {
                        min = candy[j];
                    }
                }
                answer = Math.min(answer, max - min);
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
