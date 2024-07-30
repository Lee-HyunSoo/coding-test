package swea.d3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 재미있는 오셀로 게임
 * <p>
 * 보드는 4 * 4, 6 * 6, 8 * 8 크기를 사용
 * 흑, 백이 번갈아가며 돌을 놓는다. (처음엔 흑이 시작)
 * <p>
 * 빈 공간에 돌을 놓을 수 있다.
 * 단, 놓을 위치와 내 돌 사이에 상대 돌이 있는 경우만
 * -- 사이 : 가로 / 세로 / 대각선
 * 돌을 놓으면 상대의 돌은 내 돌이 됨
 * 보드에 빈 곳이 없거나 양 플레이어 모두 돌을 놓을 곳이 없으면 게임 종료
 * (n/2, n/2), (n/2, n/2 + 1), (n/2 + 1, n/2), (n/2 + 1, n/2 + 1)
 * 지점에 흰 백 흰 백 초기 돌
 * <p>
 * 3 2 1 -> (3, 2) 지점에 흑돌
 * (3, 2) 를 기준으로 8방향 다음 칸 탐색, w 가 있다면,
 * 리스트에 w 좌표 추가 후 같은 방향으로 탐색하며 흰돌 추가
 * <p>
 * -- 탐색 중 빈 공간을 만나면 false
 * -- 끝까지 갔는데 b 가 없으면 false
 * -- 같은 색(b) 를 만나면 true
 * -- w 를 만나면 리스트에 추가
 */
public class S4615 {

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m;
    static int[][] graph;
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    static List<Pair> list;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = scan.nextInt();
            m = scan.nextInt();

            graph = new int[n + 1][n + 1];
            graphInit();

            for (int i = 0; i < m; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                int stone = scan.nextInt(); // 1: 흑돌 2: 백돌

                for (int j = 0; j < 8; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    list = new ArrayList<>();

                    // 다음 위치가 보드 내부라면
                    if (0 < nx && nx <= n && 0 < ny && ny <= n) {
                        // 8방향 중 다른 색 돌이 있었다면
                        if (graph[nx][ny] != 0 && graph[nx][ny] != stone) {
                            list.add(new Pair(nx, ny));
                            // 돌을 두는게 가능하다면
                            if (isPossible(nx, ny, stone, dx[j], dy[j])) {
                                graph[x][y] = stone; // 돌을 둔다.
                                for (Pair p : list) { // 다른 색 돌 색깔 변경
                                    graph[p.x][p.y] = stone;
                                }
                            }
                        }
                    }
                }
            }

            int black = 0, white = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][j] == 1) {
                        black++;
                    } else if (graph[i][j] == 2) {
                        white++;
                    }
                }
            }
            System.out.println("#" + tc + " " + black + " " + white);
        }
    }

    private static void graphInit() {
        graph[n / 2][n / 2 + 1] = 1;
        graph[n / 2][n / 2] = 2;
        graph[n / 2 + 1][n / 2] = 1;
        graph[n / 2 + 1][n / 2 + 1] = 2;
    }

    private static boolean isPossible(int x, int y, int stone, int dx, int dy) {
        int nx = x + dx;
        int ny = y + dy;

        while (0 < nx && nx <= n && 0 < ny && ny <= n) {
            if (graph[nx][ny] == stone) { // 같은 색의 돌을 만났을 때
                return true; // 정지 -> 사이의 돌 색을 바꾸기 위해
            } else if (graph[nx][ny] == 0) {
                return false; // 중간에 빈 공간이 있으면 바꿀 수 없음
            } else {
                list.add(new Pair(nx, ny)); // 다른 색 돌이면 바꾸기 위해 list 추가
            }
            nx += dx;
            ny += dy;
        }
        return false; // 끝까지 갔는데도 같은 색 돌이 없었다면 바꿀 수 없음
    }
}