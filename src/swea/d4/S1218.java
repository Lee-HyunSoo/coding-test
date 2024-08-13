package swea.d4;

import java.util.*;

/**
 * [S/W 문제해결 기본] 4일차 - 괄호 짝짓기
 *
 * 1. 4종류의 괄호 문자들 입력
 *  1-1. (), [], {}, <>
 *
 * 2. 괄호의 짝이 모두 맞는지 판별
 *  2-1. 4종류의 stack
 *  2-2. 여는 괄호가 나올 시 해당 stack push
 *  2-3. 닫는 괄호가 나올 시 해당 stack pop
 *
 * 3. 사전 조건 : stack 이 null 인데 닫는 괄호가 나왔을 경우
 *  3-1. 출력 후 다음 테스트 케이스로 돌아간다.
 *
 * 4. 결과 검증 : 4종류의 stack 중 하나라도 비지 않은 stack 이 있다면 0
 */
public class S1218 {

    static Deque<Character> parenthesis; // ()
    static Deque<Character> brace; // {}
    static Deque<Character> bracket; // []
    static Deque<Character> chevron; // <>
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Loop:
        for (int tc = 1; tc <= 10; tc++) {
            int length = scan.nextInt();
            scan.nextLine();
            // 1. 4종류의 괄호 문자들 입력
            String input = scan.nextLine();

            parenthesis = new ArrayDeque<>(); // ()
            brace = new ArrayDeque<>(); // {}
            bracket = new ArrayDeque<>(); // []
            chevron = new ArrayDeque<>(); // <>

            answer = 0;
            // 2. 괄호의 짝이 모두 맞는지 판별
            for (char ch : input.toCharArray()) {
                // 2-2. 여는 괄호가 나올 시 해당 stack push
                if (ch == '(' || ch == '{' || ch == '[' || ch == '<') {
                    open(ch);
                } else {
                    // 2-3. 닫는 괄호가 나올 시 해당 stack pop
                    if (!close(ch)) {
                        // 3. 사전 조건 : stack 이 null 인데 닫는 괄호가 나왔을 경우
                        // 3-1. 출력 후 다음 테스트 케이스로 돌아간다.
                        System.out.println("#" + tc + " " + answer);
                        continue Loop;
                    }
                }
            }

            // 4. 결과 검증 : 4종류의 stack 이 모두 비어있다면 1
            verify();

            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void open(char ch) {
        if (ch == '(') {
            parenthesis.push(ch);
        } else if (ch == '{') {
            brace.push(ch);
        } else if (ch == '[') {
            bracket.push(ch);
        } else if (ch == '<') {
            chevron.push(ch);
        }
    }

    private static boolean close(char ch) {
        if (ch == ')') {
            // 3. 사전 조건 : stack 이 null 인데 닫는 괄호가 나왔을 경우
            if (parenthesis.isEmpty()) {
                return false;
            } else {
                parenthesis.pop();
            }
        } else if (ch == '}') {
            // 3. 사전 조건 : stack 이 null 인데 닫는 괄호가 나왔을 경우
            if (brace.isEmpty()) {
                return false;
            } else {
                brace.pop();
            }
        } else if (ch == ']') {
            // 3. 사전 조건 : stack 이 null 인데 닫는 괄호가 나왔을 경우
            if (bracket.isEmpty()) {
                return false;
            } else {
                bracket.pop();
            }
        } else if (ch == '>') {
            // 3. 사전 조건 : stack 이 null 인데 닫는 괄호가 나왔을 경우
            if (chevron.isEmpty()) {
                return false;
            } else {
                chevron.pop();
            }
        }
        return true;
    }

    private static void verify() {
        if (parenthesis.isEmpty() && brace.isEmpty() && bracket.isEmpty() && chevron.isEmpty()) {
            answer = 1;
        }
    }
}
