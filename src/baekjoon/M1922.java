package baekjoon;

import java.util.*;

/**
 * 네트워크 연결 (크루스칼)
 * 1. 문제 정리
 * 	1-1. 비용을 최소로하여 모든 컴퓨터를 연결해라.
 *
 * 2. 크루스칼 알고리즘
 * 	2-1. 시작점, 도착점, 비용을 저장하는 클래스를 만든다.
 * 	2-2. 해당 클래스를 저장한 리스트를 비용순으로 정렬한다.
 * 	2-3. 시작점, 도착점의 대표자를 뽑아 서로 다르다면 union 하고, 비용을 더한다.
 */
public class M1922 {

    // 2-1. 시작점, 도착점, 비용을 저장하는 클래스를 만든다.
    static class Edge {
        int start, end, cost;

        Edge (int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    static int n, m;
    static int[] unf;
    static List<Edge> edges;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        init();

        edges = new ArrayList<>();
        for (int idx = 0; idx < m; idx++) {
            edges.add(new Edge(scan.nextInt(), scan.nextInt(), scan.nextInt()));
        }
        // 2-2. 해당 클래스를 저장한 리스트를 비용순으로 정렬한다.
        edges.sort((a, b) -> (a.cost - b.cost));

        int answer = 0;
        for (Edge edge : edges) {
            // 2-3. 시작점, 도착점의 대표자를 뽑아 서로 다르다면 union 하고, 비용을 더한다.
            int start = find(edge.start);
            int end = find(edge.end);
            if (start != end) {
                answer += edge.cost;
                union(start, end);
            }
        }
        System.out.println(answer);
    }

    private static void init() {
        unf = new int[n + 1];
        for (int idx = 0; idx <= n; idx++) {
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
