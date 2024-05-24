package swea.d3;

import java.util.*;

/**
 * 외로운 문자
 */
public class S10912 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String str = scan.nextLine();
            char[] ch = str.toCharArray();
            Arrays.sort(ch);

            Stack<Character> s = new Stack<>();
            for (int i = 0; i < ch.length; i++) {
                if (s.isEmpty()) {
                    s.push(ch[i]);
                } else {
                    if (s.peek() == ch[i]) {
                        s.pop();
                    } else {
                        s.push(ch[i]);
                    }
                }
            }

            System.out.print("#" + c + " ");
            if (!s.isEmpty()) {
                for (char w : s) {
                    System.out.print(w);
                }
            } else {
                System.out.print("Good");
            }
            System.out.println();
        }
    }
}
