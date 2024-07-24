package swea.d3;

import java.util.*;

/**
 * 준홍이의 카드놀이
 */
public class S7102 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt();
            int m = scan.nextInt();

            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    map.put(i + j, map.getOrDefault(i + j, 0) + 1);
                }
            }

            int max = 0;
            for (int key : map.keySet()) {
                if (map.get(key) > max) {
                    max = map.get(key);
                }
            }

            List<Integer> answer = new ArrayList<>();
            for (int key : map.keySet()) {
                if (map.get(key) == max) {
                    answer.add(key);
                }
            }

            System.out.print("#" + tc + " ");
            for (int a : answer) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }
}