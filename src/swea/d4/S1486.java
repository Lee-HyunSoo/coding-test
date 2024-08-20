package swea.d4;

import java.util.Scanner;

/**
 * 장훈이의 높은 선반
 *
 * 1. 문제 정리
 *  1-1. n 명의 점원, 선반의 높이 b
 *  1-2. 각 점원의 키는 Hi 로 나타낸다.
 *  1-3. 점원끼리 탑을 쌓아서 높은 선반의 물건을 꺼내 사용, 탑은 1명 이상의 점원으로 이루어짐
 *  1-4. 탑의 높이가 b 이상인 경우 선반위의 물건을 사용할 수 있다. 이중 가장 낮은 탑의 높이
 *  1-5. 탑의 높이 - B 의 크기를 구해라.
 *
 * 2. 점원들의 키로 부분집합을 구한다.
 * 3. b <= 부분집합의 합 중 최소의 값
 */
public class S1486 {

    static int n, b;
    static int[] tower;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = scan.nextInt();
            b = scan.nextInt();
            tower = new int[n];
            answer = Integer.MAX_VALUE;
            for (int idx = 0; idx < n; idx++) {
                tower[idx] = scan.nextInt();
            }
            powerSet(0, 0);
            System.out.println("#" + tc + " " + (answer - b));
        }
    }

    private static void powerSet(int level, int high) {
        // 3. b <= 부분집합의 합 중 최소의 값
        if (high >= b) {
            answer = Math.min(answer, high);
            return;
        }

        if (level == n) {
            return;
        }

        // 2. 점원들의 키로 부분집합을 구한다.
        powerSet(level + 1, high + tower[level]);
        powerSet(level + 1, high);
    }
}
