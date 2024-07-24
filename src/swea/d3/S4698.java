package swea.d3;

import java.util.*;

/**
 * 테네스의 특별한 소수
 *
 * 에라스토테네스의 체로 소수 구하기
 * 구한 소수로 문자열 검증
 *  -- 입력의 하한이 1 이기 때문에 prime[1] = 소수아님을 명시해야함
 */
public class S4698 {

    static int[] prime;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int d = scan.nextInt();
            int a = scan.nextInt();
            int b = scan.nextInt();

            prime = new int[b + 1];
            prime[1] = 1;
            initPrime();

            int answer = 0;
            for (int idx = a; idx <= b; idx++) {
                if (prime[idx] == 0) {
                    if (String.valueOf(idx).contains(String.valueOf(d))) {
                        answer++;
                    }
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void initPrime() {
        for (int i = 2; i < prime.length; i++) {
            if (prime[i] == 0) {
                for (int j = i + i; j < prime.length; j += i) {
                    prime[j] = 1;
                }
            }
        }
    }
}
