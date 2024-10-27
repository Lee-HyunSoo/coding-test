package baekjoon;

import java.util.*;

/**
 * 미세먼지 안녕!
 */
public class M17144 {

    static class Pair {
        int x, y;
        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Scanner scan = new Scanner(System.in);
    static int r, c, t;
    static int[][] graph;
    static List<Pair> aircon;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        init();
        while (t-- > 0) {
            spread();
            air();
        }
        int answer = calc();
        System.out.println(answer);
    }

    private static void init() {
        r = scan.nextInt();
        c = scan.nextInt();
        t = scan.nextInt();

        aircon = new ArrayList<>();
        graph = new int[r][c];
        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                graph[row][col] = scan.nextInt();
                if (graph[row][col] == -1) {
                    aircon.add(new Pair(row, col));
                }
            }
        }
    }

    // 먼지 확산
    private static void spread() {
        int[][] dust = new int[r][c];

        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                if (graph[row][col] > 0) {
                    int div = graph[row][col] / 5;

                    for (int d = 0; d < 4; d++) {
                        int nx = row + dx[d];
                        int ny = col + dy[d];

                        if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                            continue;
                        }
                        if (graph[nx][ny] == -1) {
                            continue;
                        }
                        dust[nx][ny] += div;
                        graph[row][col] -= div;
                    }
                }
            }
        }

        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                graph[row][col] += dust[row][col];
            }
        }
    }

    // 공기청정기 동작
    private static void air() {
        // 상단 공기 청정기 (시계 반대 방향)
        Pair up = aircon.get(0);
        for (int row = up.x - 1; row > 0; row--) {
            graph[row][0] = graph[row - 1][0];
        }
        for (int col = 0; col < c - 1; col++) {
            graph[0][col] = graph[0][col + 1];
        }
        for (int row = 0; row < up.x; row++) {
            graph[row][c - 1] = graph[row + 1][c - 1];
        }
        for (int col = c - 1; col > 1; col--) {
            graph[up.x][col] = graph[up.x][col - 1];
        }
        graph[up.x][1] = 0;

        // 하단 공기 청정기 (시계 방향)
        Pair down = aircon.get(1);
        for (int row = down.x + 1; row < r - 1; row++) {
            graph[row][0] = graph[row + 1][0];
        }
        for (int col = 0; col < c - 1; col++) {
            graph[r - 1][col] = graph[r - 1][col + 1];
        }
        for (int row = r - 1; row > down.x; row--) {
            graph[row][c - 1] = graph[row - 1][c - 1];
        }
        for (int col = c - 1; col > 1; col--) {
            graph[down.x][col] = graph[down.x][col - 1];
        }
        graph[down.x][1] = 0;
    }

    // 동작 완료 후 남은 먼지 계산
    private static int calc() {
        int result = 0;
        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                if (graph[row][col] == -1) continue;
                result += graph[row][col];
            }
        }
        return result;
    }
}

