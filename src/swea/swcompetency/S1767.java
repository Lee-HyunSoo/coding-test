package swea.swcompetency;

import java.util.*;

/**
 * 프로세서 연결하기
 *
 * 1. 문제 정리
 * 	1-1. 1개의 셀에는 1개의 코어 또는 1개의 전선이 올 수있다.
 * 	1-2. 코어와 연결하는 전선은 직선으로만 설치 가능하고, 교차해서는 안된다.
 * 	1-3. 사이드에 위치한 코어는 전원이 연결된 것으로 판단
 * 	1-4. 최대한 많은 코어를 전원에 연결하였을 경우, 전선 길이가 최소가 되는 합
 * 	1-5.
 *
 * 2. 제약사항
 *  2-1. 7 <= n <= 12
 *  2-2. 코어의 개수는 1 ~ 12개
 *  2-3. 최대한 많은 코어를 전원에 연결해도, 전원이 연결되지 않는 코어가 존재할 수 있다.
 *
 * 3. 가장 사이드에 있는 코어는 제외
 *
 * 4. 완전 탐색
 * 	4-1. 위쪽방향에 전선이나 코어가 없다면 전선을 잇는다.
 * 	4-2. 아래쪽방향에 전선이나 코어가 없다면 전선을 잇는다.
 * 	4-3. 왼쪽방향에 전선이나 코어가 없다면 전선을 잇는다.
 *	4-4. 오른쪽방향에 전선이나 코어가 없다면 전선을 잇는다.
 *	4-5. 어느쪽도 갈 수 없다면 다음 코어로 넘어간다.
 *
 * 5. 종료 조건
 * 	5-1. 마지막 index 에 도달 시,
 * 	5-2. 여태 센 코어의 개수가 기존에 센 코어의 개수보다 많다면 전선의 수는 넘어온 전선의 수로
 * 	5-3. 여태 센 코어의 수가 기존에 센 코어의 수와 같다면 전선의 수가 더 적은 쪽으로 갱신
 */
public class S1767 {

    static class Pair {
        int x, y;
        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Scanner scan;
    static int n;
    static int[][] graph;
    static List<Pair> pairs;
    static int totalCnt;
    static int answer;

    static final int LINK = -1;
    static final int EMPTY = 0;
    static final int CORE = 1;

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            init();
            process(0, 0, 0);
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void init() {
        n = scan.nextInt();
        graph = new int[n][n];
        pairs = new ArrayList<>();
        totalCnt = 0;
        answer = Integer.MAX_VALUE;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                graph[row][col] = scan.nextInt();
            }
        }

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (graph[row][col] == 1) {
                    // 3. 가장 사이드에 있는 코어는 제외
                    if (row == 0 || row == n - 1 || col == 0 || col == n - 1) {
                        continue;
                    }

                    Pair core = new Pair(row, col);
                    if (!isUp(core) && !isDown(core) && !isLeft(core) && !isRight(core)) {
                        continue;
                    }
                    pairs.add(core);
                }
            }
        }
    }

    private static void process(int coreIdx, int coreCnt, int wire) {
        // 5-1. 마지막 index 에 도달 시,
        if (coreIdx == pairs.size()) {
            // 5-2. 여태 센 코어의 개수가 기존에 센 코어의 개수보다 많다면 전선의 수는 넘어온 전선의 수로
            if (totalCnt < coreCnt) {
                totalCnt = coreCnt;
                answer = wire;
            } else if (totalCnt == coreCnt) {
                // 5-3. 여태 센 코어의 수가 기존에 센 코어의 수와 같다면 전선의 수가 더 적은 쪽으로 갱신
                answer = Math.min(answer, wire);
            }
            return;
        }

        Pair core = pairs.get(coreIdx);
        if (isUp(core)) {
            // 4-1. 위쪽방향에 전선이나 코어가 없다면 전선을 잇는다.
            int count = 0;
            for (int row = 0; row < core.x; row++) {
                count++;
                graph[row][core.y] = LINK;
            }
            // 재귀, 연결한 만큼 와이어 증가
            process(coreIdx + 1, coreCnt + 1, wire + count);
            // 연결한 전선 지우기
            for (int row = 0; row < core.x; row++) {
                graph[row][core.y] = EMPTY;
            }
        }

        if (isDown(core)) {
            // 4-2. 아래쪽방향에 전선이나 코어가 없다면 전선을 잇는다.
            int count = 0;
            for (int row = core.x + 1; row < n; row++) {
                count++;
                graph[row][core.y] = LINK;
            }
            // 재귀
            process(coreIdx + 1, coreCnt + 1, wire + count);
            // 연결한 전선 지우기
            for (int row = core.x + 1; row < n; row++) {
                graph[row][core.y]= EMPTY;
            }
        }

        if (isLeft(core)) {
            // 4-3. 왼쪽방향에 전선이나 코어가 없다면 전선을 잇는다.
            int count = 0;
            for (int col = 0; col < core.y; col++) {
                count++;
                graph[core.x][col] = LINK;
            }
            // 재귀
            process(coreIdx + 1, coreCnt + 1, wire + count);
            // 연결한 전선 지우기
            for (int col = 0; col < core.y; col++) {
                graph[core.x][col] = EMPTY;
            }
        }

        if (isRight(core)) {
            // 4-4. 오른쪽방향에 전선이나 코어가 없다면 전선을 잇는다.
            int count = 0;
            for (int col = core.y + 1; col < n; col++) {
                count++;
                graph[core.x][col] = LINK;
            }
            // 재귀
            process(coreIdx + 1, coreCnt + 1, wire + count);
            // 연결한 전선 지우기
            for (int col = core.y + 1; col < n; col++) {
                graph[core.x][col] = EMPTY;
            }
        }

        // 4-5. 어느쪽도 갈 수 없다면 다음 코어로 넘어간다.
        process(coreIdx + 1, coreCnt, wire);
    }

    private static boolean isUp(Pair core) {
        for (int row = 0; row < core.x; row++) {
            int value = graph[row][core.y];
            if (value == LINK || value == CORE) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDown(Pair core) {
        for (int row = core.x + 1; row < n; row++) {
            int value = graph[row][core.y];
            if (value == LINK || value == CORE) {
                return false;
            }
        }
        return true;
    }

    private static boolean isLeft(Pair core) {
        for (int col = 0; col < core.y; col++) {
            int value = graph[core.x][col];
            if (value == LINK || value == CORE) {
                return false;
            }
        }
        return true;
    }

    private static boolean isRight(Pair core) {
        for (int col = core.y + 1; col < n; col++) {
            int value = graph[core.x][col];
            if (value == LINK || value == CORE) {
                return false;
            }
        }
        return true;
    }
}