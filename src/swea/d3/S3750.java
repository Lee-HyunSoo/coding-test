package swea.d3;

import java.util.*;

/**
 * Digit sum
 *
 * f(n) = 각 자릿수를 더한 값
 * n 이 한자릿수가 될 때까지 반복
 */
public class S3750 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int tc = 1; tc <= t; tc++) {
            String n = scan.nextLine();


            int answer;
            while(true) {
                answer = 0;
                for (char c : n.toCharArray()) {
                    answer += Character.getNumericValue(c);
                }

                if (answer < 10) {
                    break;
                }
                n = String.valueOf(answer);
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}