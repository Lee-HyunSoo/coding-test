package baekjoon;

import java.util.*;

/**
 * 치즈
 *
 * 1. 문제 정리
 *  1-1. 매 시간 치즈의 테두리를 녹인다.
 *  1-2. 총 녹는 시간과 다 녹기 한시간 전 남은 개수
 *
 * 2. 치즈가 아닌 부분을 탐색한다.
 *  2-1. 반복문을 통해 visit 배열을 초기화하면서 돈다.
 *      2-1-1. bfs 는 한 영역만 검사하고 종료하기 때문에, 한번에 여러 영역이 녹은 경우 한번의 연산에 끝내기 위해 매번 초기화 필요
 * 	2-2. 탐색 중 1을 만나면 해당 부분을 모서리로 판단하고 방문처리, 0으로 바꾼다.
 * 	2-3. 0 으로 바꾼 것을 count 하다가 마지막에 return 해준다.
 * 	2-4. 탐색 종료 이후 time 을 늘리고, 모든 영역이 0 이되면 반복문을 종료한다.
 */
public class M2636 {

    static class Pair {
        int x, y;

        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int r, c;
    static int[][] graph;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        r = scan.nextInt();
        c = scan.nextInt();
        graph = new int[r][c];

        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                graph[row][col] = scan.nextInt();
            }
        }

        int time = 0, last = 0;
        while (!finish()) {
            // 2-1. 반복문을 통해 visit 배열을 초기화하면서 돈다.
            // 2-1-1. bfs 는 한 영역만 검사하고 종료하기 때문에, 한번에 여러 영역이 녹은 경우 한번의 연산에 끝내기 위해 매번 초기화 필요
            visit = new boolean[r][c];
            last = bfs(new Pair(0, 0));
            // 2-4. 탐색 종료 이후 time 을 늘리고, 모든 영역이 0 이되면 반복문을 종료한다.
            time++;
        }
        System.out.println(time);
        System.out.println(last);
    }

    private static int bfs(Pair start) {
        Queue<Pair> q = new ArrayDeque<>();
        visit[start.x][start.y] = true;
        q.offer(start);

        int count = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0) {
                Pair p = q.poll();

                for (int idx = 0; idx < 4; idx++) {
                    int nx = p.x + dx[idx];
                    int ny = p.y + dy[idx];

                    if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                        continue;
                    }
                    if (visit[nx][ny]) {
                        continue;
                    }

                    if (graph[nx][ny] == 0) {
                        visit[nx][ny] = true;
                        q.offer(new Pair(nx, ny));
                    } else if (graph[nx][ny] == 1) {
                        // 2-2. 탐색 중 1을 만나면 해당 부분을 모서리로 판단하고 방문처리, 0으로 바꾼다.
                        visit[nx][ny] = true;
                        graph[nx][ny] = 0;
                        // 2-3. 0 으로 바꾼 것을 count 하다가 마지막에 return 해준다.
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static boolean finish() {
        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                if (graph[row][col] == 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
