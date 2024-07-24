package swea.saffy.d1;

import java.util.*;

/**
 * 중간값 찾기
 */
public class D1Q1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = scan.nextInt();
        }
        Arrays.sort(arr);
        System.out.println(arr[(n / 2) + 1]);
    }
}
