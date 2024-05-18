package swea.d3;

import java.util.*;

/**
 * 숫자가 같은 배수
 */
public class S14361 {

    static boolean[] visit;
    static char[] save;
    static int n;
    static String num;
    static String answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            n = scan.nextInt();
            num = String.valueOf(n);
            visit = new boolean[num.length()];
            save = new char[num.length()];
            answer = "impossible";

            dfs(0);
            System.out.println("#" + c + " " + answer);
        }
    }

    public static void dfs(int l) {
        if (l == num.length()) {
            String word = "";
            for (int i = 0; i < l; i++) {
                word += save[i];
            }

            int nb = Integer.parseInt(word);
            if (nb > n && nb % n == 0) {
                answer = "possible";
                return;
            }
        }


        for (int i = 0; i < num.length(); i++) {
            if (!visit[i]) {
                visit[i] = true;
                save[l] = num.charAt(i);
                dfs(l + 1);
                visit[i] = false;
            }
        }
    }
}
