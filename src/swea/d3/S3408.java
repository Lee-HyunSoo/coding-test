package swea.d3;

import java.util.*;

/**
 * 세가지 합 구하기
 *
 * s1 = 양의 정수 중 작은 순서대로 n 개의 합
 * s2 = 양의 정수 중 홀수인 것 중 작은 순서대로 n 개의 합
 * s3 = 양의 정수 중 짝수인 것 중 작은 순서대로 n 개의 합
 *
 * n == 5
 * s1 = 1 + 2 + 3 + 4 + 5 == 15 -> n(n+1) / 2
 * s2 = 1 + 3 + 5 + 7 + 9 == 25 -> n(n+1) - n
 * s3 = 2 + 4 + 6 + 8 + 10 == 30 -> n(n+1)
 *
 * 숫자가 커질수도 있으니 long 사용
 */
public class S3408 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            long n = scan.nextLong();
            long s1 = n * (n + 1) / 2;
            long s2 = n * (n + 1) - n;
            long s3 = n * (n + 1);
            System.out.println("#" + tc + " " + s1 + " " + s2 + " " + s3);
        }
    }
}