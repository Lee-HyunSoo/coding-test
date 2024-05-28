package swea.d3;

import java.util.*;

/**
 * 제로
 */
public class S8931 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();

            Stack<Integer> s = new Stack<>();
            for (int i = 0; i < n; i++) {
                int money = scan.nextInt();
                if (money == 0) {
                    s.pop();
                } else {
                    s.push(money);
                }
            }

            int answer = 0;
            for (int i : s) {
                answer += i;
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
