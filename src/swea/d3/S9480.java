package swea.d3;

import java.util.*;

/**
 * 민정이와 광직이의 알파벳 공부
 */
public class S9480 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            scan.nextLine();

            Map<Character, Integer> map = new HashMap<>();
            int answer = 0;
            for (int i = 0; i < n; i++) {
                String word = scan.nextLine();

                for (int j = 0; j < word.length(); j++) {
                    map.put(word.charAt(j), map.getOrDefault(word.charAt(j), 0) + 1);
                }
            }

            for (char ch : map.keySet()) {
                System.out.println(ch + " " + map.get(ch));
            }

            boolean flag = true;
            while (flag) {
                answer++;
                for (char ch : map.keySet()) {
                    map.put(ch, map.get(ch) - 1);

                    if (map.get(ch) == 0) {
                        flag = false;
                        break;
                    }
                }
            }

            System.out.println("#" + c + " " + answer);
        }
    }
}
