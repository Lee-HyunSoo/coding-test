package swea.d4;

import java.util.*;

/**
 * 치즈 도둑
 *
 * 1. 문제 정리
 * 	1-1. n*n 모양에 맛의 정도가 1 ~ 100 까지로 표현
 * 	1-2. x 번째 날에는 맛있는 정도가 x 인 칸을 먹는다.
 * 	1-3. 먹고 남은 칸들로 덩어리를 센다.
 * 	1-4. 100일 중 덩어리가 가장 많을 때의 개수
 *
 * 2. 인접리스트 사용
 * 	2-1. 입력을 100개짜리 인접리스트로 저장한다.
 * 	2-2. 모든 부분을 먹었을 때의 최소 덩어리 수는 1개기 때문에, 초기 답을 1로 설정한다.
 * 	2-3. 인접리스트를 탐색하며 하나씩 1으로 바꾼다.
 * 	2-4. 바뀐 배열을 bfs 로 탐색, 덩어리를 센다.
 */
public class S7733 {

    static class Pair {
        int x, y;

        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    // 2. 인접리스트 사용
    static List<List<Pair>> adj;
    static int[][] graph;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = scan.nextInt();
            adj = new ArrayList<>();
            graph = new int[n][n];
            for (int idx = 0; idx <= 100; idx++) {
                adj.add(new ArrayList<>());
            }

            // 2-1. 입력을 100개짜리 인접리스트로 저장한다.
            int max = 0;
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    int input = scan.nextInt();
                    adj.get(input).add(new Pair(row, col));
                    max = Math.max(max, input);
                }
            }

            // 2-2. 모든 부분을 먹었을 때의 최소 덩어리 수는 1개기 때문에, 초기 답을 1로 설정한다.
            int answer = 1;
            for (int idx = 1; idx < max; idx++) {
                // 2-3. 인접리스트를 탐색하며 하나씩 1으로 바꾼다.
                eat(idx);
                visit = new boolean[n][n];
                int sum = 0;
                // 2-4. 바뀐 배열을 bfs 로 탐색, 덩어리를 센다.
                for (int row = 0; row < n; row++) {
                    for (int col = 0; col < n; col++) {
                        if (!visit[row][col] && graph[row][col] == 0) {
                            countBlock(new Pair(row, col));
                            sum++;
                        }
                    }
                }
                answer = Math.max(answer, sum);
            }
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void eat(int idx) {
        List<Pair> pairs = adj.get(idx);

        for (Pair p : pairs) {
            graph[p.x][p.y] = 1;
        }
    }

    private static void countBlock(Pair start) {
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(start);
        visit[start.x][start.y] = true;

        while(!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                Pair p = q.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = p.x + dx[j];
                    int ny = p.y + dy[j];

                    if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                        if (!visit[nx][ny] && graph[nx][ny] == 0) {
                            visit[nx][ny] = true;
                            q.offer(new Pair(nx, ny));
                        }
                    }
                }
            }
        }
    }
}
