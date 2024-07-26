package swea.d3;

import java.util.*;

/**
 * 세상의 모든 팰린드롬
 *
 * 입력 / 뒤집은 입력
 *  -- 처음에 비교, 둘이 같지 않으면 false
 *  -- 둘이 같지 않은 경우, 문자열에 ? 가 없다면 검사할 필요 x
 *  -- ? 가 있는 경우만 비교 시작
 *  -- 문자열을 뒤집어서 비교했을 때, ? 가 하나라도 없는 index 를 모아 새 문자열 생성
 *  -- 새로 생성한 문자열이 팰린드롬이면 true, 아니면 false
 */
public class S4522 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int tc = 1; tc <= t; tc++) {
            String input = scan.nextLine();
            String reverse = new StringBuilder(input).reverse().toString();

            String answer = "Not exist";
            if (input.equals(reverse)) {
                answer = "Exist";
            } else {
                if (input.contains("?")) {
                    String result = "";
                    for (int idx = 0; idx < input.length(); idx++) {
                        if (input.charAt(idx) != '?' && reverse.charAt(idx) != '?') {
                            result += input.charAt(idx);
                        }
                    }
                    reverse = new StringBuilder(result).reverse().toString();
                    if (result.equals(reverse)) {
                        answer = "Exist";
                    }
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}
