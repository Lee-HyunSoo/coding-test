package baekjoon;

import java.util.*;

/**
 * 소인수분해
 *
 * 1. 문제 정리
 * 	1-1. 정수 N이 주어졌을 때, 소인수분해하는 프로그램을 작성하시오.
 * 	1-2. 첫째 줄에 정수 N (1 ≤ N ≤ 10,000,000)이 주어진다.
 * 	1-3. N의 소인수분해 결과를 한 줄에 하나씩 오름차순으로 출력한다. N이 1인 경우 아무것도 출력하지 않는다.
 *
 * 2. 에라스토 테네스의 체를 사용해 소수를 생성한다.
 *
 * 3. 생성한 소수로 나눠진다면
 * 	3-1. 해당 소수로 더이상 나눌 수 없을 때까지 나누며 answer 에 추가한다.
 *
 * 4. answer 를 정렬한다.
 */
public class M11653 {

    static Scanner scan = new Scanner(System.in);
    static int n;
    static boolean[] isPrime;
    static List<Integer> answer;

    public static void main(String[] args) {
        init();
        divide();
        for (int ans : answer) {
            System.out.println(ans);
        }
    }

    private static void init() {
        n = scan.nextInt();

        // 2. 에라스토 테네스의 체를 사용해 소수를 생성한다.
        isPrime = new boolean[n + 1];
        for (int idx = 2; idx <= n; idx++) {
            isPrime[idx] = true;
        }
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        answer = new ArrayList<>();
    }

    private static void divide() {
        int num = n;
        for (int idx = 2; idx <= n; idx++) {
            if (isPrime[idx]) {
                // 3. 생성한 소수로 나눠진다면
                while(num % idx == 0) {
                    // 3-1. 해당 소수로 더이상 나눌 수 없을 때까지 나누며 answer 에 추가한다.
                    num /= idx;
                    answer.add(idx);
                }
            }
        }
        // 4. answer 를 정렬한다.
        answer.sort(null);
    }
}
