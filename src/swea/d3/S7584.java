package swea.d3;

import java.util.*;

/**
 * 자기 복제 문자열
 *
 * 4의 배수는 0
 * 4의 배수가 아닌 짝수는 1
 * 홀수 -> f(n) = f((n - 1) / 2)
 *
 * 0 0 1 0 0 1 1 0 0 0 1  1  0  1  1
 * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14
 */
public class S7584 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int tc = 1; tc <= t; tc++) {
            long k = scan.nextLong() - 1; // index - 1

            int answer = 0;
            while (k > 0) {
                if (k % 2 == 1) {
                    k = (k - 1) / 2;
                } else {
                    if (k % 4 == 0) {
                        answer = 0;
                        break;
                    }
                    answer = 1;
                    break;
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}