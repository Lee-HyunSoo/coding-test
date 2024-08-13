package baekjoon;

import java.util.Scanner;

/**
 * N 과 M (2)
 *
 * 1 부터 N 까지 자연수 중 중복 없이 M 개를 고른 수열
 */
public class Q15650 {

    static int n, m;
    static int[] nums;
    static boolean[] visit;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        n = scan.nextInt();
        m = scan.nextInt();
        nums = new int[m];
        visit = new boolean[n + 1];

        combination(0, 1);
    }

    private static void combination(int level, int num) {
        // level == m 이면 출력 후 return
        if (level == m) {
            for (int n : nums) {
                System.out.print(n + " ");
            }
            System.out.println();
            return;
        }

        // idx 를 num 부터 시작, n 개 중 m 개를 뽑는 조합을 구한다.
        for (int idx = num; idx <= n; idx++) {
            if (!visit[idx]) {
                visit[idx] = true;
                nums[level] = idx;
                combination(level + 1, idx + 1);
                visit[idx] = false;
            }
        }
    }
}
