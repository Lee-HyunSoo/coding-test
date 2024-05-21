package swea.d3;

import java.util.*;

/**
 * 평범한 숫자
 */
public class S11736 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scan.nextInt();
            }

            int answer = 0;
            for (int i = 1; i < n - 1; i++) {
                int min = Math.min(arr[i - 1], arr[i]);
                min = Math.min(min, arr[i + 1]);

                int max = Math.max(arr[i - 1], arr[i]);
                max = Math.max(max, arr[i + 1]);

                if ((arr[i - 1] == min || arr[i - 1] == max) && (arr[i + 1] == min || arr[i + 1] == max)) {
                    answer++;
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
