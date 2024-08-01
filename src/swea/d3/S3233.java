package swea.d3;

import java.util.*;

/**
 * 정삼각형 분할 놀이
 *
 * 한 변의 길이가 a 인 정삼각형을 한 변의 길이가 b 인 정삼각형으로 채우기
 * b 는 a 의 약수
 *
 * 3, 1 인경우
 * 1층에 1개
 * 2층에 3개
 * 3층에 5개 ...
 *
 * 4, 2 인경우
 * 1층에 1개
 * 2층에 3개
 *
 * a * a / b * b
 * long 사용
 */
public class S3233 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            long a = scan.nextLong();
            long b = scan.nextLong();

            long answer = (a * a) / (b * b);
            System.out.println("#" + tc + " " + answer);
        }
    }
}
