package swea.d3;

import java.util.*;

/**
 * 새샘이의 7-3-5 게임 
 *
 * 7개 중 3개 뽑아서 합을 구해 새로운 수를 만듬
 * 새로운 수들 중 5번째로 큰 수 출력
 *  -- 조합 -> 정렬
 *  -- 이 때 합이 중복될 수 있기 때문에 처음엔 set 에 저장, 이후 list로 변환 후 정렬
 */
public class S5948 {

    static int[] numbers;
    static int[] combi;
    static Set<Integer> set;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {

            numbers = new int[7];
            combi = new int[3];
            set = new HashSet<>();
            for (int i = 0; i < 7; i++) {
                numbers[i] = scan.nextInt();
            }

            dfs(0, 0);
            List<Integer> result = new ArrayList<>(set);
            Collections.sort(result, Collections.reverseOrder());
            System.out.println("#" + tc + " " + result.get(4));
        }
    }

    private static void dfs(int level, int idx) {
        if (level == combi.length) {
            int sum = 0;
            for (int c : combi) {
                sum += c;
            }
            set.add(sum);
            return;
        }

        for (int i = idx; i < 7; i++) {
            combi[level] = numbers[i];
            dfs(level + 1, i + 1);
        }
    }
}