package swea.d3;

import java.util.*;

/**
 * 새샘이와 세 소수
 *
 * 5 보다 큰 홀수 N
 * N 을 세 소수의 합으로 나타낼 수 있는 경우의 수
 *  -- 에라스토테네스의 체로 2 ~ 1000 까지의 소수 설정
 *  -- 2 ~ N 까지의 소수 선택, 중복조합
 *  -- 중복 조합 - 중복을 허용해야 하기 때문에 dfs(level + 1, idx + 1) 이 아니라
 *  -- dfs(level + 1, idx) 로 중복 탐색을 해야한다.
 */
public class S5986 {

    static int n;
    static List<Integer> prime;
    static int[] select;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] primes = new int[1001];
        initPrime(primes);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = scan.nextInt();

            prime = new ArrayList<>();
            select = new int[3];
            answer = 0;
            for (int i = 2; i <= n; i++) {
                if (primes[i] == 0) {
                    prime.add(i);
                }
            }

            dfs(0, 0);
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void dfs(int level, int idx) {
        if (level == 3) {
            int sum = 0;
            for (int s : select) {
                sum += s;
            }
            if (sum == n) {
                answer++;
            }
            return;
        }

        for (int i = idx; i < prime.size(); i++) {
            select[level] = prime.get(i);
            dfs(level + 1, i);
        }
    }

    private static void initPrime(int[] primes) {
        for (int i = 2; i <= 1000; i++) {
            if (primes[i] == 0) {
                for (int j = i * 2; j <= 1000; j += i) {
                    primes[j] = 1;
                }
            }
        }
    }
}