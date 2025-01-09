package baekjoon;

import java.util.*;

/**
 * 가희와 3단 고음
 *
 * 문제 정리
 * 1. 음의 계이름을 수로 표현해보자.
 * 2. '1옥타브 도'를 1로 표현하고 1음 올라갈 때마다 그 음을 표현하는 수도 1씩 커진다고 생각할 수 있다.
 * 3. 음 A를 시작으로 D음씩 올리면서 고음을 부르는 경우는 첫항이 A, 공차가 D인 등차수열로 표현되며,
 * 4. 이러한 등차수열의 항의 개수를 X라 할 때, 이 등차수열을 X단 고음이라고 한다. 아래는 A = 1, D = 2인 6단 고음이다.
 * 5. 우리는 수로 표현된 참가자들의 음이 순서대로 주어졌을 때 가능한 경우 중,
 * 6. 음 A를 시작으로 D음씩 올라가는 X단 고음으로 가능한 가장 큰 X를 구하려고 한다.
 *
 * Idea
 * 1. 시작 [idx] 를 찾는다.
 * 2. 찾았다면 count 를 증가시키고, a 값을 갱신한다.
 */
public class M16162 {

    static Scanner scan = new Scanner(System.in);
    static int n, a, d;
    static int[] participant;

    public static void main(String[] args) {
        init();
        System.out.println(countSequence());
    }

    private static void init() {
        n = scan.nextInt();
        a = scan.nextInt();
        d = scan.nextInt();
        participant = new int[n];
        for (int idx = 0; idx < n; idx++) {
            participant[idx] = scan.nextInt();
        }
    }

    private static int countSequence() {
        int count = 0;
        // 1. 시작 [idx] 를 찾는다.
        for (int idx = 0; idx < n; idx++) {
            // 2. 찾았다면 count 를 증가시키고, a 값을 갱신한다.
            if (participant[idx] == a) {
                count++;
                a += d;
            }
        }
        return count;
    }
}