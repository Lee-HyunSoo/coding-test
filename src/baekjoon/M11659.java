package baekjoon;

import java.util.Scanner;

/**
 * 구간 합 구하기 4
 *
 * n 개의 수에서 i ~ j 번째 수의 합 구하기
 *
 * i 번째 배열 = 0번째 ~ i번째 까지의 합을 저장
 * i ~ j 까지의 합 = j번째 배열 - (i - 1)번째 배열
 */
public class M11659 {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int m = scan.nextInt();

        int[] nums = new int[n + 1];
        for (int idx = 1; idx <= n; idx++) {
            nums[idx] = scan.nextInt();
            nums[idx] += nums[idx - 1];
        }

        for (int idx = 0; idx < m; idx++) {
            int i = scan.nextInt();
            int j = scan.nextInt();

            System.out.println(nums[j] - nums[i - 1]);
        }
    }
}
