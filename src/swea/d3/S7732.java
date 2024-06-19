package swea.d3;

import java.util.*;

/**
 * 시간 개념
 */
public class S7732 {

    static int[] times = {3600, 60, 1};
    static int result;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String[] time1 = scan.nextLine().split(":");
            String[] time2 = scan.nextLine().split(":");

            int t1 = 0, t2 = 0;
            for (int i = 0; i < 3; i++) {
                t1 += Integer.parseInt(time1[i]) * times[i];
                t2 += Integer.parseInt(time2[i]) * times[i];
            }
            if (t1 > t2) {
                t2 += 24 * 3600;
            }
            result = t2 - t1;
            String hour = getTimes(3600);
            String min = getTimes(60);
            String sec = getTimes(1);

            System.out.println("#" + c + " " + hour + ":" + min + ":" + sec);
        }
    }

    private static String getTimes(int x) {
        String time = "";
        if (result / x < 10) {
            time = "0" + String.valueOf(result / x);
        } else {
            time = String.valueOf(result / x);
        }
        result -= (result / x) * x;
        return time;
    }
}
