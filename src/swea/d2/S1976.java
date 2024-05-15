package swea.d2;

import java.util.Scanner;

/**
 * 시각 덧셈
 */
public class S1976 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int i = 1; i <= t; i++) {
            int hour1 = scan.nextInt();
            int min1 = scan.nextInt();
            int hour2 = scan.nextInt();
            int min2 = scan.nextInt();

            int hour = hour1 + hour2;
            int min = min1 + min2;

            if (min >= 60) {
                hour += min / 60;
                min = min % 60;
            }

            if (hour > 12) {
                hour = hour % 12;
            }
            if (hour == 0) {
                hour = 12;
            }
            System.out.println("#" + i + " " + hour + " " + min);
        }
    }
}
