package swea.d3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 보드는 4 * 4, 6 * 6, 8 * 8 크기를 사용
 * 흑, 백이 번갈아가며 돌을 놓는다. (처음엔 흑이 시작)
 *
 * 빈 공간에 돌을 놓을 수 있다.
 * 단, 놓을 위치와 내 돌 사이에 상대 돌이 있는 경우만
 * -- 사이 : 가로 / 세로 / 대각선
 * 돌을 놓으면 상대의 돌은 내 돌이 됨
 * 보드에 빈 곳이 없거나 양 플레이어 모두 돌을 놓을 곳이 없으면 게임 종료
 *
 * 3 2 1 -> (3, 2) 지점에 흑돌
 * (3, 2) 를 기준으로 8방향 탐색, w 가 있다면,
 * 같은 방향으로 계속 탐색 이어나감
 * 이어나가다가 b 가 나오면 그만큼 다 b로 바꿔야함
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
            graph[n / 2][n / 2 + 1] = 1;
            graph[n / 2][n / 2] = 2;
            graph[n / 2 + 1][n / 2] = 1;
            graph[n / 2 + 1][n / 2 + 1] = 2;

            for (int i = 0; i < m; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                int stone = scan.nextInt(); // 1: 흑돌 2: 백돌

                for (int j = 0; j < 8; j++) {
                    if (isPossible(x, y, stone, dx[j], dy[j])) { // 같은 색 돌 사이에 다른 색 돌이 있었다면
                        graph[x][y] = stone; // 돌을 둔다.
                        for (Pair p : list) { // 다른 색 돌 색깔 변경
                            graph[p.x][p.y] = stone;
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

    private static boolean isPossible(int x, int y, int stone, int dx, int dy) {
        int nx = x + dx;
        int ny = y + dy;
        list = new ArrayList<>();

        boolean flag = false;
        while (0 < nx && nx <= n && 0 < ny && ny <= n) {
            if (graph[nx][ny] == stone) { // 같은 색의 돌을 만났을 때
                if (!list.isEmpty()) { // 사이에 다른 색의 돌이 있었다면
                    flag = true;
                    break; // 정지 -> 사이의 돌 색을 바꾸기 위해
                }
            }
            if (graph[nx][ny] != 0 && graph[nx][ny] != stone) {
                list.add(new Pair(nx, ny));
            }
            nx += dx;
            ny += dy;
        }
        return flag;
    }
}
