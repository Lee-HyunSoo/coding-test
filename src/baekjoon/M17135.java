package baekjoon;

import java.util.*;

/**
 * 캐슬 디펜스
 *
 * 궁수는 거리 d 이하의 적 중 가장 가까운적을 쏜다. 그런 적이 여러명일 때는 가장 왼쪽의 적을 쏜다.
 * 조합을 돌려서 궁수의 col 좌표를 특정
 * x = 현재 row ~ row - d + 1
 * y = 궁수의 col 좌표까지 (x, y) 를 구해서 (row+1, 궁수 col) 과의 거리 잼
 * 그 중 거리가 더 작으면 -> 좌표 값 갱신
 * 최소와 같은 값이 나올땐 좌표 갱신 x -> 가장 왼쪽의 적이 아니기 때문
 */
public class M17135 {

    static int n, m, d;
    static int[][] graph;
    static boolean[] combi;

    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        n = scan.nextInt();
        m = scan.nextInt();
        d = scan.nextInt();
        graph = new int[n][m];
        combi = new boolean[m];
        answer = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                graph[row][col] = scan.nextInt();
            }
        }

        combination(0, 0);
        System.out.println(answer);
    }

    private static void combination(int level, int idx) {
        if (level == 3) {
            answer = Math.max(answer, defense());
            return;
        }

        for (int i = idx; i < m; i++) {
            combi[i] = true;
            combination(level + 1, i + 1);
            combi[i] = false;
        }
    }

    private static int defense() {
        int[][] copy = new int[n][m];
        for (int idx = 0; idx < n; idx++) {
            copy[idx] = graph[idx].clone();
        }

        int count = 0;
        for (int row = n - 1; row >= 0; row -= d) {
            for (int col = 0; col < m; col++) {
                if (combi[col]) {
                    for (int x = row; x >= x - d + 1; x--) {

                    }

                }
            }
        }



        return count;
    }
}
