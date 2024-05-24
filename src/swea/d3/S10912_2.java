package swea.d3;

import java.util.*;

public class S10912_2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String str = scan.nextLine();

            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }

            List<Character> list = new ArrayList<>();
            for (char ch : map.keySet()) {
                if (map.get(ch) % 2 == 1) {
                    list.add(ch);
                }
            }
            Collections.sort(list);

            System.out.print("#" + c + " ");
            if (list.size() == 0) {
                System.out.print("Good");
            } else {
                for (char ch : list) {
                    System.out.print(ch);
                }
            }
            System.out.println();
        }
    }
}
