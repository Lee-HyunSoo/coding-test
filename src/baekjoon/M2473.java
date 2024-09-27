package baekjoon;

import java.util.*;

/**
 * 세 용액
 *
 * 1. 문제정리
 * 	1-1. 정렬 된 배열이 들어온다.
 * 	1-2. 배열은 음수, 양수로 이루어져있다.
 * 	1-3. 두 수를 더해 가장 0 에 가까운 수 2개를 뽑아라.
 * 	1-4. 여러 종류가 있을 시 아무거나 출력해도 된다.
 * 	1-5. 이진 탐색을 위해 입력 배열을 정렬한다.
 *
 * 2. 이진탐색
 * 	2-1. 3개를 탐색해야 하기 때문에 먼저 start 를 정한다.
 * 		2-1-1. 이 때 3개는 고를 수 있어야하기 때문에 0 ~ n-2 까지 반복한다.
 * 	2-2. mid = start + 1, end = n - 1 로 한다.
 * 		2-2-1. 기존에 start ~ end 로 탐색하던 것이 mid ~ end 로 바뀐 것
 * 	2-3. 세 수의 합이 min 보다 작다면 갱신해준다.
 * 		2-3-1. 이 때 값이 0 이면 break
 * 	2-4. result 가 0 보다 작으면 mid 를 올린다.
 * 	2-5. result 가 0 보다 크면 end 를 내린다.
 *
 * 3. 값이 int 범위를 넘기 때문에 long 으로 결과를 계산한다.
 */
public class M2473 {

    static Scanner scan = new Scanner(System.in);
    static int n;
    static long[] water;
    static long min;
    static long[] answer;

    public static void main(String[] args) {
        init();
        binarySearch();
        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
    }

    private static void binarySearch() {
        // 2-1. 3개를 탐색해야 하기 때문에 먼저 start 를 정한다.
        // 2-1-1. 이 때 3개는 고를 수 있어야하기 때문에 0 ~ n-2 까지 반복한다.
        for (int start = 0; start < n - 2; start++) {
            // 2-2. mid = start + 1, end = n - 1 로 한다.
            // 2-2-1. 기존에 start ~ end 로 탐색하던 것이 mid ~ end 로 바뀐 것
            int mid = start + 1;
            int end = n - 1;

            // 2-3. 세 수의 합이 min 보다 작다면 갱신해준다.
            while(mid < end) {
                // 3. 값이 int 범위를 넘기 때문에 long 으로 결과를 계산한다.
                long result = water[start] + water[mid] + water[end];
                if (Math.abs(result) < min) {
                    min = Math.abs(result);
                    answer[0] = water[start];
                    answer[1] = water[mid];
                    answer[2] = water[end];

                    // 2-3-1. 이 때 값이 0 이면 break
                    if (result == 0) {
                        break;
                    }
                }

                // 2-4. result 가 0 보다 작으면 mid 를 올린다.
                // 2-5. result 가 0 보다 크면 end 를 내린다.
                if (result < 0) {
                    mid++;
                } else {
                    end--;
                }
            }
        }
    }

    private static void init() {
        n = scan.nextInt();
        water = new long[n];
        min = Long.MAX_VALUE;
        answer = new long[3];
        for (int idx = 0; idx < n; idx++) {
            water[idx] = scan.nextLong();
        }
        // 1-5. 이진 탐색을 위해 입력 배열을 정렬한다.
        Arrays.sort(water);
    }
}
