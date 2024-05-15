package swea.d2;

import java.util.Scanner;

/**
 * 간단한 369 게임
 */
public class S1926 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            String num = String.valueOf(i);

            boolean flag = false;
            for (char c : num.toCharArray()) {
                if (c != '0' && c % 3 == 0) {
                    answer.append("-");
                    flag = true;
                }
            }

            if (!flag) {
                answer.append(num);
            }
            answer.append(" ");
        }
        System.out.println(answer.toString());
    }
}
