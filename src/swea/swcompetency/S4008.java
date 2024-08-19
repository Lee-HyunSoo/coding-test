package swea.swcompetency;

import java.util.*;

/**
 * [모의 SW 역량테스트] 숫자 만들기
 *
 * 1. 입력
 * 	1-1. +, -, *, / 를 저장할 배열을 만든다.
 * 	1-2. n 크기의 숫자 배열
 * 	1-3. n-1 크기의 연산자 배열
 * 	1-4. 최대, 최소 값
 * 	1-5. 결과가 음수가 나올 수 있기 때문에 최대값의 초기 값을 Integer.MAX_VALUE + 1 로해 가장 작은 음수로 한다.
 *
 * 2. 순열
 * 	2-1. 연산자를 다 소모한 상태면 더이상 사용할 수 없다.
 * 	2-2. 연산자를 사용할 수 있다면, 하나 사용 후 재귀
 * 	2-3. 재귀에서 돌아온 후 사용하지 않은 상태로 변경
 *
 * 3. 종료 조건
 * 	3-1. 연산자 배열 크기의 끝에 도달했다면,
 * 	3-2. numbers 배열과 연산자 배열을 통해 연산
 * 	3-3. 연산 결과를 통해 min, max 를 구한다.
 */
public class S4008 {

    static int n;
    static int[] operators;
    static int[] numbers;
    static int[] permu;
    static int min, max;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = scan.nextInt();
            // 1-1. +, -, *, / 를 저장할 배열을 만든다.
            operators = new int[4];
            // 1-2. n 크기의 숫자 배열
            numbers = new int[n];
            // 1-3. n-1 크기의 연산자 배열
            permu = new int[n - 1];
            // 1-4. 최대, 최소 값
            // 1-5. 결과가 음수가 나올 수 있기 때문에 최대값의 초기 값을 Integer.MAX_VALUE + 1 로해 가장 작은 음수로 한다.
            min = Integer.MAX_VALUE;
            max = Integer.MAX_VALUE + 1;

            for (int idx = 0; idx < 4; idx++) {
                operators[idx] = scan.nextInt();
            }
            for (int idx = 0; idx < n; idx++) {
                numbers[idx] = scan.nextInt();
            }

            permutation(0);

            System.out.println("#" + tc + " " + (max - min));
        }
    }

    private static void permutation(int level) {
        // 3-1. 연산자 배열 크기의 끝에 도달했다면,
        if (level == n - 1) {
            // 3-2. numbers 배열과 연산자 배열을 통해 연산
            calc();
            return;
        }

        for (int idx = 0; idx < 4; idx++) {
            // 2-1. 연산자를 다 소모한 상태면 더이상 사용할 수 없다.
            if (operators[idx] > 0) {
                // 2-2. 연산자를 사용할 수 있다면, 하나 사용 후 재귀
                operators[idx]--;
                permu[level] = idx;
                permutation(level + 1);
                // 2-3. 재귀에서 돌아온 후 사용하지 않은 상태로 변경
                operators[idx]++;
            }
        }
    }

    private static void calc() {
        int result = numbers[0];
        for (int idx = 1; idx < n; idx++) {
            if (permu[idx - 1] == 0) {
                result += numbers[idx];
            } else if (permu[idx - 1] == 1) {
                result -= numbers[idx];
            } else if (permu[idx - 1] == 2) {
                result *= numbers[idx];
            } else if (permu[idx - 1] == 3) {
                result /= numbers[idx];
            }
        }
        // 3-3. 연산 결과를 통해 min, max 를 구한다.
        min = Math.min(min, result);
        max = Math.max(max, result);
    }
}
