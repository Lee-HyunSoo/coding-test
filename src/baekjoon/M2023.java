package baekjoon;

import java.util.Scanner;

/**
 * 신기한 소수
 *
 * 1. 입력부, 조건
 *   1-1. 7331 -> 7, 73, 733, 7331 모두 소수여야 한다.
 *
 * 2. 소수의 특징
 *   2-1. 짝수는 소수가 아니다.
 *   2-2. 한자리는 2, 3, 5, 7 중 하나가 아니면 소수가 아니다.
 *   2-3. 두자리 이상의 수의 경우 1, 3, 7, 9 로만 끝날 수 있다.
 *
 * 3. 판별
 *   3-1. 메모리 초과를 방지하기 위해 StringBuilder 를 사용한다.
 *   3-2. {2, 3, 5, 7} 중 하나를 맨 앞에 넣는다.
 *   3-3. {1, 3, 7, 9} 를 통해 중복순열을 구한다.
 *
 * 4. 종료 조건
 *   4-1. 부분수열 또한 소수여야 하기 때문에, 부분수열이 소수가 아니라면 return 한다.
 *   4-2. 맨 앞의 수를 이미 정했기 때문에 n-1 크기의 중복순열을 구한다.
 *   4-3. n-1 크기의 중복순열을 구했다면, 소수여부를 판단하고 출력 후 return 한다.
 */
public class M2023 {

    static int n;
    static int[] firstNum = {2, 3, 5, 7};
    static int[] lastNum = {1, 3, 7, 9};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();

        for (int idx = 0; idx < 4; idx++) {
            // 3-1. 메모리 초과를 방지하기 위해 StringBuilder 를 사용한다.
            StringBuilder subNum = new StringBuilder();
            // 3-2. {2, 3, 5, 7} 중 하나를 맨 앞에 넣는다.
            subNum.append(firstNum[idx]);
            permutation(0, subNum);
        }
    }

    private static void permutation(int level, StringBuilder subNum) {
        // 4-1. 부분수열 또한 소수여야 하기 때문에, 부분수열이 소수가 아니라면 return 한다.
        if (!isPrime(subNum)) {
            return;
        }

        // 4-2. 맨 앞의 수를 이미 정했기 때문에 n-1 크기의 중복순열을 구한다.
        // 4-3. n-1 크기의 중복순열을 구했다면, 소수여부를 판단하고 출력 후 return 한다.
        if (level == n - 1) {
            if (isPrime(subNum)) {
                System.out.println(subNum);
                return;
            }
        }

        // 3-3. {1, 3, 7, 9} 를 통해 중복순열을 구한다.
        for (int i = 0; i < 4; i++) {
            subNum.append(lastNum[i]);
            permutation(level + 1, subNum);
            subNum.delete(subNum.length() - 1, subNum.length());
        }
    }

    private static boolean isPrime(StringBuilder subNum) {
        int num = Integer.parseInt(subNum.toString());
        for (int idx = 2; idx <= Math.sqrt(num); idx++) {
            if (num % idx == 0) {
                return false;
            }
        }
        return true;
    }
}
