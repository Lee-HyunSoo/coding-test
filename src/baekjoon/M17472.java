package baekjoon;

import java.util.*;

/**
 * 다리 만들기 2
 */
public class M17472 {

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static Scanner scan = new Scanner(System.in);

    static int n, m, land, answer;
    static int[][] graph;
    static boolean[][] visit;
    static int[] parent;
    static Queue<Edge> bridge;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        init();
        makeLand();
        makeBridge();
        findShortest();
        System.out.println(answer);
    }

    private static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        land = 1;
        graph = new int[n][m];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                graph[row][col] = scan.nextInt();
            }
        }
        visit = new boolean[n][m];
        bridge = new PriorityQueue<>((a, b) -> (a.cost - b.cost));
        answer = 0;
    }

    private static void makeLand() {
        // bfs 를 통해 다리를 종류별로 나눈다.
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (!visit[row][col] && graph[row][col] == 1) {
                    bfs(new Pair(row, col), land++);
                }
            }
        }
    }

    private static void bfs(Pair start, int land) {
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(start);
        graph[start.x][start.y] = land;
        visit[start.x][start.y] = true;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Pair p = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        continue;
                    }
                    if (visit[nx][ny] || graph[nx][ny] == 0) {
                        continue;
                    }
                    visit[nx][ny] = true;
                    graph[nx][ny] = land;
                    q.offer(new Pair(nx, ny));
                }
            }
        }
    }

    private static void makeBridge() {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (graph[row][col] > 0) {
                    int startLand = graph[row][col];
                    // 현재 위치가 다리라면 4방향으로 뻗어나간다.
                    for (int d = 0; d < 4; d++) {
                        int nx = row + dx[d];
                        int ny = col + dy[d];

                        // 다리 건설
                        int size = 0;
                        while (0 <= nx && nx < n && 0 <= ny && ny < m && graph[nx][ny] == 0) {
                            size++;
                            nx += dx[d];
                            ny += dy[d];
                        }
                        // size 가 1 보다 크면 다리로 등록
                        if (0 <= nx && nx < n && 0 <= ny && ny < m && graph[nx][ny] > 0) {
                            if (size > 1) {
                                int endLand = graph[nx][ny];
                                bridge.offer(new Edge(startLand, endLand, size));
                            }
                        }
                    }
                }
            }
        }
    }

    private static void findShortest() {
        // 다리별 부모배열 생성
        parent = new int[land];
        for (int idx = 1; idx < land; idx++) {
            parent[idx] = idx;
        }

        int sum = 0;
        int connect = 0;
        // 다리끼리 부모가 다르다면 pq 에서 뽑아 길이를 더한다.
        while (!bridge.isEmpty()) {
            Edge e = bridge.poll();
            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                sum += e.cost;
                connect++;
            }
        }

        // 모든 섬이 연결되어있는지 확인 (부모가 같은지)
        if (connect != land - 2) {
            answer = -1;
        } else {
            answer = sum;
        }
    }

    private static int find(int v) {
        if (v == parent[v]) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }

    private static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if (fa != fb) {
            parent[fa] = fb;
        }
    }
}
