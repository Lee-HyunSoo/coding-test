package baekjoon;

import java.util.*;

/**
 * 도영이가 만든 맛있는 음식
 *
 * 1. 입력부
 *  1-1. n 개의 재료, 신맛 s 와 쓴맛 b
 *  1-2. 신맛: 사용한 재료들의 s 곱
 *  1-3. 쓴맛: 사용한 재료들의 b 합
 *
 * 2. 부분집합 사용
 *  2-1. level, idx, 신맛의 곱, 쓴맛의 합을 파라미터로 넘긴다.
 *  2-2. 연산을 진행하고 넘어가는 재귀
 *  2-3. 연산을 하지 않고 넘어가는 재귀
 *
 * 3. 종료 조건
 *  3-1. level 이 n 이되면 종료한다.
 *  3-2. sour, bitter 가 초기값이 아닐 때 신맛의 곱과 쓴맛의 합의 차이가 가장 적은 것으로 갱신한다.
 */
public class M2961 {

    static class Pair {
        int sour, bitter;

        Pair(int sour, int bitter) {
            this.sour = sour;
            this.bitter = bitter;
        }
    }

    static int n;
    static List<Pair> foods;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // 1. 입력부
        n = scan.nextInt();
        foods = new ArrayList<>();
        answer = Integer.MAX_VALUE;
        for (int idx = 0; idx < n; idx++) {
            int sour = scan.nextInt();
            int bitter = scan.nextInt();
            foods.add(new Pair(sour, bitter));
        }

        // 2. 부분집합 사용
        subset(0, 1, 0);
        System.out.println(answer);
    }

    // 2-1. level, idx 를 파라미터로 넘긴다.
    private static void subset(int level, int sour, int bitter) {
        // 3-1. level 이 n 이되면 종료한다.
        if (level == n) {
            // 3-2. sour, bitter 가 초기값이 아닐 때 신맛의 곱과 쓴맛의 합의 차이가 가장 적은 것으로 갱신한다.
            if (sour != 1 && bitter != 0) {
                answer = Math.min(answer, Math.abs(sour - bitter));
            }
            return;
        }

        // 2-2. 연산을 진행하고 넘어가는 재귀
        subset(level + 1, sour * foods.get(level).sour, bitter + foods.get(level).bitter);
        // 2-3. 연산을 하지 않고 넘어가는 재귀
        subset(level + 1, sour, bitter);
    }
}
