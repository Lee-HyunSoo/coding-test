package swea.d3;

import java.util.*;

/**
 * 의석이의 세로로 말해요
 *
 * 입력 된 String 을 세로로 읽음
 * index = 0, index < String 의 길이 일 때만 체크
 */
public class S5356 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int tc = 1; tc <= t; tc++) {
            List<String> words = new ArrayList<>();

            int max = 0;
            for (int i = 0; i < 5; i++) {
                String input = scan.nextLine();
                words.add(input);
                max = Math.max(max, input.length());
            }

            StringBuilder answer = new StringBuilder();
            for (int row = 0; row < max; row++) {
                for (String word : words) {
                    if (row < word.length()) {
                        answer.append(String.valueOf(word.charAt(row)));
                    }
                }
            }
            System.out.println("#" + tc + " " + answer.toString());
        }
    }
}
