package swea.d3;

import java.util.*;

/**
 * 홀수 피라미드
 */
public class S8016 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            long n = scan.nextLong();

            long cnt = n + n - 1;
            long end = cnt * n + (cnt - n);
            long start = end - ((cnt - 1) * 2);
            System.out.println("#" + c + " " + start + " " + end);
        }
    }
}

/*
(n + n - 1)개
1층 - 1개 -> 1 -> 1: 1 * 1 + 0
2층 - 3개 -> 3 ~ 7 -> 7: 3 * 2 + 1
3층 - 5개 -> 9 ~ 17 -> 17: 5 * 3 + 2
4층 - 7개 -> 19 ~ 31 -> 31: 7 * 4 + 3
마지막수 = 개수 * n + (개수 - n)
처음수 = 마지막수 - (개수 - 1) * 2
 */