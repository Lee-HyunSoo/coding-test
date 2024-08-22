package baekjoon;

import java.util.*;

/**
 * 스네이크버드
 *
 * 1. 문제 정리
 *  1-1. 과일을 하나 먹으면 길이가 1 늘어난다.
 *  1-2. 과일은 일정한 높이를 두고 있으며, i (1 <= i <= n) 번째 과일의 높이는 hi 이다.
 *  1-3. 자신의 길이보다 작거나 같은 높이에 있는 과일들을 먹을 수 있다.
 *  1-4. 처음 길이 l 에서 과일을 먹어 늘릴 수 있는 최대 길이
 *
 * 2. 과일 배열을 정렬 후 더하기를 시작한다.
 *  2-1. 정렬 후 자신보다 작거나 같은 것을 먹는다.
 *  2-2. 먹은 후 길이를 + 1 한다.
 *  2-3. 자신보다 큰게 나오면 break
 */
public class M16435 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int l = scan.nextInt();
        int[] fruits = new int[n];
        for (int idx = 0; idx < n; idx++) {
            fruits[idx] = scan.nextInt();
        }
        // 2. 과일 배열을 정렬 후 더하기를 시작한다.
        Arrays.sort(fruits);

        int answer = l;
        for (int fruit : fruits) {
            // 2-1. 정렬 후 자신보다 작거나 같은 것을 먹는다.
            if (answer >= fruit) {
                // 2-2. 먹은 후 길이를 + 1 한다.
                answer++;
            } else {
                // 2-3. 자신보다 큰게 나오면 break
                break;
            }
        }
        System.out.println(answer);
    }
}
