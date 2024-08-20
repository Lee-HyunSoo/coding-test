package baekjoon;

import java.util.*;

/**
 * N 과 M (1)
 *
 * 재귀를 통한 순열 구하기
 */
public class M15649 {

    static int[] nums;
    static boolean[] visit;
    static int n, m;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();

        nums = new int[m]; // 뽑은 수를 저장하는 배열
        visit = new boolean[n + 1]; // 어떤 수를 뽑았는지 기록하는 배열

        permutation(0);
    }

    private static void permutation(int level) {
        // 뽑은 수의 개수가 m 개라면, 출력 후 return
        if (level == m) {
            for (int num : nums) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }

        // 뽑지 않은 수만 뽑는다.
        for (int idx = 1; idx <= n; idx++) {
            if (!visit[idx]) {
                visit[idx] = true;
                nums[level] = idx;
                permutation(level + 1);
                visit[idx] = false;
            }
        }
    }
}
