package baekjoon;

import java.util.Scanner;

/**
 * 괄호 추가하기
 *
 * 1. 문제정리
 *  1-1. 괄호를 중첩되지 않게 추가해서 최대값을 구하자.
 *  1-2. 결과가 음수가 될 수 있기 때문에, answer 의 초기값을 Integer.MIN_VALUE 로 한다.
 *
 * 2. 완전탐색
 *  2-1. 괄호를 앞에 걸거나, 뒤에 걸거나의 경우로 탐색
 *  2-2. 이 때 1차 탐색 이후 2차 탐색 시 index error 에 주의한다.
 */
public class M16637 {

    static Scanner scan = new Scanner(System.in);
    static int n;
    static String expression;
    static int answer;

    public static void main(String[] args) {
        init();
        dfs(1, expression.charAt(0) - '0');
        System.out.println(answer);
    }

    private static void dfs(int idx, int curr) {
        if (idx >= expression.length()) {
            answer = Math.max(answer, curr);
            return;
        }
        // 2-1. 괄호를 걸거나, 안걸거나의 경우로 탐색
        if (expression.charAt(idx) == '+') {
            // 괄호를 앞에 건 경우
            dfs(idx + 2, curr + (expression.charAt(idx + 1) - '0'));
            // 2-2. 이 때 1차 탐색 이후 2차 탐색 시 index error 에 주의한다.
            if (idx + 2 >= expression.length()) {
                return;
            }
            // 괄호를 뒤에 건 경우
            dfs(idx + 4, curr + getResult(idx));

        } else if (expression.charAt(idx) == '-') {
            dfs(idx + 2, curr - (expression.charAt(idx + 1) - '0'));
            if (idx + 2 >= expression.length()) {
                return;
            }
            dfs(idx + 4, curr - getResult(idx));

        } else if (expression.charAt(idx) == '*') {
            dfs(idx + 2, curr * (expression.charAt(idx + 1) - '0'));
            if (idx + 2 >= expression.length()) {
                return;
            }
            dfs(idx + 4, curr * getResult(idx));
        }
    }

    private static int getResult(int idx) {
        int result = expression.charAt(idx + 1) - '0';
        if (expression.charAt(idx + 2) == '+') {
            result += expression.charAt(idx + 3) - '0';
        } else if (expression.charAt(idx + 2) == '-') {
            result -= expression.charAt(idx + 3) - '0';
        } else if (expression.charAt(idx + 2) == '*') {
            result *= expression.charAt(idx + 3) - '0';
        }
        return result;
    }

    private static void init() {
        n = scan.nextInt();
        expression = scan.next();
        // 1-2. 결과가 음수가 될 수 있기 때문에, answer 의 초기값을 Integer.MIN_VALUE 로 한다.
        answer = Integer.MIN_VALUE;
    }
}
