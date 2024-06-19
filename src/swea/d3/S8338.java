package swea.d3;

import java.util.*;

/**
 * 계산기
 */
public class S8338 {

    static int answer;
    static int[] arr;
    static char[] ope;
    static char[] operator = {'+', '*'};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            arr = new int[n];
            ope = new char[n - 1];
            answer = 0;

            for (int i = 0; i < n; i++) {
                arr[i] = scan.nextInt();
            }
            dfs(0);
            System.out.println("#" + c + " " + answer);
        }
    }

    private static void dfs(int l) {
        if (l == ope.length) {
            int result = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (ope[i - 1] == '+') {
                    result += arr[i];
                } else {
                    result *= arr[i];
                }
            }
            answer = Math.max(answer, result);
        } else {
            for (int i = 0; i < operator.length; i++) {
                ope[l] = operator[i];
                dfs(l + 1);
            }
        }
    }
}
