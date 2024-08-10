package swea.d3;

import java.util.*;

/**
 * 상호의 배틀필드
 *
 * 전차는 이동한다.
 * 	-- 그래프 밖으론 이동 불가
 * 	-- . : 평지, 이동 가능
 * 	-- * : 벽돌벽, # : 강철벽
 * 	-- - : 물, 이동불가
 * 	-- 이동 시 방향을 바꾸고, 평지인 곳이라면 한칸 이동
 *
 * 전차는 자신이 바라보고 있는 방향으로 포탄을 쏜다.
 * 	-- 포탄을 쐈을 때, 벽돌벽 / 강철벽 / 맵 밖까지 직진
 * 	-- 벽돌벽은 맞으면
 * 부서져 평지가 된다.
 * 	-- 강철벽에 맞거나 맵 밖까지 가면 아무일도 없다.
 */
public class S1873 {

    static int h, w;
    static char[][] graph;
    static int y, x; // 전차의 현재 위치

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            h = scan.nextInt();
            w = scan.nextInt();
            scan.nextLine();

            graph = new char[h][w];
            for (int row = 0; row < h; row++) {
                String input = scan.nextLine();
                for (int col = 0; col < w; col++) {
                    char pos = input.charAt(col);
                    graph[row][col] = pos;
                    if (pos == '<' || pos == '>' || pos == '^' || pos == 'v') {
                        y = row;
                        x = col;
                    }
                }
            }
            int n = scan.nextInt();
            scan.nextLine();
            String command = scan.nextLine();

            for (char c : command.toCharArray()) {
                // 슈팅 명령, 현재 위치 ~ 바라보는쪽 벽까지 탐색
                if (c == 'S') {
                    shoot();
                } else if (c == 'U') {
                    move(-1, 0, '^');
                } else if (c == 'D') {
                    move(1, 0, 'v');
                } else if (c == 'L') {
                    move(0, -1, '<');
                } else if (c == 'R') {
                    move(0, 1, '>');
                }
            }

            System.out.print("#" + tc + " ");
            for (int row = 0; row < h; row++) {
                for (int col = 0; col < w; col++) {
                    System.out.print(graph[row][col]);
                }
                System.out.println();
            }
        }
    }

    private static void shoot() {
        char dir = graph[y][x];
        // 벽돌벽이면 부수고, 강철벽이면 못부숨
        if (dir == '^') {
            // 0 ~ 현재 y 좌표까지 탐색
            for (int row = y - 1; row >= 0; row--) {
                if (graph[row][x] == '#') {
                    break;
                } else if (graph[row][x] == '*') {
                    graph[row][x] = '.';
                    break;
                }
            }
        } else if (dir == 'v') {
            // y ~ h 까지 탐색
            for (int row = y + 1; row < h; row++) {
                if (graph[row][x] == '#') {
                    break;
                } else if (graph[row][x] == '*') {
                    graph[row][x] = '.';
                    break;
                }
            }
        } else if (dir == '<') {
            // 0 ~ 현재 x 좌표까지 탐색
            for (int col = x - 1; col >= 0; col--) {
                if (graph[y][col] == '#') {
                    break;
                } else if (graph[y][col] == '*') {
                    graph[y][col] = '.';
                    break;
                }
            }
        } else if (dir == '>') {
            // x ~ w 까지 탐색
            for (int col = x + 1; col < w; col++) {
                if (graph[y][col] == '#') {
                    break;
                } else if (graph[y][col] == '*') {
                    graph[y][col] = '.';
                    break;
                }
            }
        }
    }

    private static void move(int dy, int dx, char direction) {
        int ny = y + dy;
        int nx = x + dx;

        if (0 <= ny && ny < h && 0 <= nx && nx < w) {
            // 다음 칸이 평지라면
            if (graph[ny][nx] == '.') {
                // 기존 전차가 있던 자리 평지로 전환
                graph[y][x] = '.';
                y = ny;
                x = nx;
            }
        }
        // 방향 전환
        graph[y][x] = direction;
    }
}
