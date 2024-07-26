package swea.d3;

import java.util.*;

/**
 * 태혁이의 사랑은 타이밍
 *
 * d = 11, h == 11, m < 11 -> -1
 * d = 11, h < 11 -> -1
 *
 * 시작시간 = (11 * 24 * 60) + (11 * 60) + 11
 * 도착시간 = (d * 24 * 60) + (h * 60) + m
 */
public class S4299 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int d = scan.nextInt();
            int h = scan.nextInt();
            int m = scan.nextInt();

            int answer = -1;
            if (d == 11 && h == 11 && m < 11) {
                answer = -1;
            } else if (d == 11 && h < 11) {
                answer = -1;
            } else {
                int start = (11 * 24 * 60) + (11 * 60) + 11;
                int end = (d * 24 * 60) + (h * 60) + m;
                answer = end - start;
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}