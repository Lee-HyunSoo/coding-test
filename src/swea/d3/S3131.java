package swea.d3;

import java.util.*;

/**
 * 100만 이하의 모든 소수
 *
 * 에라스토테네스의 체
 */
public class S3131 {

    public static void main(String[] args) {
        int[] prime = new int[1000001];
        initPrime(prime);


        for (int i = 2; i <= 1000000; i++) {
            if (prime[i] == 0) {
                System.out.print(i + " ");
            }
        }
    }

    private static void initPrime(int[] prime) {
        for (int i = 2; i <= 1000000; i++) {
            if (prime[i] == 0) { // 0 이면 소수
                for (int j = i * 2; j <= 1000000; j += i) {
                    prime[j] = 1; // 1 이면 소수아님
                }
            }
        }
    }
}