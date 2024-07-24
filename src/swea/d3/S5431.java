package swea.d3;

import java.util.*;

/**
 * 민석이의 과제 체크하기
 *
 * n 명의 수강생, k 명의 제출자
 *  -- int[n + 1], 제출하면 1
 */
public class S5431 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt();
            int k = scan.nextInt();

            int[] student = new int[n + 1];
            for (int idx = 0; idx < k; idx++) {
                int input = scan.nextInt();
                student[input] = 1;
            }

            System.out.print("#" + tc + " ");
            for (int idx = 1; idx <= n; idx++) {
                if (student[idx] == 0) {
                    System.out.print(idx + " ");
                }
            }
            System.out.println();
        }
    }
}