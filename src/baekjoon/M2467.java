package baekjoon;

import java.util.*;

/**
 * 용액
 *
 * 1. 문제정리
 * 	1-1. 정렬 된 배열이 들어온다.
 * 	1-2. 배열은 음수, 양수로 이루어져있다.
 * 	1-3. 두 수를 더해 가장 0 에 가까운 수 2개를 뽑아라.
 * 	1-4. 여러 종류가 있을 시 아무거나 출력해도 된다.
 *
 * 2. 이진탐색
 * 	2-1. start = 0, end = n-1 로 잡는다.
 * 	2-2. start, end 는 둘이 동일할 수 없다. (같은 용액을 고르는 경우는 없음)
 * 	2-3. 때문에 while 문의 조건이 (start < end) 가 된다.
 * 	2-4. 결과가 음수가 나올 수 있기 때문에, 두 수의 합에 절대값을 씌운다.
 * 	2-5. 씌운 값이 현재 최소치보다 작으면 갱신
 * 		2-5-1. 이 때 최소치가 0이면 더 진행할 필요 없으므로 break
 * 	2-6. 결과가 0 보다 작으면 start 를 올린다.
 * 		2-6-1. 정렬 된 배열은 작은 쪽이 음수이기 때문
 *	2-7. 결과가 0 보다 크면 end 를 줄인다.
 */
public class M2467 {

    static Scanner scan = new Scanner(System.in);
    static int n;
    static int[] water;
    static int min;
    static int[] answer;

    public static void main(String[] args) {
        init();
        binarySearch();
        System.out.println(answer[0] + " " + answer[1]);
    }

    private static void binarySearch() {
        // 2-1. start = 0, end = n-1 로 잡는다.
        int start = 0;
        int end = n - 1;

        // 2-2. start, end 는 둘이 동일할 수 없다. (같은 용액을 고르는 경우는 없음)
        // 2-3. 때문에 while 문의 조건이 (start < end) 가 된다.
        while(start < end) {
            int result = water[start] + water[end];

            // 2-4. 결과가 음수가 나올 수 있기 때문에, 두 수의 합에 절대값을 씌운다.
            if (Math.abs(result) < min) {
                // 2-5. 씌운 값이 현재 최소치보다 작으면 갱신
                min = Math.abs(result);
                answer[0] = water[start];
                answer[1] = water[end];

                // 2-5-1. 이 때 최소치가 0이면 더 진행할 필요 없으므로 break
                if (result == 0) {
                    break;
                }
            }

            // 2-6. 결과가 0 보다 작으면 start 를 올린다.
            // 2-7. 결과가 0 보다 크면 end 를 줄인다.
            if (result < 0) {
                start++;
            } else {
                end--;
            }
        }
    }

    private static void init() {
        n = scan.nextInt();
        water = new int[n];
        min = Integer.MAX_VALUE;
        answer = new int[2];
        for (int idx = 0; idx < n; idx++) {
            water[idx] = scan.nextInt();
        }
    }
}
