package programmers;

import java.util.*;

/*
다익스트라

s -> 경유지
경유지 -> a
경유지 -> b

3개의 dist 배열
*/
public class Lv3_합승택시요금 {

    static class Edge implements Comparable<Edge> {
        int end, cost;

        Edge (int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }
    }

    int[] dist1, dist2, dist3; // s, a, b
    List<Edge>[] edges;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        init(n, fares);
        getDist(s, dist1);
        getDist(a, dist2);
        getDist(b, dist3);

        int answer = Integer.MAX_VALUE;
        for (int idx = 1; idx <= n; idx++) {
            int minDist = dist1[idx] + dist2[idx] + dist3[idx];
            answer = Math.min(answer, minDist);
        }

        // for (int d : dist1) {
        //     System.out.print(d + " ");
        // }
        // System.out.println();
        // for (int d : dist2) {
        //     System.out.print(d + " ");
        // }
        // System.out.println();
        // for (int d : dist3) {
        //     System.out.print(d + " ");
        // }
        // System.out.println();

        return answer;
    }

    private void getDist(int start, int[] dist) {
        Queue<Edge> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Edge(start, dist[start]));

        while(!pq.isEmpty()) {
            Edge e = pq.poll();

            if (dist[e.end] < e.cost) {
                continue;
            }
            for (Edge edge : edges[e.end]) {
                int newCost = edge.cost + e.cost;
                if (newCost < dist[edge.end]) {
                    dist[edge.end] = newCost;
                    pq.offer(new Edge(edge.end, newCost));
                }
            }
        }
    }

    private void init(int n, int[][] fares) {
        edges = new ArrayList[n + 1];
        for (int idx = 1; idx <= n; idx++) {
            edges[idx] = new ArrayList<>();
        }
        for (int[] fare : fares) {
            edges[fare[0]].add(new Edge(fare[1], fare[2]));
            edges[fare[1]].add(new Edge(fare[0], fare[2]));
        }

        dist1 = new int[n + 1];
        dist2 = new int[n + 1];
        dist3 = new int[n + 1];
        Arrays.fill(dist1, Integer.MAX_VALUE);
        Arrays.fill(dist2, Integer.MAX_VALUE);
        Arrays.fill(dist3, Integer.MAX_VALUE);
    }
}
