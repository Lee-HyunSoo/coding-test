package baekjoon;

import java.util.Scanner;

/**
 * 소수 구하기
 */
public class Q1929 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int m = scan.nextInt();
        int n = scan.nextInt();

        boolean[] prime = new boolean[n + 1];
        prime[1] = true; // true 소수 아님 false 소수

        for (int i = 2; i <= n; i++) {
            if (!prime[i]) {
                for (int j = i + i; j <= n; j += i) {
                    prime[j] = true;
                }
            }
        }

//        n = 1000000;
//        i <= n;
//        i -> 1000000;
//        i * i < n;
//        i -> 1000;

//        for (int i = 2; i * i <= n; i++) {
//            if (!prime[i]) {
//                for (int j = i * i; j <= n; j += i) {
//                    prime[j] = true;
//                }
//            }
//        }

        for (int i = m; i <= n; i++) {
            if (!prime[i]) {
                System.out.println(i);
            }
        }
    }
}
