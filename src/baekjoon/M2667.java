package baekjoon;

import java.util.*;

/**
 * 단지번호 붙이기
 *
 * 1. BFS 로 단지의 크기를 구한다.
 * 2. 이 때 BFS 를 돌 때마다 count 한다.
 * 3. 구한 크기들을 정렬 후 출력한다.
 */
public class M2667 {

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Scanner scan = new Scanner(System.in);
    static int n, answer;
    static int[][] graph;
    static boolean[][] visit;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        init();
        int count = 0;
        List<Integer> houses = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // 1. BFS 로 단지의 크기를 구한다.
                if (!visit[row][col] && graph[row][col] == 1) {
                    // 2. 이 때 BFS 를 돌 때마다 count 한다.
                    count++;
                    int result = house(new Pair(row, col));
                    houses.add(result);
                }
            }
        }
        System.out.println(count);
        // 3. 구한 크기들을 정렬 후 출력한다.
        houses.sort(null);
        for (int h : houses) {
            System.out.println(h);
        }
    }

    private static void init() {
        n = scan.nextInt();
        scan.nextLine();
        graph = new int[n][n];
        visit = new boolean[n][n];
        for (int row = 0; row < n; row++) {
            String input = scan.nextLine();
            for (int col = 0; col < n; col++) {
                graph[row][col] = input.charAt(col) - '0';
            }
        }
    }

    private static int house(Pair start) {
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(start);
        visit[start.x][start.y] = true;

        int result = 1;
        while(!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0) {
                Pair p = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                        continue;
                    }
                    if (visit[nx][ny]) {
                        continue;
                    }
                    if (graph[nx][ny] == 0) {
                        continue;
                    }
                    visit[nx][ny] = true;
                    q.offer(new Pair(nx, ny));
                    result++;
                }
            }
        }
        return result;
    }
}
