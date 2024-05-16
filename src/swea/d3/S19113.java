package swea.d3;

import java.util.*;

/**
 * 식료품 가게
 */
public class S19113 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();

            Map<Long, Integer> map = new HashMap<>();
            List<Long> list = new ArrayList<>();
            for (int i = 0; i < n * 2; i++) {
                long p = scan.nextInt();
                map.put(p, map.getOrDefault(p, 0) + 1);
                list.add(p);
            }

            List<Long> answer = new ArrayList<>();
            for (long i : list) {
                if (map.get(i) == 0) {
                    continue;
                }
                if (map.containsKey(i * 100 / 75)) {
                    answer.add(i);
                    map.put(i * 100 / 75, map.get(i * 100 / 75) - 1);
                }
                map.put(i, map.get(i) - 1);
            }

            System.out.print("#" + c + " ");
            for (long a : answer) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }
}
