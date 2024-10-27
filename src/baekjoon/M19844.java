package baekjoon;

import java.util.*;

/**
 * 단어 개수 세기
 *
 * 1. 문제 정리
 * 	1-1. 기본적으로는 띄어쓰기나 -(하이픈) 단위로 단어를 구분한다.
 * 	1-2. 앞 단어가 ce, je, ne, me, te, se, le, la, de, que 혹은 si이고
 * 	1-3. 뒤 단어가 모음(a, e, i, o, u, h)으로 시작하는 경우,
 * 	1-4. 앞 단어의 마지막 모음이 사라지고, 대신 '(어포스트로피)가 붙으면서 이어진다.
 * 	1-5. 프랑스어에서 h는 언제나 묵음이므로, 이 문제에서는 일반적으로 알려진 모음 a, e, i, o, u는 물론이고 h도 모음으로 취급
 *
 * 2. 조건
 * 	2-1. 먼저 띄어쓰기와 -(하이픈)을 기준으로 “단어”를 쪼갠다.
 * 	2-2. 각각의 “단어”에서, 위처럼 줄어들었을 가능성이 있는 경우
 * 		2-2-1. 즉, c', j', n', m', t', s', l', d', qu'로 시작하고 어포스트로피 뒤 글자가 모음인 경우
 * 	2-3. 이 단어들을 한 번 더 분리해 준다.
 */
public class M19844 {

    static Scanner scan = new Scanner(System.in);
    static Set<String> word1, word2;
    static int answer = 0;

    public static void main(String[] args) {
        init();
        // 2-1. 먼저 띄어쓰기와 -(하이픈)을 기준으로 “단어”를 쪼갠다.
        String[] split = scan.nextLine().split(" |-");
        for (String s : split) {
            String[] sp = s.split("'");

            if (sp.length == 1) {
                answer += 1;
            } else {
                String w = String.valueOf(sp[1].charAt(0));
                // 2-2-1. c', j', n', m', t', s', l', d', qu'로 시작하고 어포스트로피 뒤 글자가 모음인 경우
                if (word1.contains(sp[0]) && word2.contains(w)) {
                    // 2-3. 이 단어들을 한 번 더 분리해 준다.
                    answer += 2;
                } else {
                    answer += 1;
                }
            }
        }
        System.out.println(answer);
    }

    private static void init() {
        word1 = new HashSet<>();
        word1.add("c");
        word1.add("j");
        word1.add("n");
        word1.add("m");
        word1.add("t");
        word1.add("s");
        word1.add("l");
        word1.add("l");
        word1.add("d");
        word1.add("qu");
        word1.add("s");

        word2 = new HashSet<>();
        word2.add("a");
        word2.add("e");
        word2.add("i");
        word2.add("o");
        word2.add("u");
        word2.add("h");

        answer = 0;
    }
}
