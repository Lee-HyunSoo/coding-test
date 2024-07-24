package swea.d3;

import java.util.Scanner;

/**
 * 사랑의 카운슬러
 */
public class S7227 {

    static class Pair {
        int x, y;
        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Pair[] pairs; // 입력 값들 저장
    static boolean[] visit; // combination 을 구하기 위한 visit 배열
    static int n;
    static long answer; // 가장 작은 벡터합

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = scan.nextInt();

            answer = Long.MAX_VALUE;
            visit = new boolean[n];
            pairs = new Pair[n];
            for (int i = 0; i < n; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                pairs[i] = new Pair(x, y);
            }

            dfs(0, 0);
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void dfs(int l, int idx) {
        if (l == n / 2) {
            long x = 0, y = 0;
            for (int i = 0; i < n; i++) {
                if (visit[i]) {
                    x += pairs[i].x;
                    y += pairs[i].y;
                } else {
                    x -= pairs[i].x;
                    y -= pairs[i].y;
                }
            }
            answer = Math.min(answer,  x * x  + y * y);
            return;
        }

        // 조합, 순서 상관 x (1, 2) == (2, 1)
        for (int i = idx; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                dfs(l + 1, i + 1); // i + 1 항상 주의!
                visit[i] = false;
            }
        }
    }
}