package baekjoon;

import java.util.*;

/**
 * DNA 비밀번호
 *
 * 1. 입력부
 *   1-1. 문자열의 길이 s, 부분 문자열의 길이 p, 문자열 입력
 *   1-2. 부분 문자열에 포함되어야 할 {A, C, G, T} 의 최소 개수
 *   1-3. 문자를 세기위한 words 배열
 *
 * 2. start, end 를 잡고 부분문자열을 하나씩 늘려간다.
 *   2-1. 부분 문자열의 길이가 주어지기 때문에, end = start + p - 1
 *   2-2. start, end 를 사용해 부분문자열을 구한다.
 *   2-3. 부분 문자열을 통해 문자의 개수를 count 한다.
 *   2-4. end 가 s 보다 작을 때까지 반복한다.
 *   2-5. 매번 문자열을 자르면 시간초과가 나기 때문에, 처음에만 자르고 이후 한칸씩만 count 를 줄이고 늘리며 비교한다.
 */
public class M12891 {

    static int[] minCount;
    static int[] words;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int s = scan.nextInt();
        int p = scan.nextInt();
        scan.nextLine();
        String dna = scan.nextLine();

        minCount = new int[4]; // A C G T
        words = new int[4];
        for (int idx = 0; idx < 4; idx++) {
            minCount[idx] = scan.nextInt();
        }

        int answer = 0;
        int start = 0;
        // 2-1. 부분 문자열의 길이가 주어지기 때문에, end = start + p
        int end = start + p;
        // 2-2. start, end 를 사용해 부분문자열을 구한다.
        String subString = dna.substring(start, end);
        // 2-3. 부분 문자열을 통해 문자의 개수를 count 한다.s
        for (char word : subString.toCharArray()) {
            add(word);
        }
        if (isPassword()) {
            answer++;
        }

        // 2-4. end 가 s 보다 작을 때까지 반복한다.
        while (end < s) {
            // 2-5. 매번 문자열을 자르면 시간초과가 나기 때문에, 처음에만 자르고 이후 한칸씩만 줄이고 늘리며 비교한다.
            remove(dna.charAt(start++));
            add(dna.charAt(end++));
            if (isPassword()) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    private static void add(char word) {
        if (word == 'A') {
            words[0]++;
        } else if (word == 'C') {
            words[1]++;
        } else if (word == 'G') {
            words[2]++;
        } else if (word == 'T') {
            words[3]++;
        }
    }

    private static void remove(char word) {
        if (word == 'A') {
            words[0]--;
        } else if (word == 'C') {
            words[1]--;
        } else if (word == 'G') {
            words[2]--;
        } else if (word == 'T') {
            words[3]--;
        }
    }

    private static boolean isPassword() {
        return words[0] >= minCount[0]
                && words[1] >= minCount[1]
                && words[2] >= minCount[2]
                && words[3] >= minCount[3];
    }
}
