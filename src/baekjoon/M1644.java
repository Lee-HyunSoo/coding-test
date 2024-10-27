package baekjoon;

import java.util.*;

/**
 * 소수의 연속합
 *
 * 1. 문제 정리
 * 	1-1. 하나 이상의 연속된 소수의 합으로 나타낼 수 있는 자연수들이 있다. 몇 가지 자연수의 예를 들어 보면 다음과 같다.
 *  	1-1-1. 3 : 3 (한 가지)
 *  	1-1-2. 41 : 2+3+5+7+11+13 = 11+13+17 = 41 (세 가지)
 *  	1-1-3. 53 : 5+7+11+13+17 = 53 (두 가지)
 * 	1-2. 하지만 연속된 소수의 합으로 나타낼 수 없는 자연수들도 있는데, 20이 그 예이다.
 * 	1-3. 7+13을 계산하면 20이 되기는 하나 7과 13이 연속이 아니기에 적합한 표현이 아니다.
 * 	1-4. 한 소수는 반드시 한 번만 덧셈에 사용될 수 있기 때문에, 3+5+5+7과 같은 표현도 적합하지 않다.
 * 	1-5. 자연수가 주어졌을 때, 이 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 구하는 프로그램을 작성하시오.
 *
 * 2. 에라스토테네스의 체를 이용해 소수를 선별한다.
 * 	2-1. 2 ~ i*i 까지 반복문을 돌린다.
 * 	2-2. 해당 수가 소수라면, 그 수의 배수는 전부 소수가 아님으로 체크한다.
 *
 * 3. 선별한 소수를 list 에 담는다.
 *
 * 4. 투 포인터 알고리즘을 사용한다.
 * 	4-1. end 에 해당하는 값을 더한다.
 * 	4-2. 더한 후 n 보다 값이 커지면 작거나 같아질 때 까지 start 를 증가시키며 빼준다.
 * 	4-3. 만약 sum == n 이면 answer 를 추가한다.
 */
public class M1644 {

    static Scanner scan = new Scanner(System.in);
    static int n, answer;
    static List<Integer> primes;

    public static void main(String[] args) {
        init();
        setPrime();
        countSum();
        System.out.println(answer);
    }

    private static void init() {
        n = scan.nextInt();
        primes = new ArrayList<>();
        answer = 0;
    }

    private static void setPrime() {
        // 2. 에라스토테네스의 체를 이용해 소수를 선별한다.
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        // 2-1. 2 ~ i*i 까지 반복문을 돌린다.
        for (int i = 2; i * i <= n; i++) {
            // 2-2. 해당 수가 소수라면, 그 수의 배수는 전부 소수가 아님으로 체크한다.
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        // 3. 선별한 소수를 list 에 담는다.
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) primes.add(i);
        }
    }

    private static void countSum() {
        // 4. 투 포인터 알고리즘을 사용한다.
        int start = 0, sum = 0;
        for (int end = 0; end < primes.size(); end++) {
            // 4-1. end 에 해당하는 값을 더한다.
            sum += primes.get(end);
            // 4-2. 더한 후 n 보다 값이 커지면 작거나 같아질 때 까지 start 를 증가시키며 빼준다.
            while(sum > n) {
                sum -= primes.get(start++);
            }
            // 4-3. 만약 sum == n 이면 answer 를 추가한다.
            if (sum == n) {
                answer++;
            }
        }
    }
}
