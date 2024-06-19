package swea.d3;

import java.util.*;

/**
 * 알 덴테 스파게티
 */
public class S8457 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt(); // n개의 모래시계
            int b = scan.nextInt(); // 면 삶는시간
            int e = scan.nextInt(); // 오차

            int answer = 0;
            for (int i = 0; i < n; i++) {
                int timer = scan.nextInt();

                int remainder = 0;
                if (timer < b) {
                    int share = b / timer;
                    remainder = Math.min(b % timer, (share + 1) * timer - b);
                } else {
                    remainder = timer - b;
                }
                if (remainder <= e) {
                    answer++;
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
