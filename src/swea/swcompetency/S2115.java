package swea.swcompetency;

import java.util.*;

/**
 * 벌꿀 채취
 *
 * 1. 문제 정리
 *  1-1. 두 일꾼이 벌꿀을 채취한다.
 *  1-2. 벌꿀을 가로로 m 만큼 채취한다.
 *  1-3. 겹치면 안되고, m 만큼의 크기에 있는 벌꿀이 다합쳐서 c 를 넘으면 안된다.
 *
 * 2. n 의 크기가 작기 때문에 모든 경우의 수를 탐색한다.
 *  2-1. 우선 이중 for-loop  로 1번 일꾼의 영역을 정한다.
 *      2-1-1. 부분집합으로 최대 값을 구한다.
 *      2-1.2. 구한 값을 갱신한다.
 *  2-2. 2번 일꾼은 1번 일꾼과 영역이 겹칠 수 없다.
 *      2-2-1. 2번 일꾼이 고를 수 있는 영역은 1번 일꾼과 같은 열이거나, 그 다음열이다.
 *      2-2-2. 때문에 같은 열인 경우 반복문 1번,
 *      2-2-3. 다음 열부터는 2번 일꾼이 자유롭게 쓸 수 있는 영역이기 때문에 이중 for-loop 로 다 찾는다.
 *      2-2-4. 즉, 1번 일꾼과 같은열 + 나머지 영역을 다 탐색해 가장 최대 값으로 갱신한다.
 *  2-3. 1번 일꾼과 2번 일꾼의 최대 값이 결정되면 더한 후 answer 를 갱신한다.
 *
 * 3. 부분집합 종료 조건
 *  3-1. 채취한 벌꿀이 c 를 넘어가면 종료
 *  3-2. m 개 캤을 때, 값을 갱신해준다.
 */
public class S2115 {

    static Scanner scan = new Scanner(System.in);
    static int n, m, c;
    static int[][] graph;
    static int maxBenefit, answer;

    public static void main(String[] args) {
        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            init();
            // 2. n 의 크기가 작기 때문에 모든 경우의 수를 탐색한다.
            combination();
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        c = scan.nextInt();
        graph = new int[n][n];
        answer = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                graph[row][col] = scan.nextInt();
            }
        }
    }

    private static void combination() {
        int firstValue, secondValue; // 1번 일꾼, 2번 일꾼의 결과를 임시 저장할 값들
        // 2-1. 우선 이중 for-loop  로 1번 일꾼의 영역을 정한다.
        for (int firstRow = 0; firstRow < n; firstRow++) {
            for (int firstCol = 0; firstCol <= n - m; firstCol++) {
                maxBenefit = 0;
                // 2-1-1. 부분집합으로 최대 값을 구한다.
                powerSet(firstRow, firstCol, 0, 0, 0);
                // 2-1.2. 구한 값을 갱신한다.
                firstValue = maxBenefit;

                // 2-2. 2번 일꾼은 1번 일꾼과 영역이 겹칠 수 없다.
                // 2-2-1. 2번 일꾼이 고를 수 있는 영역은 1번 일꾼과 같은 열이거나, 그 다음열이다.
                // 2-2-2. 때문에 같은 열인 경우 반복문 1번,
                maxBenefit = 0;
                secondValue = 0;
                for (int secondCol = firstCol + m; secondCol <= n - m; secondCol++) {
                    powerSet(firstRow, secondCol, 0, 0, 0);
                    // 2-2-4. 즉, 1번 일꾼과 같은열 + 나머지 영역을 다 탐색해 가장 최대 값으로 갱신한다.
                    secondValue = Math.max(secondValue, maxBenefit);
                }

                // 2-2-3. 다음 열부터는 2번 일꾼이 자유롭게 쓸 수 있는 영역이기 때문에 이중 for-loop 로 다 찾는다.
                for (int secondRow = firstRow + 1; secondRow < n; secondRow++) {
                    for (int secondCol = 0; secondCol <= n - m; secondCol++) {
                        powerSet(secondRow, secondCol, 0, 0, 0);
                        // 2-2-4. 즉, 1번 일꾼과 같은열 + 나머지 영역을 다 탐색해 가장 최대 값으로 갱신한다.
                        secondValue = Math.max(secondValue, maxBenefit);
                    }
                }
                // 2-3. 1번 일꾼과 2번 일꾼의 최대 값이 결정되면 더한 후 answer 를 갱신한다.
                answer = Math.max(answer, firstValue + secondValue);
            }
        }
    }

    private static void powerSet(int row, int col, int level, int totalHoney, int benefit) {
        // 3-1. 채취한 벌꿀이 c 를 넘어가면 종료
        if (totalHoney > c) {
            return;
        }

        // 3-2. m 개 캤을 때, 값을 갱신해준다.
        if (level == m) {
            maxBenefit = Math.max(maxBenefit, benefit);
            return;
        }

        int honey = graph[row][col];
        powerSet(row, col + 1, level + 1, totalHoney + honey, benefit + honey * honey);
        powerSet(row, col + 1, level + 1, totalHoney, benefit);
    }
}
