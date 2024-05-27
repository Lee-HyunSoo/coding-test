package swea.d3;

import java.util.*;

/**
 * 민정이와 광직이의 알파벳 공부
 */
public class S9480 {

    static String[] words;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            scan.nextLine();

            words = new String[n];
            answer = 0;
            for (int i = 0; i < n; i++) {
                words[i] = scan.nextLine();
            }

            dfs(0, "");
            System.out.println("#" + c + " " + answer);
        }
    }

    private static void dfs(int idx, String s) {
        boolean flag = false;
        for (char c = 'a'; c <= 'z'; c++) {
            if (s.indexOf(c) == -1) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            answer++;
        }
        if (idx == words.length) {
            return;
        }
        for (int i = idx; i < words.length; i++) {
            dfs(i + 1, s + words[i]);
        }
    }
}
