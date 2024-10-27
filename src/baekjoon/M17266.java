package baekjoon;

import java.util.*;

/**
 * 어두운 굴다리
 *
 * 1. 높이를 이분탐색으로 찾는다.
 * 2. 높이가 정해지면 가로등을 설치해본다.
 * 3. 왼쪽 pos 를 기준으로 계산한다.
 * 	3-1. 현재 위치를 기준으로 가로등이 비출 수 있는 left, right 를 구한다.
 * 	3-2. 이전 right 보다 현재 left 가 크면 비출 수 없다는 뜻
 * 	3-3. 끝까지 갔을 때 가장 마지막 지점이 n 보다 크거나 같다면 설치 가능
 * 	3-4. 작다면 다 비출 수 없다는 뜻
 */
public class M17266 {

    static Scanner scan = new Scanner(System.in);
    static int n, m;
    static int[] bridge;

    public static void main(String[] args) {
        init();
        binarySearch();
    }

    private static void init() {
        n = scan.nextInt();
        m = scan.nextInt();

        bridge = new int[n + 1];
        for (int idx = 0; idx < m; idx++) {
            bridge[idx] = scan.nextInt();
        }
    }

    private static void binarySearch() {
        int start = 0;
        int end = n;

        int answer = 0;
        while(start <= end) {
            // 1. 높이를 이분탐색으로 찾는다.
            int mid = (start + end) / 2; // 가로등 높이
            // 2. 높이가 정해지면 가로등을 설치해본다.
            if (light(mid)) {
                answer = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(answer);
    }

    private static boolean light(int height) {
        // 3. 왼쪽 pos 를 기준으로 계산한다.
        int lastPos = 0;
        for (int idx = 0; idx < m; idx++) {
            // 3-1. 현재 위치를 기준으로 가로등이 비출 수 있는 left, right 를 구한다.
            int left = bridge[idx] - height;
            int right = bridge[idx] + height;

            // 3-2. 이전 right 보다 현재 left 가 크면 비출 수 없다는 뜻
            if (lastPos < left) {
                return false;
            }
            lastPos = right;
        }

        // 3-3. 끝까지 갔을 때 가장 마지막 지점이 n 보다 크거나 같다면 설치 가능
        // 3-4. 작다면 다 비출 수 없다는 뜻
        return lastPos >= n;
    }
}
