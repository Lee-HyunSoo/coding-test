package swea.d3;

import java.util.*;

/**
 * 세상의 모든 팰린드롬 2
 *
 * 팰린드롬 = 뒤집어도 같은 문자가 되는 것
 * 알파벳 소문자와 '*' 를 사용해 단어 매칭 가능
 *  -- a*a -> aa, ana, appa ...
 *  -- * 은 길이 0 이상인 모든문자
 *
 * 입력 된 문자가 * 을 포함하지 않을 때
 *  -- 뒤집어서 팰린드롬이면 true, 아니면 false
 * 입력 된 문자가 * 을 포함할 때
 *  -- abc*daa / abc*a
 *  -- 뒤집었을 때 같지 않은 문자만 추출 (bc*da / bc*)
 *  -- * 이 양쪽 사이드 중 하나라도 없으면 false
 */
public class S4579 {

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
                // 별이 포함안되있는데 뒤집었을 때 다르면 검사할 필요 x
                if (input.contains("*")) {
                    String result = "";
                    for (int idx = 0; idx < input.length(); idx++) {
                        if (input.charAt(idx) != reverse.charAt(idx)) {
                            result += input.charAt(idx);
                        }
                    }
                    if (result.length() != 0 && (result.charAt(0) == '*' || result.charAt(result.length() - 1) == '*')) {
                        answer = "Exist";
                    }
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}
