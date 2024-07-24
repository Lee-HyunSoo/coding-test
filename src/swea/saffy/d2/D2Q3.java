package swea.saffy.d2;

import java.util.Scanner;

/**
 * 두개의 숫자열
 */
public class D2Q3 {

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
            if (n > m) {
                for (int i = 0; i < n; i++) {
                    int idx = i, sum = 0;
                    boolean flag = false;
                    for (int j = 0; j < m; j++) {
                        if (idx == n) {
                            flag = true;
                            break;
                        }
                        sum += arr1[idx++] * arr2[j];
                    }
                    if (flag) {
                        break;
                    }
                    answer = Math.max(answer, sum);
                }
            } else {
                for (int i = 0; i < m; i++) {
                    int idx = i, sum = 0;
                    boolean flag = false;
                    for (int j = 0; j < n; j++) {
                        if (idx == m) {
                            flag = true;
                            break;
                        }
                        sum += arr2[idx++] * arr1[j];
                    }
                    if (flag) {
                        break;
                    }
                    answer = Math.max(answer, sum);
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
