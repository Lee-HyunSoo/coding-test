package swea.d3;

import java.util.*;

/**
 * 모음이 보이지 않는 사람
 *
 * 모음 = a e i o u
 * 입력 된 문자열에서 모음만 제외하고 출력
 */
public class S4406 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int tc = 1; tc <= t; tc++) {
            String input = scan.nextLine();

            String answer = "";
            for (char c : input.toCharArray()) {
                if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') {
                    answer += c;
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}