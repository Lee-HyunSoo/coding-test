package swea.d3;

import java.util.*;

/**
 * 늘어지는 소리 만들기
 *
 * 단어의 중간에 - 넣기
 * 하이픈의 위치가 주어지는데, 중복 된 자리에도 주어짐
 *  -- 2, 3, 2 or 0, 0, 0
 *  -- map 을 통해 pos 와 cnt 저장 -> 한번에 추가하기
 */
public class S4676 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            scan.nextLine();
            String input = scan.nextLine();
            int h = scan.nextInt();

            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < h; i++) {
                int pos = scan.nextInt();
                map.put(pos, map.getOrDefault(pos, 0) + 1);
            }

            StringBuilder answer = new StringBuilder();
            for (int idx = 0; idx <= input.length(); idx++) {
                if (map.containsKey(idx)) {
                    for (int i = 0; i < map.get(idx); i++) {
                        answer.append("-");
                    }
                }
                if (idx < input.length()) {
                    answer.append(input.charAt(idx));
                }
            }
            System.out.println("#" + tc + " " + answer.toString());
        }
    }
}
