package swea.d3;

import java.util.*;

/**
 * 오타
 *
 * 경우의 수 사용
 * 각 index 별로 글자를 비교, 다른 글자이면 경우의 수가 증가
 *  -- 0, 마지막 index 는 자신과 같으면 1, 다르면 2
 *  -- 나머지 index 는 3개의 값을 비교해서 다른 글자 수만큼이 경우의 수
 */
public class S7853 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int tc = 1; tc <= t; tc++) {
            char[] ch = scan.nextLine().toCharArray();

            long answer = 1;
            for (int i = 1; i < ch.length - 1; i++) {
                Set<Character> set = new HashSet<>();
                set.add(ch[i - 1]);
                set.add(ch[i]);
                set.add(ch[i + 1]);
                answer = answer * set.size() % 1000_000_007;
            }

            answer = (ch[0] != ch[1]) ? answer * 2 % 1000_000_007 : answer;
            answer = (ch[ch.length - 2] != ch[ch.length - 1]) ? answer * 2 % 1000_000_007 : answer;
            System.out.println("#" + tc + " " + answer);
        }
    }
}