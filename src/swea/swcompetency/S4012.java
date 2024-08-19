package swea.swcompetency;

import java.util.Scanner;

/**
 * 요리사
 *
 * 1. n 개의 식재료를 각각 n/2 개씩 나누어 두개의 요리를 만든다. (n 은 짝수)
 * 2. A 음식과 B 음식의 맛의 차이가 최소가 되도록 재료를 배분해야한다.
 * 3. 음식의 맛 = 음식을 구성하는 식재료들의 조합 합
 *  3-1. i, j 를 식재료로 음식을 만들었을 때 맛은 Si*Sj + Sj*Si
 *  3-2. nCn/2 를 구하고, 구한 조합으로 A 음식의 맛을 구한다.
 *  3-3. 나머지 재료를 통해 구한 조합으로 B 음식의 맛을 구한다.
 *
 * 4. A, B 음식 간의 맛의 차이가 최소가 되는 경우를 찾고 그 최소값을 정답으로 출력
 */
public class S4012 {

    static int n;
    static int[][] graph;
    static boolean[] combi;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = scan.nextInt();
            graph = new int[n][n];
            combi = new boolean[n];
            answer = Integer.MAX_VALUE;
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    graph[row][col] = scan.nextInt();
                }
            }

            combination(0, 0);
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void combination(int level, int idx) {
        if (level == n / 2) {
            // // 3-1. i, j 를 식재료로 음식을 만들었을 때 맛은 Si*Sj + Sj*Si
            int[] select = new int[n / 2];
            int[] deselect = new int[n / 2];
            setArray(select, deselect);

            // 3-2. nCn/2 를 구하고, 구한 조합으로 A 음식의 맛을 구한다.
            int aSum = getSum(select);
            // 3-3. 나머지 재료를 통해 구한 조합으로 B 음식의 맛을 구한다.
            int bSum = getSum(deselect);
            // 4. A, B 음식 간의 맛의 차이가 최소가 되는 경우를 찾고 그 최소값을 정답으로 출력
            answer = Math.min(answer, Math.abs(aSum - bSum));
            return;
        }

        // 3. 음식의 맛 = 음식을 구성하는 식재료들의 조합 합
        for (int i = idx; i < n; i++) {
            combi[i] = true;
            combination(level + 1, i + 1);
            combi[i] = false;
        }
    }

    private static void setArray(int[] select, int[] deselect) {
        int aidx = 0, bidx = 0;
        for (int i = 0; i < n; i++) {
            if (combi[i]) {
                select[aidx++] = i;
            } else {
                deselect[bidx++] = i;
            }
        }
    }

    private static int getSum(int[] arr) {
        int sum = 0;
        for (int row = 0; row < n / 2; row++) {
            int x = arr[row];
            for (int col = 0; col < n / 2; col++) {
                if (row == col) {
                    continue;
                }
                int y = arr[col];
                sum += graph[x][y];
            }
        }
        return sum;
    }
}