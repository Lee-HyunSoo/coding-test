package baekjoon;

import java.util.*;

/**
 * 감시
 *
 * 1. 문제 정리
 * 	1-1. 5종류의 cctv
 * 	1-2. 빈칸은 0, cctv 는 1~5, 벽은 6 으로 나타낸다.
 * 	1-3. 각각의 cctv 를 90도로 돌려가며 감시 방향을 바꿀 수 있음
 * 	1-4. cctv 끼리는 통과 가능, 벽은 투과 불가능
 * 	1-5. cctv 의 방향을 적절히 정해 사각지대의 최소 크기를 구해라
 *
 * 2. 입력 시 1~5 인 좌표를 따로 리스트에 저장한다.
 * 3. 경우의 수에 대해 중복 순열을 돌린다. (경우의수 : 회전)
 * 	3-1. 1 의 경우 한쪽방향 체크
 * 	3-2. 2의 경우 두 방향 체크
 * 	3-3. 3의 경우 90도 방향 체크
 * 	3-4. 4의 경우 세 방향 체크
 * 	3-5. 5의 경우 전 방향 체크
 *
 * 4. 체크 시 mod 연산을 활용한다.
 * 	4-1. 방향은 0~3 까지 4개기 때문에 4로 나눈 나머지가 해당 방향이 된다.
 * 	4-2. 그래프 외부나 벽을 만날때까지 가야하기 때문에 기존 좌표를 계속 갱신한다.
 * 	4-3. 그래프 밖으로 나가면 종료한다.
 * 	4-4. 벽을 만나면 종료한다.
 * 	4-5. 이동 지점이 0이 아니라면(1~5의 숫자) continue
 * 	4-6. 0이라면 -1로 바꾼다.
 */
public class M15683 {

    static class Pair {
        int x, y;
        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m;
    static int[][] graph;
    static List<Pair> pairs;
    static int[] dx = {0, -1, 0, 1}; // 우상좌하
    static int[] dy = {1, 0, -1, 0};
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        graph = new int[n][m];
        pairs = new ArrayList<>();
        answer = Integer.MAX_VALUE;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                graph[row][col] = scan.nextInt();
                if (1 <= graph[row][col] && graph[row][col] <= 5) {
                    // 2. 입력 시 1~5 인 좌표를 따로 리스트에 저장한다.
                    pairs.add(new Pair(row, col));
                }
            }
        }

        // 3. 경우의 수에 대해 중복 순열을 돌린다. (경우의수 : 회전)
        cctv(0);
        System.out.println(answer);
    }

    public static void cctv(int idx) {
        if (idx == pairs.size()) {
            int count = 0;
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < m; col++) {
                    if (graph[row][col] == 0) {
                        count++;
                    }
                }
            }
            answer = Math.min(answer, count);
            return;
        }

        int row = pairs.get(idx).x;
        int col = pairs.get(idx).y;
        int[][] copy = new int[n][m];
        for (int dir = 0; dir < 4; dir++) {
            graphToCopy(copy);
            if (graph[row][col] == 1) {
                // 3-1. 1 의 경우 한쪽방향 체크
                check(row, col, dir);
            } else if (graph[row][col] == 2) {
                // 3-2. 2의 경우 두 방향 체크
                check(row, col, dir);
                check(row, col, dir + 2);
            } else if (graph[row][col] == 3) {
                // 3-3. 3의 경우 90도 방향 체크
                check(row, col, dir);
                check(row, col, dir + 1);
            } else if (graph[row][col] == 4) {
                // 3-4. 4의 경우 세 방향 체크
                check(row, col, dir);
                check(row, col, dir + 1);
                check(row, col, dir + 2);
            } else if (graph[row][col] == 5) {
                // 3-5. 5의 경우 전 방향 체크
                check(row, col, dir);
                check(row, col, dir + 1);
                check(row, col, dir + 2);
                check(row, col, dir + 3);
            }
            cctv(idx + 1);
            copyToGraph(copy);
        }
    }

    private static void check(int x, int y, int dir) {
        // 4. 체크 시 mod 연산을 활용한다.
        // 4-1. 방향은 0~3 까지 4개기 때문에 4로 나눈 나머지가 해당 방향이 된다.
        dir %= 4;
        while(true) {
            // 4-2. 그래프 외부나 벽을 만날때까지 가야하기 때문에 기존 좌표를 계속 갱신한다.
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            x = nx;
            y = ny;

            // 4-3. 그래프 밖으로 나가면 종료한다.
            if (0 > nx || nx >= n || 0 > ny || ny >= m) {
                return;
            }
            // 4-4. 벽을 만나면 종료한다.
            if (graph[nx][ny] == 6) {
                return;
            }
            // 4-5. 이동 지점이 0이 아니라면(1~5의 숫자) continue
            if (graph[nx][ny] != 0) {
                continue;
            }
            // 4-6. 0이라면 -1로 바꾼다.
            graph[nx][ny] = -1;
        }
    }

    private static void graphToCopy(int[][] copy) {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                copy[row][col] = graph[row][col];
            }
        }
    }

    private static void copyToGraph(int[][] copy) {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                graph[row][col] = copy[row][col];
            }
        }
    }
}
