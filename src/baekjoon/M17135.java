package baekjoon;

import java.util.*;

/**
 * 캐슬 디펜스
 *
 * 1. 문제 정리
 *  1-1. 궁수는 거리 d 이하의 적 중 가장 가까운적을 쏜다.
 *  1-2. 그런 적이 여러명일 때는 가장 왼쪽의 적을 쏜다.
 *  1-3. 궁수들이 공통 된 적을 쏠 수도 있다.
 *  1-4. 한 턴에 한 row 씩 움직인다.
 *
 * 2. 궁수의 위치를 조합을 통해 구한다.
 * 	2-1. 궁수의 인원은 3명 고정이기 때문에, 입력 된 m 중 3개를 뽑는 경우의 수를 구한다.
 *
 * 3. 궁수의 위치를 n~1 까지 이동 시키며 쏠 적을 구한다.
 * 	3-1. 한 라운드가 끝나면 row 가 한줄씩 내려온다.
 * 	3-2. 이는 궁수가 한칸 올라가는 것과 마찬가지이다.
 * 	3-3. 때문에 궁수의 row 를 n~1 까지 움직이며 궁수의 좌표를 특정한다.
 *
 * 4. 궁수의 위치를 기준으로 거리를 구한다.
 * 	4-1. 궁수의 현재 row-1 ~ 0 까지 탐색하며 적의 위치를 찾는다.
 * 	4-2. 적의 위치를 찾았다면 궁수와의 거리를 구한다.
 * 	4-3. 거리가 d 이하라면, 최소 거리인지 확인 후 갱신한다.
 * 	4-4. 거리가 동일하다면, 이전 적의 col 과 비교 후 더 왼쪽거로 갱신한다.
 * 	4-5. 고른 적을 list 에 담는다.
 * 		4-5-1. 궁수가 공통 된 적을 쏠 수도 있기 때문에 바로 죽이지 않는다.
 *
 * 5. 모든 궁수가 쏠 적을 정했다면, 죽인다.
 * 	5-1. 이 때 같은 적을 쐈을 수 있기 때문에 아직 살아있는 적을 쏜 경우만 count 증가
 *
 * 6. 한 게임이 끝나면, 화살을 쏜 총 수량을 갱신한다.
 */
public class M17135 {

    static class Pair {
        int x, y;
        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Scanner scan = new Scanner(System.in);
    static int n, m, d;
    static int[][] graph;
    static int[] archer;

    static int[][] copy;
    static List<Pair> enemys;
    static int answer;

    public static void main(String[] args) {
        init();
        // 2. 궁수의 위치를 조합을 통해 구한다.
        selectArcher(0, 0);
        System.out.println(answer);
    }

    private static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        d = scan.nextInt();
        graph = new int[n][m];
        archer = new int[3];
        answer = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                graph[row][col] = scan.nextInt();
            }
        }
    }

    private static void selectArcher(int level, int idx) {
        // 2-1. 궁수의 인원은 3명 고정이기 때문에, 입력 된 m 중 3개를 뽑는 경우의 수를 구한다.
        if (level == 3) {
            copy();
            // 3. 궁수의 위치를 n~1 까지 이동 시키며 쏠 적을 구한다.
            // 6. 한 게임이 끝나면, 화살을 쏜 총 수량을 갱신한다.
            answer = Math.max(answer, moveArcher());
            return;
        }

        for (int i = idx; i < m; i++) {
            archer[level] = i;
            selectArcher(level + 1, i + 1);
        }
    }

    private static int moveArcher() {
        int maxKill = 0;
        // 3-1. 한 라운드가 끝나면 row 가 한줄씩 내려온다.
        // 3-2. 이는 궁수가 한칸 올라가는 것과 마찬가지이다.
        // 3-3. 때문에 궁수의 row 를 n~1 까지 움직이며 궁수의 좌표를 특정한다.
        for (int row = n; row > 0; row--) {
            enemys = new ArrayList<>();
            for (int col : archer) {
                // 4. 궁수의 위치를 기준으로 거리를 구한다.
                selectEnemy(row, col);
            }
            // 5. 모든 궁수가 쏠 적을 정했다면, 죽인다.
            maxKill += kill();
        }
        return maxKill;
    }

    private static void selectEnemy(int archerX, int archerY) {
        int minDist = Integer.MAX_VALUE;
        int attackX = -1, attackY = -1;
        // 4-1. 궁수의 현재 row-1 ~ 0 까지 탐색하며 적의 위치를 찾는다.
        for (int row = archerX - 1; row >= 0; row--) {
            for (int col = 0; col < m; col++) {
                // 4-2. 적의 위치를 찾았다면 궁수와의 거리를 구한다.
                if (copy[row][col] == 1) {
                    int dist = getDist(row, col, archerX, archerY);
                    if (dist > d) {
                        continue;
                    }
                    if (dist < minDist) {
                        // 4-3. 거리가 d 이하라면, 최소 거리인지 확인 후 갱신한다.
                        minDist = dist;
                        attackX = row;
                        attackY = col;
                    } else if (dist == minDist) {
                        // 4-4. 거리가 동일하다면, 이전 적의 col 과 비교 후 더 왼쪽거로 갱신한다.
                        if (col < attackY) {
                            attackX = row;
                            attackY = col;
                        }
                    }
                }
            }
        }
        // 4-5. 고른 적을 list 에 담는다.
        if (attackX != -1 && attackY != -1) {
            // 4-5-1. 궁수가 공통 된 적을 쏠 수도 있기 때문에 바로 죽이지 않는다.
            enemys.add(new Pair(attackX, attackY));
        }
    }

    private static int kill() {
        int count = 0;
        // 5-1. 이 때 같은 적을 쐈을 수 있기 때문에 아직 살아있는 적을 쏜 경우만 count 증가
        for (Pair enemy : enemys) {
            if (copy[enemy.x][enemy.y] == 1) {
                count++;
                copy[enemy.x][enemy.y] = 0;
            }
        }
        return count;
    }

    private static int getDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static void copy() {
        copy = new int[n][m];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                copy[row][col] = graph[row][col];
            }
        }
    }
}
