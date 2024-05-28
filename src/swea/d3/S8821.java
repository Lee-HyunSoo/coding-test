package swea.d3;

import java.util.*;

/**
 * 적고 지우기
 */
public class S8821 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String str = scan.nextLine();

            Map<Character, Integer> map = new HashMap<>();
            for (char ch : str.toCharArray()) {
                if (map.containsKey(ch) && map.get(ch) == 1) {
                    map.put(ch, map.get(ch) - 1);
                } else {
                    map.put(ch, 1);
                }
            }

            int answer = 0;
            for (int i : map.values()) {
                answer += i;
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
