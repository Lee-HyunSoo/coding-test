package baekjoon;

import java.util.*;

/**
 * 최단경로
 *
 * 1. 문제 정리
 * 	1-1. 방향그래프, 주어진 시작점에서 다른 모든 정점으로의 최단 경로
 *  1-2. 모든 간선의 가중치는 10 이하이다.
 *
 * 2. 다익스트라
 * 	2-1. 알고리즘 시작 전 dist 배열들을 최대값으로 초기화한다. (다익스트라는 음의 가중치가 없기때문)
 * 	2-2. pq 에 시작점과 가중치 0을 넣는다.
 * 	2-3. 시작점의 거리를 0으로 초기화한다. (현재는 시작-도착이 동일하기때문)
 * 	2-4. q 에서 정점을 하나 뽑는다.
 * 	2-5. 뽑은 정점까지의 비용이 dist 배열의 값보다 크다면 continue
 * 		2-5-1. 기존에 기록해둔 dist 배열보다 크면 더 비싼 비용이 드는 것이기 때문
 * 	2-6. dist 배열의 값보다 작다면, 해당 정점을 시작점으로 도착점을 탐색한다.
 * 	2-7. 현재까지의 비용 + 도착점까지의 비용이 dist 배열 보다 작다면, dist 배열 갱신
 * 	2-8. 갱신 후 도착점, 현재까지의 비용 + 도착점까지의 비용을 q 에 넣는다.
 */
public class M1753 {

    static class Edge {
        int end, cost;

        Edge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

    static Scanner scan;
    static int v, e, start;
    static List<Edge>[] edges;
    static int[] dist;

    public static void main(String[] args) {
        init();
        dijkstra();
        print();
    }

    private static void init() {
        scan = new Scanner(System.in);
        v = scan.nextInt();
        e = scan.nextInt();
        start = scan.nextInt();
        edges = new ArrayList[v + 1];
        dist = new int[v + 1]; // 누적 비용을 저장하는 배열
        for (int idx = 1; idx <= v; idx++) {
            edges[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < e; idx++) {
            int from = scan.nextInt();
            int to = scan.nextInt();
            int cost = scan.nextInt();
            edges[from].add(new Edge(to, cost));
        }
    }

    private static void dijkstra() {
        // 2-1. 알고리즘 시작 전 dist 배열들을 최대값으로 초기화한다. (다익스트라는 음의 가중치가 없기때문)
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<Edge> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        // 2-2. pq 에 시작점과 가중치 0을 넣는다.
        pq.offer(new Edge(start, 0));
        // 2-3. 시작점의 거리를 0으로 초기화한다. (현재는 시작-도착이 동일하기때문)
        dist[start] = 0;

        while (!pq.isEmpty()) {
            // 2-4. q 에서 정점을 하나 뽑는다.
            Edge e = pq.poll();
            // 2-5. 뽑은 정점까지의 비용이 dist 배열의 값보다 크다면 continue
            if (dist[e.end] < e.cost) {
                continue;
            }
            // 2-6. dist 배열의 값보다 작다면, 해당 정점을 시작점으로 도착점을 탐색한다.
            for (Edge edge : edges[e.end]) {
                int newCost = e.cost + edge.cost;
                // 2-7. 현재까지의 비용 + 도착점까지의 비용이 dist 배열 보다 작다면, dist 배열 갱신
                if (dist[edge.end] > newCost) {
                    dist[edge.end] = newCost;
                    // 2-8. 갱신 후 도착점, 현재까지의 비용 + 도착점까지의 비용을 q 에 넣는다.
                    pq.offer(new Edge(edge.end, newCost));
                }
            }
        }
    }

    private static void print() {
        for (int idx = 1; idx <= v; idx++) {
            System.out.println(dist[idx] == Integer.MAX_VALUE ? "INF" : dist[idx]);
        }
    }
}
