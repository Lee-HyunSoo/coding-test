package swea.d3;

import java.util.*;

/**
 * 규영이와 인영이의 카드 게임
 *
 * 1 ~ 18 의 배열을 통해 규영이 / 인영이의 카드 분류
 * 분류 된 인영이의 카드 목록으로 가능한 모든 순열 구하기
 * 하나의 순열이 완성되었으면 점수 비교 -> count
 */
public class S6808 {

    static int[] permu;
    static boolean[] visit;
    static List<Integer> l1;
    static List<Integer> l2;
    static int total;
    static int win;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int[] cards = new int[19];
            for (int idx = 0; idx < 9; idx++) {
                int card = scan.nextInt();
                cards[card] = 1;
            }
            permu = new int[9];
            visit = new boolean[9];
            total = 0;
            win = 0;

            l1 = new ArrayList<>(); // 규영
            l2 = new ArrayList<>(); // 인영
            for (int idx = 1; idx <= 18; idx++) {
                if (cards[idx] == 1) {
                    l1.add(idx);
                } else {
                    l2.add(idx);
                }
            }

            dfs(0);
            System.out.println("#" + tc + " " + win + " " + (total - win));
        }
    }

    private static void dfs(int level) {
        if (level == 9) {
            total++;
            int score1 = 0, score2 = 0; // 규영, 인영
            for (int idx = 0; idx < 9; idx++) {
                if (l1.get(idx) > permu[idx]) {
                    score1 += (l1.get(idx) + permu[idx]);
                } else if (l1.get(idx) < permu[idx]) {
                    score2 += (l1.get(idx) + permu[idx]);
                }
            }
            if (score1 > score2) {
                win++;
            }
            return;
        }

        for (int idx = 0; idx < 9; idx++) {
            if (!visit[idx]) {
                visit[idx] = true;
                permu[level] = l2.get(idx);
                dfs(level + 1);
                visit[idx] = false;
            }
        }
    }
}