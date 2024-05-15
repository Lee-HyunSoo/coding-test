package swea.d2;

import java.util.Scanner;

/**
 * 백만장자 프로젝트
 */
public class S1859 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        int idx = 1;
        while (t-- > 0) {
            int n = scan.nextInt();
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                int input = scan.nextInt();
                arr[i] = input;
            }

            long max = 0;
            long answer = 0;
            for (int i = arr.length - 1; i >= 0; i--) {
                if (arr[i] > max) {
                    max = arr[i];
                }

                if (arr[i] < max) {
                    answer += max - arr[i];
                }
            }
            System.out.println("#" + idx++ + " " + answer);
        }
    }
}
