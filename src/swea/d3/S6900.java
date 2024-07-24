package swea.d3;

import java.util.*;

/**
 * 주혁이의 복권 당첨
 *
 * (복권, 점수) - n 개
 * 구매한 복권 - m 개
 * '*' 은 모든 숫자와 매칭 가능하므로 continue, 이후 숫자 하나라도 매칭이 안되면 break
 *
 * 당첨복권 = ***7 이고 구매한 복권이 (1117, 1237) 이면 두개 다 당첨으로 판정
 * 때문에 당첨됐다고 당첨 복권을 삭제하면 안된다.
 */
public class S6900 {

    static class Pair {
        String lotto;
        int score;

        Pair (String lotto, int score) {
            this.lotto = lotto;
            this.score = score;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            scan.nextLine();

            List<Pair> lottos = new ArrayList<>();
            for (int idx = 0; idx < n; idx++) {
                String[] split = scan.nextLine().split(" ");
                lottos.add(new Pair(split[0], Integer.parseInt(split[1])));
            }

            List<String> select = new ArrayList<>();
            for (int idx = 0; idx < m; idx++) {
                select.add(scan.nextLine());
            }


            long answer = 0L;
            for (int row = 0; row < m; row++) {
                String mySelect = select.get(row);
                for (int col = 0; col < lottos.size(); col++) {
                    String lotto = lottos.get(col).lotto;
                    boolean flag = false;
                    for (int idx = 0; idx < 8; idx++) {
                        if (lotto.charAt(idx) == '*') {
                            continue;
                        }
                        if (mySelect.charAt(idx) != lotto.charAt(idx)) {
                            flag = true;
                            break;
                        }
                    }

                    if (!flag) {
                        answer += lottos.get(col).score;
                        break;
                    }
                }
            }
            System.out.println("#" + tc + " " + answer);

        }
    }
}
