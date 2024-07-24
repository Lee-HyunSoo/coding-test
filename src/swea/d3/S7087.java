package swea.d3;

import java.util.*;

/**
 * 문제 제목 붙이기
 */
public class S7087 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int input = scan.nextInt();
            scan.nextLine();

            Set<Character> words = new HashSet<>();
            for (int idx = 0; idx < input; idx++) {
                words.add(scan.nextLine().charAt(0));
            }

            int answer = 0;
            for (char ch = 'A'; ch <= 'Z'; ch++) {
                if (!words.contains(ch)) {
                    break;
                }
                answer++;
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}