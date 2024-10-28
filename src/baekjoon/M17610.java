package baekjoon;

import java.util.*;

/**
 * 양팔저울
 *
 * 1. 문제 정리
 *  1-1. 무게가 서로 다른 k개의 추와 빈 그릇이 있다. 모든 추의 무게는 정수이고, 그릇의 무게는 0으로 간주한다.
 *  1-2. 양팔저울을 한 번만 이용하여 원하는 무게의 물을 그릇에 담고자 한다.
 *  1-3. 주어진 모든 추 무게의 합을 S라 하자.
 *  1-4. k(3 ≤ k ≤ 13)개 추 무게 g1, g2, ..., gk가 주어질 때, 1부터 S사이에 있는 정수 중,
 *  	1-4-1. 양팔 저울을 한번만 이용하여서는 측정이 불가능한 경우의 수를 찾는 프로그램을 작성하고자 한다.
 *
 * 2. 추를 사용해 만들 수 있는 모든 부분집합을 구한다.
 * 	2-1. 물6 => 물6 + 추3 = 추9 => 물6 = 추9 - 추3
 * 		2-1-1. 이와 같은 수식으로 추를 더하거나 빼거나 사용하지 않거나 해서 모든 경우의 수를 구할 수 있다.
 * 	2-2. sum 을 파라미터로 사용한다.
 * 	2-3. sum 에 현재 추를 더하거나, 아무것도 하지 않거나, 빼거나 하는 모든 경우의 수를 구한다.
 * 	2-4. idx 가 끝에 도달하면, 현재 sum 값을 set 에 저장한다.
 *
 * 3. 1 ~ 물의 무게까지 순회하며 set 에 값이 없다면 센다.
 */
public class M17610 {

    static Scanner scan = new Scanner(System.in);
    static int n, totalWeight;
    static int[] scale;
    static Set<Integer> powerset;
    static boolean flag;

    public static void main(String[] args) {
        init();
        getPowerset(0, 0);
        int answer = 0;
        for (int water = 1; water <= totalWeight; water++) {
            if (!powerset.contains(water)) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    private static void init() {
        n = scan.nextInt();
        scale = new int[n];
        powerset = new HashSet<>();
        totalWeight = 0;
        for (int idx = 0; idx < n; idx++) {
            scale[idx] = scan.nextInt();
            totalWeight += scale[idx];
        }
    }

    private static void getPowerset(int idx, int sum) {
        powerset.add(sum);
        if (idx == scale.length) {
            return;
        }

        getPowerset(idx + 1, sum + scale[idx]);
        getPowerset(idx + 1, sum);
        getPowerset(idx + 1, sum - scale[idx]);
    }
}