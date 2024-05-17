package swea.d3;

import java.util.*;

/**
 * 공과 잡초
 */
public class S14555 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String str = scan.nextLine();

            int answer = 0;
            for (int i = 1; i < str.length(); i++) {
                String word = String.valueOf(str.charAt(i - 1)) + String.valueOf(str.charAt(i));

                if (word.equals("(|") || word.equals("|)") || word.equals("()")) {
                    answer++;
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
