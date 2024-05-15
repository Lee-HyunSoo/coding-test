package swea.d2;

import java.util.*;

/**
 * 최빈수 구하기
 */
public class S1204 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            scan.nextLine();
            String score = scan.nextLine();
            String[] split = score.split(" ");

            Map<String, Integer> map = new HashMap<>();
            for (String s : split) {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
            int max = Collections.max(map.values());

            int answer = 0;
            for (String s : map.keySet()) {
                if (map.get(s) == max) {
                    answer = Math.max(answer, Integer.parseInt(s));
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
