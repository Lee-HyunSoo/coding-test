package swea.d3;

import java.util.*;

/**
 * 북북서
 */
public class S8556 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int tc = 1; tc <= t; tc++) {
            String input = scan.nextLine();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == 'n' || input.charAt(i) == 'w') {
                    sb.append(input.charAt(i));
                }
            }
            char[] ch = sb.reverse().toString().toCharArray();
            double result = (ch[0] == 'n') ? 0.0 : 90.0;
            for (int i = 1; i < ch.length; i++) {
                if (ch[i] == 'n') {
                    result -= 90 / Math.pow(2, i);
                } else {
                    result += 90 / Math.pow(2, i);
                }
            }

            int count = 0; // 분모
            while (true) {
                if (result == (int) result) { // 분모가 1이라면
                    break;
                }
                result *= 2; // 아니면 * 2
                count++; // * 2 한만큼 마지막에 나눠주기 위해 count
            }

            System.out.print("#" + tc + " " + (int) result);
            if (count > 0) {
                System.out.print("/" + (int) Math.pow(2, count));
            }
            System.out.println();
        }
    }
}