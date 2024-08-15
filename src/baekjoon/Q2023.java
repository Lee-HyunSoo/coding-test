package baekjoon;

import java.util.Scanner;

/**
 * 신기한 소수
 *
 * 1. 입력부
 *   1-1. 7331 -> 7, 73, 733, 7331 모두 소수여야 한다.
 *   1-2. min = 10^(n - 1), max = 10^n
 *
 * 2. 소수의 특징
 *   2-1. 한자리는 2, 3, 5, 7 중 하나가 아니면 소수가 아니다.
 *   2-2. 두자리 이상의 수의 경우 1, 3, 7, 9 로만 끝날 수 있다.
 *
 * 3. 판별
 *   3-1. 10 미만의 수라면 맨 앞자리 수가 2, 3, 5, 7 인지 확인한다.
 *   3-2. 10 이상의 수라면 우선 0 ~ 2 까지 문자열을 자른다.
 *   3-3. 슬라이딩 윈도우를 활용하기 위해 end 를 사용한다.
 *   3-4. 우선 자른 문자열의 맨 앞이 2, 3, 5, 7 인지 확인한다.
 *   3-5. 이후 end 가 1, 3, 7, 9 인지 확인한다.
 *   3-6. 만약 end 가 1, 3, 7, 9 라면 소수임을 판별한다.
 *     3-6-1. 넘어온 수가 짝수면 소수가 아니다.
 *     3-6-2. 홀수라면 3 ~ 루트 num 까지 나눠보며 소수임을 판별한다.
 */
public class Q2023 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int min = (int) Math.pow(10, n - 1);
        int max = (int) Math.pow(10, n);

        Loop:
        for (int num = min; num < max; num++) {
            String parseNum = String.valueOf(num);

            // 3-1. 10 미만의 수라면 맨 앞자리 수가 2, 3, 5, 7 인지 확인한다.
            if (parseNum.length() == 1 && !first(parseNum.charAt(0))) {
                continue;
            }

            // 3-2. 10 이상의 수라면 우선 0 ~ 1 까지 문자열을 자른다.
            int end = 1;
            StringBuilder subNum = new StringBuilder(parseNum.substring(0, end));
            while (end < n) {
                // 3-3. 슬라이딩 윈도우를 활용하기 위해 end 를 사용한다.
                subNum.append(parseNum.charAt(end));

                // 3-4. 우선 자른 문자열의 맨 앞이 2, 3, 5, 7 인지 확인한다.
                if (!first(subNum.charAt(0))) {
                    continue Loop;
                }

                // 3-5. 이후 end 가 1, 3, 7, 9 인지 확인한다.
                if (!last(subNum.charAt(end))) {
                    continue Loop;
                }

                // 3-6. 만약 end 가 1, 3, 7, 9 라면 소수임을 판별한다.
                if (!isPrime(Integer.parseInt(subNum.toString()))) {
                    continue Loop;
                }
                end++;
            }
            System.out.println(num);
        }
    }

    private static boolean first(char num) {
        // 2-1. 한자리는 2, 3, 5, 7 중 하나가 아니면 소수가 아니다.
        return num == '2' || num == '3' || num == '5' || num == '7';
    }

    private static boolean last(char num) {
        // 2-2. 두자리 이상의 수의 경우 1, 3, 7, 9 로만 끝날 수 있다.
        return num == '1' || num == '3' || num == '7' || num == '9';
    }

    private static boolean isPrime(int num) {
        // 3-6-1. 넘어온 수가 짝수면 소수가 아니다.
        // 3-6-2. 홀수라면 3 ~ 루트 num 까지 나눠보며 소수임을 판별한다.
        for (int div = 2; div <= Math.sqrt(num); div++) {
            if (num % div == 0) {
                return false;
            }
        }
        return true;
    }
}
