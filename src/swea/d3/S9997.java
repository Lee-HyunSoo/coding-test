package swea.d3;

import java.util.*;

/**
 * 미니멀리즘 시계
 */
public class S9997 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();

            // 1시간 : 30도, 2분 : 1도
            int hour = n / 30;
            int min = (n % 30) * 2;
            if (hour == 12) {
                hour = 0;
            }

            System.out.println("#" + c + " " + hour + " " + min);
        }
    }
}
