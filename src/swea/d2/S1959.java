package swea.d2;

import java.util.Scanner;

/**
 * 두개의 숫자열
 */
public class S1959 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            int m = scan.nextInt();

            int[] arr1 = new int[n];
            int[] arr2 = new int[m];

            for (int i = 0; i < n; i++) {
                arr1[i] = scan.nextInt();
            }
            for (int i = 0; i < m; i++) {
                arr2[i] = scan.nextInt();
            }

            int answer = 0;
            if (n < m) {
                for (int i = 0; i < m; i++) {
                    int rt = i + n;
                    if (rt > m) {
                        break;
                    }
                    int sum = 0;
                    for (int j = i; j < rt; j++) {
                        sum += arr1[j - i] * arr2[j];
                    }
                    answer = Math.max(answer, sum);
                }
            } else {
                for (int i = 0; i < n; i++) {
                    int rt = i + m;
                    if (rt > n) {
                        break;
                    }
                    int sum = 0;
                    for (int j = i; j < rt; j++) {
                        sum += arr1[j] * arr2[j - i];
                    }
                    answer = Math.max(answer, sum);
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
