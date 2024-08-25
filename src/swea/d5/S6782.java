package swea.d5;

import java.util.*;

/**
 * 현주가 좋아하는 제곱근 놀이
 *
 * 1. 문제 정리
 * 	1-1. 2이상의 정수 n 이 있다.
 * 	1-2. n 을 n + 1 로 바꿀 수 있다.
 * 	1-3. 루트 n 이 정수일 때 n 을 루트 n 으로 바꿀 수 있다.
 *
 * 2. 반복문 내에서 연산한다.
 * 	2-1. 루트 n 이 정수일 때 n 을 루트 값으로 바꾼다.
 * 	2-2. 아닌 경우 해당 숫자를 루트 n 값이 정수일 때까지 n += 1 을 해줘야한다.
 * 		2-2-1. 5 일 때
 * 		2-2-2. 9 까지는 가야 루트를 쓸 수 있음
 * 		2-2-3. 때문에 9 - 5 만큼 answer 에 더해준다.
 * 		2-2-4. 이후 n 을 루트 9 의 값으로 설정
 * 	2-3. n 을 변경 후 answer 를 1 증가시킨다.
 */
public class S6782 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int tc = 1; tc <= t; tc++) {
            long n = scan.nextLong();
            long answer = 0;

            while (true) {
                if (n == 2) {
                    break;
                }

                long sqrt = (long) Math.sqrt(n);
                if (sqrt * sqrt == n) {
                    // 2-1. 루트 n 이 정수일 때 n 을 루트 값으로 바꾼다.
                    n = sqrt;
                } else {
                    // 2-2. 아닌 경우 해당 숫자를 루트 n 값이 정수일 때까지 n += 1 을 해줘야한다.
                    // 2-2-1. 5 일 때
                    // 2-2-2. 9 까지는 가야 루트를 쓸 수 있음
                    // 2-2-3. 때문에 9 - 5 만큼 answer 에 더해준다.
                    answer += (sqrt + 1) * (sqrt + 1) - n;
                    // 2-2-4. 이후 n 을 루트 9 의 값으로 설정
                    n = sqrt + 1;
                }
                // 2-3. n 을 변경 후 answer 를 1 증가시킨다.
                answer++;
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}