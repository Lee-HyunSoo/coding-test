package baekjoon;

import java.util.*;

/**
 * 좋다
 *
 * 1. 해당 숫자의 index 를 파라미터로 넘긴다.
 *
 * 2. 시작 인덱스, 끝 인덱스를 start, end 로 잡는다.
 *
 * 3. 이 때 start 와 end 가 같을 수는 없다.
 * 	3-1. 때문에 while 문의 조건이 (start < end) 가 된다.
 *
 * 4. 만약 start 나 end 가 index 와 같으면 처리해준다.
 * 	4-1. start 가 index 와 같으면 start 를 1 증가 시키고 continue 한다.
 * 	4-2. end 가 index 와 같으면 end 를 1 감소 시키고 continue 한다.
 *
 * 5. 두 수의 합을 구한 후 비교한다.
 * 	5-1. 두 수의 합이 index 위치의 숫자와 같으면 answer 를 증가시키고 정지한다.
 * 	5-2. 두 수의 합이 index 위치의 숫자보다 작으면 start 를 증가시킨다.
 * 	5-3. 두 수의 합이 index 위치의 숫자보다 크면 end 를 감소시킨다.
 */
public class M1253 {

    static Scanner scan = new Scanner(System.in);
    static int n;
    static int[] numbers;
    static int answer;

    public static void main(String[] args) {
        init();
        for (int idx = 0; idx < n; idx++) {
            // 1. 해당 숫자의 index 를 파라미터로 넘긴다.
            binarySearch(idx);
        }
        System.out.println(answer);
    }

    private static void binarySearch(int index) {
        // 2. 시작 인덱스, 끝 인덱스를 start, end 로 잡는다.
        int start = 0;
        int end = n - 1;

        // 3. 이 때 start 와 end 가 같을 수는 없다.
        // 3-1. 때문에 while 문의 조건이 (start < end) 가 된다.
        while (start < end) {
            // 4. 만약 start 나 end 가 index 와 같으면 처리해준다.
            if (start == index) {
                // 4-1. start 가 index 와 같으면 start 를 1 증가 시키고 continue 한다.
                start++;
                continue;
            }
            if (end == index) {
                // 4-2. end 가 index 와 같으면 end 를 1 감소 시키고 continue 한다.
                end--;
                continue;
            }

            // 5. 두 수의 합을 구한 후 비교한다.
            int sum = numbers[start] + numbers[end];
            if (sum == numbers[index]) {
                // 5-1. 두 수의 합이 index 위치의 숫자와 같으면 answer 를 증가시키고 정지한다.
                answer++;
                break;
            } else if (sum < numbers[index]) {
                // 5-2. 두 수의 합이 index 위치의 숫자보다 작으면 start 를 증가시킨다.
                start++;
            } else {
                // 5-3. 두 수의 합이 index 위치의 숫자보다 크면 end 를 감소시킨다.
                end--;
            }
        }
    }

    private static void init() {
        n = scan.nextInt();
        numbers = new int[n];
        answer = 0;
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = scan.nextInt();
        }
        Arrays.sort(numbers);
    }
}
