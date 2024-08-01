package swea.d3;

import java.util.*;

/**
 * 부분 수열의 합
 *
 * a1 ~ aN 개의 자연수가 주어졌을 때, 최소 1개 이상의 수 선택
 * 그 합이 k 가 되는 경우의 수
 *
 * sum < k -> 재귀
 * sum > k -> return
 * sum == k -> answer++, return
 */
public class S2817 {

    static int n, k;
    static int[] seq;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = scan.nextInt();
            k = scan.nextInt();
            seq = new int[n];
            answer = 0;

            for (int i = 0; i < n; i++) {
                seq[i] = scan.nextInt();
            }

            for (int i = 0; i < n; i++) {
                dfs(i + 1, seq[i]);
            }
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void dfs(int idx, int sum) {
        if (sum > k) {
            return;
        }
        if (sum == k) {
            answer++;
            return;
        }

        for (int i = idx; i < n; i++) {
            dfs(i + 1, sum + seq[i]);
        }
    }
}
