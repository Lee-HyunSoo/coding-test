package swea.d2;

import java.util.Scanner;

/**
 * 지그재그 숫자
 */
public class S1986 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int i = 1; i <= t; i++) {
            int input = scan.nextInt();

            int answer = 0;
            for (int j = 1; j <= input; j++) {
                if (j % 2 == 0) {
                    answer -= j;
                } else {
                    answer += j;
                }
            }
            System.out.println("#" + i + " " + answer);
        }
    }
}
