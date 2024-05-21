package swea.d3;

import java.util.*;

/**
 * 반반
 */
public class S11856 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String s = scan.nextLine();

            Map<Character, Integer> map = new HashMap<>();
            for (char ch : s.toCharArray()) {
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }

            String answer = "Yes";
            if (map.keySet().size() != 2) {
                answer = "No";
            } else {
                for (int i : map.values()) {
                    if (i != 2) {
                        answer = "No";
                        break;
                    }
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
