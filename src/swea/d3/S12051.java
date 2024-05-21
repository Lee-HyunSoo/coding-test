package swea.d3;

import java.util.*;

/**
 * 프리셀 통계
 */
public class S12051 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            long n = scan.nextLong();
            int pd = scan.nextInt(); // 오늘 승률
            int pg = scan.nextInt(); // 전체 승률

            String answer = "Possible";
            if (pd != 0 && pg == 0) {
                answer = "Broken";
            } else if (pd != 100 && pg == 100) {
                answer = "Broken";
            } else {
                boolean flag = false;
                for (long d = 1; d <= n; d++) {
                    double win = (double) d * pd / 100;
                    if (win % 1 == 0) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    answer = "Broken";
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
