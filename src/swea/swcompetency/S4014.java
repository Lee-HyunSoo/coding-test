package swea.swcompetency;

import java.util.Scanner;

/**
 * 활주로 건설
 *
 * 1. 높이가 변하는 지점이 있을 때 경사로 설치가 가능한지 검사한다.
 *
 * 2. 높이가 올라가는 경우
 *  2-1. idx - 1 ~ idx - x 까지 설치가 가능한지 검사한다.
 *
 * 3. 높이가 내려가는 경우
 *  3-1. idx ~ idx + x - 1 까지 설치가 가능한지 검사한다.
 */
public class S4014 {

    static Scanner scan = new Scanner(System.in);
    static int n, x;
    static int[][] graph1, graph2;
    static int answer;

    public static void main(String[] args) {
        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            init();
            for (int[] line : graph1) {
                if (checkLine(line)) {
                    answer++;
                }
            }

            for (int[] line : graph2) {
                if (checkLine(line)) {
                    answer++;
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void init() {
        n = scan.nextInt();
        x = scan.nextInt();
        graph1 = new int[n][n];
        graph2 = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                graph1[row][col] = scan.nextInt();
            }
        }

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                graph2[row][col] = graph1[col][row];
            }
        }
        answer = 0;
    }

    private static boolean checkLine(int[] line) {
        boolean[] visit = new boolean[n];
        int high = line[0];
        for (int idx = 1; idx < n; idx++) {
            int nextHigh = line[idx];

            if (Math.abs(high - nextHigh) > 1) {
                // 둘의 차이가 1보다 크면 애초에 불가능함
                return false;
            }

            // 1. 높이가 변하는 지점이 있을 때 경사로 설치가 가능한지 검사한다.
            // 2. 높이가 올라가는 경우
            if (high < nextHigh) {
                // 2-1. idx - 1 ~ idx - x 까지 설치가 가능한지 검사한다.
                // idx - 1 ~ idx - x 가 high 면 ok
                for (int base = idx - 1; base >= idx - x; base--) {
                    if (base < 0 || visit[base] || line[base] != high) {
                        return false;
                    }
                }
                // 방문 체크
                for (int base = idx - 1; base >= idx - x; base--) {
                    visit[base] = true;
                }
            }
            // 3. 높이가 내려가는 경우
            else if (high > nextHigh){
                // 3-1. idx ~ idx + x - 1 까지 설치가 가능한지 검사한다.
                // idx ~ idx + x - 1 이 nextHigh 면 ok
                for (int base = idx; base < idx + x; base++) {
                    if (base >= n || visit[base] || line[base] != nextHigh) {
                        return false;
                    }
                }
                // 방문체크
                for (int base = idx; base < idx + x; base++) {
                    visit[base] = true;
                }
            }
            high = nextHigh;
        }
        return true;
    }
}
