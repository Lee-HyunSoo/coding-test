package swea.d4;

import java.util.*;

/**
 * 최소 스패닝 트리
 *
 * 1. 문제 정리
 * 	1-1. 가중치의 합이 최소인 트리를 구해라. (크루스칼)
 *
 * 2. 크루스칼 알고리즘
 * 	2-1. 시작점, 도착점, 비용을 저장하는 클래스를 만든다.
 * 	2-2. 해당 클래스를 저장한 리스트를 비용순으로 정렬한다.
 * 	2-3. 시작점, 도착점의 대표자를 뽑아 서로 다르다면 union 하고, 비용을 더한다.
 */
public class S3124 {

    // 2-1. 시작점, 도착점, 비용을 저장하는 클래스를 만든다.
    static class Edge {
        int start, end, cost;

        Edge (int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    static int v, e;
    static int[] unf;
    static Edge[] edges;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            v = scan.nextInt();
            e = scan.nextInt();
            edges = new Edge[e];
            for (int idx = 0; idx < e; idx++) {
                edges[idx] = new Edge(scan.nextInt(), scan.nextInt(), scan.nextInt());
            }
            init();
            // 2-2. 해당 클래스를 저장한 리스트를 비용순으로 정렬한다.
            Arrays.sort(edges, (a, b) -> a.cost - b.cost);

            long answer = 0;
            for (Edge edge : edges) {
                // 2-3. 시작점, 도착점의 대표자를 뽑아 서로 다르다면 union 하고, 비용을 더한다.
                int start = find(edge.start);
                int end = find(edge.end);

                if (start != end) {
                    answer += edge.cost;
                    union(start, end);
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void init() {
        unf = new int[v + 1];
        for (int idx = 0; idx <= v; idx++) {
            unf[idx] = idx;
        }
    }

    private static int find(int v) {
        if (v == unf[v]) {
            return v;
        }
        return unf[v] = find(unf[v]);
    }

    private static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            unf[fb] = fa;
        }
    }
}
