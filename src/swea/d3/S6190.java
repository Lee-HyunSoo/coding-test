package swea.d3;

import java.util.*;

/**
 * 정곤이의 단조 증가하는 수
 *
 * 단조 증가하는 수 = 각 숫자의 자릿수가 증가하는 수
 *  -- k 자리 수 = d1d2d3...dk 일 때 d1 <= d2 <= d3 ... <= dk 를 만족
 *
 * 양의 정수 n 개 (a1...an)
 * 1 <= i, j <= N 인 i, j 중 ai * aj 가 단조 증가하는 수 일 때 그중 최대값?, 없으면 -1 출력
 * 	-- 조합으로 2개의 수 pick
 * 	-- pick 한 두개의 수로 연산
 */
public class S6190 {

    static int[] nums;
    static int[] combi;
    static boolean[] visit;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt();

            nums = new int[n];
            combi = new int[2];
            visit = new boolean[n];
            answer = -1;
            for (int idx = 0; idx < n; idx++) {
                nums[idx] = scan.nextInt();
            }

            dfs(0, 0);
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void dfs(int level, int idx) {
        if (level == 2) {
            String result = String.valueOf(combi[0] * combi[1]);
            char prev = result.charAt(0);

            boolean flag = false;
            for (int i = 1; i < result.length(); i++) {
                if (prev <= result.charAt(i)) {
                    prev = result.charAt(i);
                } else {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                answer = Math.max(answer, Integer.parseInt(result));
            }
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                combi[level] = nums[i];
                dfs(level + 1, i + 1);
                visit[i] = false;
            }
        }
    }
}
