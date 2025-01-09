package codeplus.math;

import java.util.Scanner;

/**
 * 소수 찾기
 *
 * 1. 문제정리
 *  1-1. 주어진 수 N개 중에서 소수가 몇 개인지 찾아서 출력
 *
 * 2. 풀이
 *  2-1. 입력을 받을 때 소수인지 검증을 해준다.
 *
 * 3. 소수 조건
 *  3-1. 입력받은 수가 1이면 무조건 소수가 아니다.
 *  3-2. 입력받은 수가 2이면 무조건 소수이다.
 *  3-3. 이외의 수의 경우 2 ~ 입력받은 수 - 1 까지 나누며 소수판정을 한다.
 */
public class M1978 {

    static Scanner scan = new Scanner(System.in);
    static int n;

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        n = scan.nextInt();

        int result = 0;
        for (int idx = 0; idx < n; idx++) {
            int input = scan.nextInt();
            // 2-1. 입력을 받을 때 소수인지 검증을 해준다.
            if (isPrime(input)) {
                result++;
            }
        }
        System.out.println(result);
    }

    private static boolean isPrime(int input) {
        // 3-1. 입력받은 수가 1이면 무조건 소수가 아니다.
        if (input == 1) {
            return false;
        }
        // 3-2. 입력받은 수가 2이면 무조건 소수이다.
        if (input == 2) {
            return true;
        }
        // 3-3. 이외의 수의 경우 2 ~ 입력받은 수 - 1 까지 나누며 소수판정을 한다.
        for (int idx = 2; idx < input; idx++) {
            if (input % idx == 0) {
                return false;
            }
        }
        return true;
    }
}
