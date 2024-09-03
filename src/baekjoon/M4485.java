package baekjoon;

import java.util.*;

/**
 * 녹색 옷 입은 애가 젤다지?
 *
 * 1. 문제 정리
 * 	1-1. (0,0) 시작, (n-1, n-1) 까지 가야한다.
 * 	1-2. 가는 비용을 최소로 하며 갈 수 있는 방법?
 *
 * 2. 다익스트라
 * 	2-1. 최소비용을 저장할 이차원배열을 만든다.
 * 	2-2. 만든 이차원 배열을 INF 로 초기화한다.
 * 	2-3. 시작점의 비용은 graph[0][0] 로 초기화한다.
 * 	2-4. 기록된 누적비용이 새로 들어온 누적비용보다 적다면 continue
 * 	2-5. 아니라면, 상하좌우를 본다.
 * 	2-6. 누적비용 + 다음칸의 비용을 더해 새로운 비용을 구한다.
 * 	2-7. 비용을 갱신하고, q 에 담는다.
 */
public class M4485 {

    static class Edge {
        int x, y, cost;

        Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    static Scanner scan = new Scanner(System.in);
    static int n;
    static int[][] graph;
    static int[][] dist;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        int t = 1;
        while(true) {
            n = scan.nextInt();
            if (n == 0) {
                break;
            }
            init();
            int answer = dijkstra();
            System.out.println("Problem " + t++ + ": " + answer);
        }
    }

    private static void init() {
        graph = new int[n][n];
        // 2-1. 최소비용을 저장할 이차원배열을 만든다.
        dist = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                graph[row][col] = scan.nextInt();
                // 2-2. 만든 이차원 배열을 INF 로 초기화한다.
                dist[row][col] = Integer.MAX_VALUE;
            }
        }
    }

    private static int dijkstra() {
        Queue<Edge> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        // 2-3. 시작점의 비용은 graph[0][0] 로 초기화한다.
        dist[0][0] = graph[0][0];
        pq.offer(new Edge(0, 0, dist[0][0]));

        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            // 2-4. 기록된 누적비용이 새로 들어온 누적비용보다 적다면 continue
            if (dist[e.x][e.y] < e.cost) {
                continue;
            }
            // 2-5. 아니라면, 상하좌우를 본다.
            for (int d = 0; d < 4; d++) {
                int nx = e.x + dx[d];
                int ny = e.y + dy[d];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }
                // 2-6. 누적비용 + 다음칸의 비용을 더해 새로운 비용을 구한다.
                int newCost = e.cost + graph[nx][ny];
                if (dist[nx][ny] > newCost) {
                    // 2-7. 비용을 갱신하고, q 에 담는다.
                    dist[nx][ny] = newCost;
                    pq.offer(new Edge(nx, ny, newCost));
                }
            }
        }
        return dist[n - 1][n - 1];
    }
}
