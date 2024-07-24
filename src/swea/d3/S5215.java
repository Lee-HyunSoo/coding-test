package swea.d3;

import java.util.*;

/**
 * 햄버거 다이어트
 */
public class S5215 {

    static class Pair {
        int x, y; // 점수, 칼로리
        public Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static List<Pair> list = new ArrayList<>();
    static int answer;
    static int n = 0, l = 0;

    public static void main(String args[]) throws Exception {
        Scanner scan = new Scanner(System.in);
        int T;
        T=scan.nextInt();

        for(int tc = 1; tc <= T; tc++) {
            n = scan.nextInt();
            l = scan.nextInt();

            list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int t = scan.nextInt();
                int k = scan.nextInt();
                list.add(new Pair(t, k));
            }

            answer = 0;
            dfs(0, 0, 0);
            System.out.println("#" + tc + " " + answer);
        }
    }

    public static void dfs(int idx, int sumt, int sumk) {
        if (sumk > l) {
            return;
        }
        if (idx == n) {
            answer = Math.max(answer, sumt);
            return;
        }

        dfs(idx + 1, sumt + list.get(idx).x, sumk + list.get(idx).y);
        dfs(idx + 1, sumt, sumk);
    }
}
