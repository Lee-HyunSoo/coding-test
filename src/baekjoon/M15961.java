package baekjoon;

import java.util.*;

/**
 * 회전 초밥
 *
 * 1. 문제 정리
 * 	1-1. 벨트 위에 같은 종류의 초밥이 둘 이상 있을 수 있다.
 * 	1-2. 벨트의 임의의 한 위치로부터 k 개의 접시를 연속해서 먹을 경우 할인된 정액 가격
 * 	1-3. 고객에게 초밥의 종류가 하나 쓰인 쿠폰을 발행하고, 1번 행사에 참가할 경우 초밥 하나 무료 제공
 * 		1-3-1. 1번 행사 : k 개의 접시를 연속해서 먹은 경우
 * 		1-3-2. 없을 경우 요리사가 새로 하나 만들어 제공
 * 	1-4. (2, 7, 9, 25, 30) 5가지 종류의 초밥이 있는 상태에서 30번 쿠폰을 가지고 있다하자.
 * 	1-5. k = 4 라면, (2, 7, 9, 25) 를 고르고 30번을 쿠폰으로 먹으면 총 5종류 먹을 수 있다.
 *
 * 2. 투 포인터 알고리즘을 사용한다.
 * 	2-1. 우선 0~k 까지 map 에 집어 넣는다. 이 때 중복을 확인하기 위해 value 는 개수를 넣는다.
 * 	2-2. start, end 를 잡고 start == n 이 될 때까지 반복한다.
 * 		2-2-1. start == n 인 순간 한바퀴를 돈 것이기 때문에 반복문 종료
 * 	2-3. end 는 더하다보면 index 를 벗어날 수 있기 때문에 n 으로 나머지 연산해준다.
 * 	2-4. 쿠폰을 무조건 사용한다 가정하고, map 에 넣는다.
 * 	2-5. 최대 종류의 수를 map 의 크기로 갱신한다.
 * 	2-6. start 에 해당하는 초밥을 지운다.
 * 		2-6-1. value 가 1보다 크면 value 만 1 줄인다.
 * 		2-6-2. value 가 1이하면 map 에서 초밥 자체를 삭제한다.
 * 	2-7. end 에 해당하는 초밥을 map 에 넣는다.
 */
public class M15961 {

    static Scanner scan = new Scanner(System.in);
    static int n, d, k, c;
    static int[] sushi;
    static int maxCount;

    public static void main(String[] args) {
        init();
        countSushi();
        System.out.println(maxCount);
    }

    private static void init() {
        n = scan.nextInt();
        d = scan.nextInt();
        k = scan.nextInt();
        c = scan.nextInt();
        sushi = new int[n];
        maxCount = 0;
        for (int idx = 0; idx < n; idx++) {
            sushi[idx] = scan.nextInt();
        }
    }

    private static void countSushi() {
        Map<Integer, Integer> map = new HashMap<>();
        // 2-1. 우선 0~k 까지 map 에 집어 넣는다. 이 때 중복을 확인하기 위해 value 는 개수를 넣는다.
        for (int idx = 0; idx < k; idx++) {
            map.put(sushi[idx], map.getOrDefault(sushi[idx], 0) + 1);
        }
        // 2-2. start, end 를 잡고 start == n 이 될 때까지 반복한다.
        int start = 0, end = k;
        // 2-2-1. start == n 인 순간 한바퀴를 돈 것이기 때문에 반복문 종료
        while (start < n) {
            // 2-3. end 는 더하다보면 index 를 벗어날 수 있기 때문에 n 으로 나머지 연산해준다.
            end %= n;

            // 2-4. 쿠폰을 무조건 사용한다 가정하고, map 에 넣는다.
            map.put(c, map.getOrDefault(c, 0) + 1);
            // 2-5. 최대 종류의 수를 map 의 크기로 갱신한다.
            maxCount = Math.max(maxCount, map.size());

            // 2-6. start 에 해당하는 초밥을 지운다.
            int value = map.get(sushi[start]);
            if (value > 1) {
                // 2-6-1. value 가 1보다 크면 value 만 1 줄인다.
                map.put(sushi[start], value - 1);
            } else {
                // 2-6-2. value 가 1이하면 map 에서 초밥 자체를 삭제한다.
                map.remove(sushi[start]);
            }
            // 2-7. end 에 해당하는 초밥을 map 에 넣는다.
            map.put(sushi[end], map.getOrDefault(sushi[end], 0) + 1);
            start++;
            end++;
        }
    }
}
