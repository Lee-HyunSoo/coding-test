package baekjoon;

import java.util.*;

/**
 * 노드 사이의 거리
 *
 * 1. 문제 정리
 *  1-1. N개의 노드로 이루어진 트리가 주어지고 M개의 두 노드 쌍을 입력받을 때 두 노드 사이의 거리를 출력하라.
 *
 * 2. 인접리스트를 통한 BFS
 */
public class M1240 {

    static class Edge {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static Scanner scan = new Scanner(System.in);
    static int n, m;
    static List<Edge>[] adj;

    public static void main(String[] args) {
        init();
        for (int idx = 0; idx < m; idx++) {
            int from = scan.nextInt();
            int to = scan.nextInt();
            int dist = findDist(from, to);
            System.out.println(dist);
        }
    }

    private static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        adj = new ArrayList[n + 1];
        for (int idx = 1; idx <= n; idx++) {
            adj[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < n - 1; idx++) {
            int from = scan.nextInt();
            int to = scan.nextInt();
            int cost = scan.nextInt();
            adj[from].add(new Edge(to, cost));
            adj[to].add(new Edge(from, cost));
        }
    }

    private static int findDist(int from, int to) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(from);
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[from] = 0;

        while (!q.isEmpty()) {
            int start = q.poll();
            for (Edge end : adj[start]) {
                if (distance[end.to] == Integer.MAX_VALUE) {
                    distance[end.to] = distance[start] + end.cost;
                    q.offer(end.to);
                }
            }
        }
        return distance[to];
    }
}
