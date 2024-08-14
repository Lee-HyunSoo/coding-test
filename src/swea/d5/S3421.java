package swea.d5;

import java.util.*;

/**
 * 수제 버거 장인
 *
 * 1. 입력 값
 *   1-1. 재료로 사용할 수 있는 1~n 의 수
 *   1-2. 입력 된 a, b 는 서로 같이 쓸 수 없는 재료
 *   1-3. 같이 쓸 수 없는 재료는 Pair 를 통해 List 에 저장한다.
 *
 * 2. 부분집합을 set 자료구조로 관리
 *   2-1. 해당 수를 집합에 넣는 경우 add
 *   2-2. 해당 수를 집합에 넣지 않는 경우 remove
 *
 * 3. 종료 조건
 *   3-1. level 이 n 을 넘어선 경우
 *   3-2. 금지목록을 탐색하며 현재 부분 집합에 금지목록이 포함되어있으면 바로 return
 *   3-3. 모든 금지목록에 해당하지 않으면 answer + 1
 */
public class S3421 {

    static class Pair {
        int row, col;

        Pair (int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int n, m;
    static Set<Integer> sub;
    static List<Pair> banList;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            // 1-1. 재료로 사용할 수 있는 1~n 의 수
            n = scan.nextInt();
            m = scan.nextInt();
            sub = new HashSet<>();
            banList = new ArrayList<>();
            answer = 0;

            for (int idx = 0; idx < m; idx++) {
                // 1-2. 입력 된 a, b 는 서로 같이 쓸 수 없는 재료
                int a = scan.nextInt();
                int b = scan.nextInt();
                // 1-3. 같이 쓸 수 없는 재료는 Pair 를 통해 List 에 저장한다.
                banList.add(new Pair(a, b));
            }

            subset(1);
            System.out.println("#" + tc + " " + answer);
        }

    }

    private static void subset(int level) {
        // 3-1. level 이 n 을 넘어선 경우
        if (level > n) {
            // 3-2. 금지목록을 탐색하며 현재 부분 집합에 금지목록이 포함되어있으면 바로 return
            for (Pair p : banList) {
                if (sub.contains(p.row) && sub.contains(p.col)) {
                    return;
                }
            }
            // 3-3. 모든 금지목록에 해당하지 않으면 answer + 1
            answer++;
            return;
        }

        // 2-1. 해당 수를 집합에 넣는 경우 add
        sub.add(level);
        subset(level + 1);
        // 2-2. 해당 수를 집합에 넣지 않는 경우 remove
        sub.remove(level);
        subset(level + 1);
    }
}