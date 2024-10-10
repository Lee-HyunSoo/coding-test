package codetree;

import java.util.*;

/**
 * 나무박멸
 *
 * 1. 문제 정리
 * 	1-1. 성장
 * 		1-1-1. 인접한 네 개의 칸 중 나무가 있는 칸의 수 만큼 성장한다.
 * 		1-1-2. 성장은 모든 나무에게 동시에 일어난다.
 * 	1-2. 번식
 * 		1-2-1. 인접한 4개의 칸 중 벽, 다른 나무, 제초제 모두 없는 칸에서 번식을 진행
 * 		1-2-2. 인접한 4개의 칸 중 빈 칸의 수를 센다.
 * 		1-2-3. 현재칸의 나무의수 / 빈칸의수 만큼 번식한다.
 *  1-3. 제초제 살포
 *  	1-3-1. 나무가 없는 칸에 뿌리면 아무것도 박멸되지 않는다.
 *  	1-3-2. 나무가 있는 칸에 뿌리면 4개의 대각선으로 k 칸만큼 전파된다.
 *  		1-3-2-1. 전파 도중 벽이거나 (-1) 나무가 없는 칸이면 (0) 그 칸까지는 제초제를 뿌리고 그다음칸은 전파 x
 *  	1-3-3. 제초제가 뿌려진 칸에는 c 년만큼 제초제가 남아있다가 c+1 년째에 사라진다.
 *  	1-3-4. 이미 뿌려진데에 또 뿌리면 갱신된다.
 */
public class 나무박멸 {

    static Scanner scan = new Scanner(System.in);
    static int n, m, k, c;
    static int[][] tree, die;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static int[] diax = {-1, -1, 1, 1}, diay = {-1, 1, -1, 1};
    static int answer;

    private static final int WALL = -1;

    public static void main(String[] args) {
        init();
        for (int year = 0; year < m; year++) {
            grow();
            propagate();
            weeding();
            recovery();
        }
        System.out.println(answer);
    }

    private static void init() {
        n = scan.nextInt(); // 격자의 크기
        m = scan.nextInt(); // 박멸이 진행되는 년 수
        k = scan.nextInt(); // 제초제의 확산 범위
        c = scan.nextInt(); // 제초제가 남아있는 년 수

        tree = new int[n][n];
        die = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                tree[row][col] = scan.nextInt();
            }
        }
        answer = 0;
    }

    // 1-1-1. 인접한 네 개의 칸 중 나무가 있는 칸의 수 만큼 성장한다.
    private static void grow() {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // 나무가 없거나 벽인경우 continue
                if (tree[row][col] <= 0) continue;


                int count = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = row + dx[d];
                    int ny = col + dy[d];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                        continue;
                    }
                    if (tree[nx][ny] <= 0 || die[nx][ny] > 0) {
                        continue;
                    }

                    // 인접좌표에 나무가 있다면 count++
                    count++;
                }
                tree[row][col] += count;
            }
        }
    }

    // 1-2-1. 인접한 4개의 칸 중 벽, 다른 나무, 제초제 모두 없는 칸에서 번식을 진행
    private static void propagate() {
        int[][] newTree = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (tree[row][col] <= 0) continue;

                // 1-2-2. 인접한 4개의 칸 중 빈 칸의 수를 센다.
                int count = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = row + dx[d];
                    int ny = col + dy[d];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                        continue;
                    }
                    if (tree[nx][ny] == WALL || tree[nx][ny] > 0 || die[nx][ny] > 0) {
                        continue;
                    }
                    count++;
                }

                // 1-2-3. 현재칸의 나무의수 / 빈칸의수 만큼 번식한다.
                for (int d = 0; d < 4; d++) {
                    int nx = row + dx[d];
                    int ny = col + dy[d];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                        continue;
                    }
                    if (tree[nx][ny] == WALL || tree[nx][ny] > 0 || die[nx][ny] > 0) {
                        continue;
                    }
                    newTree[nx][ny] += tree[row][col] / count;
                }
            }
        }

        // 누적 합을 더한다.
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                tree[row][col] += newTree[row][col];
            }
        }
    }

    // 1-3. 제초제 살포
    // 1-3-1. 나무가 없는 칸에 뿌리면 아무것도 박멸되지 않는다.
    // 1-3-2. 나무가 있는 칸에 뿌리면 4개의 대각선으로 k 칸만큼 전파된다.
    // 1-3-2-1. 전파 도중 벽이거나 (-1) 나무가 없는 칸이면 (0) 그 칸까지는 제초제를 뿌리고 그다음칸은 전파 x
    // 1-3-3. 제초제가 뿌려진 칸에는 c 년만큼 제초제가 남아있다가 c+1 년째에 사라진다.
    // 1-3-4. 이미 뿌려진데에 또 뿌리면 갱신된다.
    private static void weeding() {
        int maxWeed = 0, sRow = -1, sCol = -1;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (tree[row][col] <= 0) continue;

                int result = countWeed(row, col);

                if (maxWeed < result) {
                    sRow = row;
                    sCol = col;
                    maxWeed = result;
                }
            }
        }
        if (sRow != -1 && sCol != -1) {
            shootWeed(sRow, sCol);
            answer += maxWeed;
        }
    }

    private static int countWeed(int row, int col) {
        int result = tree[row][col];
        // 1-3-2. 나무가 있는 칸에 뿌리면 4개의 대각선으로 k 칸만큼 전파된다.
        for (int d = 0; d < 4; d++) {
            int nx = row;
            int ny = col;
            for (int range = 1; range <= k; range++) {
                nx += diax[d];
                ny += diay[d];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    break;
                }
                // 1-3-2-1. 전파 도중 벽이거나 (-1) 나무가 없는 칸이면 (0 이하) 그 칸까지는 제초제를 뿌리고 그다음칸은 전파 x
                if (tree[nx][ny] <= 0) {
                    break;
                }
                // 나무가 있는 칸만 추가
                result += tree[nx][ny];

            }
        }
        return result;
    }

    private static void shootWeed(int row, int col) {
        die[row][col] = c + 1;
        tree[row][col] = 0;
        // 1-3-2. 나무가 있는 칸에 뿌리면 4개의 대각선으로 k 칸만큼 전파된다.
        for (int d = 0; d < 4; d++) {
            int nx = row;
            int ny = col;
            for (int range = 1; range <= k; range++) {
                nx += diax[d];
                ny += diay[d];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }
                if (tree[nx][ny] == WALL) {
                    break;
                }

                // 1-3-2-1. 전파 도중 벽이거나 (-1) 나무가 없는 칸이면 (0 이하) 그 칸까지는 제초제를 뿌리고 그다음칸은 전파 x
                die[nx][ny] = c + 1;
                if (tree[nx][ny] == 0) {
                    break;
                }
                tree[nx][ny] = 0;
            }
        }
    }

    private static void recovery() {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (die[row][col] > 0) {
                    die[row][col]--;
                }
            }
        }
    }
}
