package swea.d3;

import java.util.*;

/**
 * 다양성 측정
 */
public class S7728 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String num = scan.nextLine();
            Set<Character> s = new HashSet<>();

            for (char ch : num.toCharArray()) {
                s.add(ch);
            }
            System.out.println("#" + c + " " + s.size());
        }
    }
}
