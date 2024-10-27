package baekjoon;

import java.util.*;

/**
 * 알약
 *
 * 1. 문제 정리
 * 	1-1. 손녀 선영이는 종수 할아버지에게 약이 N개 담긴 병을 선물로 주었다.
 * 	1-2. 첫째 날에 종수는 병에서 약 하나를 꺼낸다. 그 다음, 그 약을 반으로 쪼개서 한 조각은 먹고, 다른 조각은 다시 병에 넣는다.
 * 	1-3. 다음 날부터 종수는 병에서 약을 하나 꺼낸다.
 * 		1-3-1. 약은 한 조각 전체 일 수도 있고, 쪼갠 반 조각 일 수도 있다
 * 	1-4. 반 조각이라면 그 약을 먹고,
 * 	1-5. 아니라면 반을 쪼개서 한 조각을 먹고, 다른 조각은 다시 병에 넣는다.
 * 	1-6. 종수는 손녀에게 한 조각을 꺼낸 날에는 W를, 반 조각을 꺼낸 날에는 H 보낸다.
 * 	1-7. 총 2N일이 지나면 길이가 2N인 문자열이 만들어지게 된다.
 * 	1-8. 이때, 가능한 서로 다른 문자열의 개수는 총 몇 개일까?
 *  1-9. 입력은 최대 1000개의 테스트 케이스로 이루어져 있다.
 *  1-10. 병에 들어있는 약의 개수 N ≤ 30 가 주어진다.
 *
 * 2. N 이 최대 30이므로, 30 * 30 배열을 생성하고 -1 로 초기화한다.
 *
 * 3. 기저조건
 * 	3-1. 알약하나 or 반쪽알약의 개수가 0보다 작으면 먹을 수 없으므로 0을 return 한다.
 * 	3-2. 알약하나 and 반쪽알약의 개수가 모두 0 이면 다 먹은 것이니 1을 return 한다.
 * 	3-3. 이미 계산된 배열이면 그 값을 return 한다.
 *
 * 4. dp 조건
 * 	4-1. 결과를 저장할 변수를 선언한다.
 * 		4-1-1. 직접 배열에 값을 더해버리면 배열의 값이 변해 다음 재귀에서 문제가 생길 수 있다.
 * 	4-2. 알약하나의 개수가 0보다 크면 알약하나를 먹고, 반쪽알약을 추가한다.
 * 	4-3. 반쪽알약의 개수가 0보다 크면 반쪽알약 하나를 먹는다.
 * 	4-4. 계산 완료 된 결과를 배열에 저장한다.
 * 	4-5. 배열의 값을 return 한다.
 */
public class M4811 {

    static Scanner scan = new Scanner(System.in);
    static int n;
    static long[][] medicine;

    public static void main(String[] args) {
        init();
        while (true) {
            n = scan.nextInt();
            if (n == 0) {
                break;
            }
            System.out.println(takeMedicine(n, 0));
        }
    }

    private static void init() {
        // 2. N 이 최대 30이므로, 30 * 30 배열을 생성하고 -1 로 초기화한다.
        medicine = new long[31][31]; // 한조각, 반조각
        for (int row = 0; row <= 30; row++) {
            for (int col = 0; col <= 30; col++) {
                medicine[row][col] = -1;
            }
        }
    }

    private static long takeMedicine(int whole, int half) {
        // 3. 기저조건
        // 3-1. 알약하나 or 반쪽알약의 개수가 0보다 작으면 먹을 수 없으므로 0을 return 한다.
        if (whole < 0 || half < 0) {
            return 0;
        }
        // 3-2. 알약하나 and 반쪽알약의 개수가 모두 0 이면 다 먹은 것이니 1을 return 한다.
        if (whole == 0 && half == 0) {
            return 1;
        }
        // 3-3. 이미 계산된 배열이면 그 값을 return 한다.
        if (medicine[whole][half] != -1) {
            return medicine[whole][half];
        }

        // 4. dp 조건
        // 4-1. 결과를 저장할 변수를 선언한다.
        // 4-1-1. 직접 배열에 값을 더해버리면 배열의 값이 변해 다음 재귀에서 문제가 생길 수 있다.
        long ways = 0;
        // 4-2. 알약하나의 개수가 0보다 크면 알약하나를 먹고, 반쪽알약을 추가한다.
        if (whole > 0) {
            ways += takeMedicine(whole - 1, half + 1);
        }
        // 4-3. 반쪽알약의 개수가 0보다 크면 반쪽알약 하나를 먹는다.
        if (half > 0) {
            ways += takeMedicine(whole, half - 1);
        }
        // 4-4. 계산 완료 된 결과를 배열에 저장한다.
        medicine[whole][half] = ways;
        // 4-5. 배열의 값을 return 한다.
        return medicine[whole][half];
    }
}
