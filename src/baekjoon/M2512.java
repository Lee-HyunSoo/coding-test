package baekjoon;

import java.util.Scanner;

/**
 * 예산
 *
 * 1. 입력 값 중 최대 값을 정답으로 설정해둔다.
 * 2. 입력 값의 합이 최대 예산보다 작으면 초기 설정한 정답 출력
 * 3. 입력 값의 합이 최대 예산보다 크면 이분탐색 진행
 */
public class M2512 {

    static Scanner scan = new Scanner(System.in);
    static int n, m;
    static int[] city;
    static int answer;

    private static void init() {
        n = scan.nextInt();
        city = new int[n];
        answer = 0;
        for (int idx = 0; idx < n; idx++) {
            city[idx] = scan.nextInt();
            // 1. 입력 값 중 최대 값을 정답으로 설정해둔다.
            answer = Math.max(answer, city[idx]);
        }
        m = scan.nextInt();
    }

    private static int currentTotal() {
        int sum = 0;
        for (int budget : city) {
            sum += budget;
        }
        return sum;
    }

    private static int binarySearch() {
        int start = 0;
        int end = m;

        while (start <= end) {
            int mid = (start + end) / 2; // 상한액

            int sum = 0;
            for (int budget : city) {
                sum += Math.min(budget, mid);
            }
            if (sum <= m) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }


    public static void main(String[] args) {
        init();
        // 2. 입력 값의 합이 최대 예산보다 작으면 초기 설정한 정답 출력
        // 3. 입력 값의 합이 최대 예산보다 크면 이분탐색 진행
        if (currentTotal() > m) {
            answer = binarySearch();
        }
        System.out.println(answer);
    }
}
