package swea.d4;

import java.util.*;

/**
 * [S/W 문제해결 응용] 4일차 - 하나로
 *
 * 1. 문제 정리
 * 	1-1. 두 섬을 연결한다.
 * 	1-2. 직접적으로 연결해도 되지만, 간접적으로 연결될 수도 있다.
 * 	1-3. 환경 부담 세율 e, 각 해저터널의 길이 l
 * 	1-4. 환경 부담금 : e * l^2
 * 	1-5. 환경 부담금을 최소로 지불하며 모든 섬을 연결하여라.
 *
 * 2. 좌표와 idx 를 관리하는 클래스를 생성한다.
 * 	2-1. idx 가 있어야 union-find 시 대표자를 설정하기 용이하기 때문
 *
 * 3. 시작좌표, 도착좌표, 비용을 저장하는 클래스를 생성한다.
 * 	3-1. 일단 가능한 모든 간선을 생성하기 위해 반복문을 사용한다.
 *
 * 4. 크루스칼 알고리즘
 * 	4-1. 간선들을 비용 순으로 오름차순 정렬한다.
 * 	4-2. 간선을 하나 뽑아, 양쪽 좌표의 대표자가 다르면 비용 계산 후 합친다.
 * 	4-3. 다 계산 후, 마지막에 반올림 해준다.
 */
public class S1251 {

    // 2. 좌표와 idx 를 관리하는 클래스를 생성한다.
    static class Pair {
        int x, y, idx;
        Pair (int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
    }

    // 3. 시작좌표, 도착좌표, 비용을 저장하는 클래스를 생성한다.
    static class Edge {
        Pair start, end;
        double cost;

        Edge(Pair start, Pair end, double cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    static int n;
    static int[] unf;
    static Pair[] pairs;
    static List<Edge> edges;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = scan.nextInt();
            scan.nextLine();
            String[] x = scan.nextLine().split(" ");
            String[] y = scan.nextLine().split(" ");
            double E = scan.nextDouble();
            scan.nextLine();
            unf = new int[n];
            pairs = new Pair[n];
            edges = new ArrayList<>();
            init();

            for (int idx = 0; idx < n; idx++) {
                // 2-1. idx 가 있어야 union-find 시 대표자를 설정하기 용이하기 때문
                Pair p = new Pair(Integer.parseInt(x[idx]), Integer.parseInt(y[idx]), idx);
                pairs[idx] = p;
            }

            // 3-1. 일단 가능한 모든 간선을 생성하기 위해 반복문을 사용한다.
            for (int row = 0; row < n; row++) {
                for (int col = row + 1; col < n; col++) {
                    edges.add(new Edge(pairs[row], pairs[col], getCost(pairs[row], pairs[col])));
                }
            }
            // 4-1. 간선들을 비용 순으로 오름차순 정렬한다.
            edges.sort((a, b) -> Double.compare(a.cost, b.cost));

            double answer = 0;
            for (Edge edge : edges) {
                // 4-2. 간선을 하나 뽑아, 양쪽 좌표의 대표자가 다르면 비용 계산 후 합친다.
                int start = find(edge.start.idx);
                int end = find(edge.end.idx);

                if (start != end) {
                    answer += E * edge.cost * edge.cost;
                    union(start, end);
                }
            }
            // 4-3. 다 계산 후, 마지막에 반올림 해준다.
            System.out.println("#" + tc + " " + Math.round(answer));
        }
    }

    private static void init() {
        for (int idx = 0; idx < n; idx++) {
            unf[idx] = idx;
        }
    }

    private static double getCost(Pair p1, Pair p2) {
        double x = (double) Math.abs(p1.x - p2.x) * Math.abs(p1.x - p2.x);
        double y = (double) Math.abs(p1.y - p2.y) * Math.abs(p1.y - p2.y);
        return Math.sqrt(x + y);
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
