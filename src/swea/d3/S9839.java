package swea.d3;

import java.util.*;

/**
 * 최고의 쌍
 */
public class S9839 {

    static int n;
    static int[] arr;
    static int[] combination;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            n = scan.nextInt();
            arr = new int[n];
            combination = new int[2];
            answer = -1;
            for (int i = 0; i < n; i++) {
                arr[i] = scan.nextInt();
            }

            dfs(0, 0);
            System.out.println("#" + c + " " + answer);
        }
    }

    public static void dfs(int l, int idx) {
        if (l == 2) {
            int multi = combination[0] * combination[1];
            String num = String.valueOf(multi);
            if (num.length() == 1) {
                answer = Math.max(answer, multi);
                return;
            }

            boolean flag = false;
            for (int i = 1; i < num.length(); i++) {
                if (Character.getNumericValue(num.charAt(i)) - Character.getNumericValue(num.charAt(i - 1)) != 1) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                answer = Math.max(answer, multi);
            }
        } else {
            for (int i = idx; i < n; i++) {
                combination[l] = arr[i];
                dfs(l + 1, i + 1);
            }
        }

    }
}
