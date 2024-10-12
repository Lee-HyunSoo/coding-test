package codetree;

import java.util.*;

/**
 * 코드트리 투어
 *
 * 1. 문제 정리
 *  1-1. 코드트리 랜드는 아래와 같이 n 개의 도시와 각 도시 사이를 연결하는 m 개의 간선으로 이루어져 있습니다.
 *  1-2. 각 도시는 0 번부터 n−1 번까지 번호가 붙여져 있으며, 각 간선은 방향성을 갖지 않습니다. (양방향 그래프)
 *  1-3. 또, 두 도시 사이를 연결하는 간선은 여러 개가 존재할 수 있으며, 자기 자신을 향하는 간선 또한 존재할 수 있습니다. (사이클 존재)
 *  1-4. 처음 코드트리 여행 상품의 출발지는 0번 도시입니다.
 *
 * 2. 코드트리 랜드 건설
 *  2-1. 도시의 수 n, 간선의 수 m
 *  2-2. m 개의 간선에 해당하는 정보 (v, u, w) -> (출발지, 도착지, 비용)
 *  2-3. 최초 1번만 수행
 *  2-4. 미리 다익스트라를 돌려 최단경로를 구해둔다.
 *
 * 3. 여행 상품 생성
 *  3-1. (id, revenue, dest) -> (고유 id, 매출, 도착지)
 *  3-2. 최대 30000번 수행
 *  3-3. 이 때 미리 최적의 여행 상품을 계산한다.
 *      3-3-1. 상품 판매 시 마다 id 를 통해 순회하면 시간 복잡도 over
 *
 * 4. 여행 상품 취소
 *  4-1. 고유 id 에 해당하는 상품 삭제
 *  4-2. 최대 30000번 수행
 *
 * 5. 최적의 여행 상품 판매
 *  5-1. 최적의 여행 상품 = revenue - cost 가 최대인 상품
 *  5-2. 계산한 값이 동일하면 더 작은 id 가 우선순위
 *  5-3. 최대 30000번 수행
 *  5-4. 이 때 취소 된 상품인지 확인해야한다.
 *      5-4-1. 확인 후 있는 상품이면, pq 에서 뽑는 것 뿐만 아니라 id 도 삭제해야한다.
 *      5-4-2. 그래야 출발지가 변경되었을 때 다시 생성되지 않는다.
 *
 * 6. 여행 상품의 출발지 변경
 *  6-1. 출발지를 변경
 *  6-2. 최대 15번 수행
 *  6-3. 출발지가 바뀌었기 때문에 최적의 여행 상품을 다시 계산한다.
 */
public class 코드트리투어 {

    static class Edge {
        int v, cost;

        public Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    static class Goods {
        int id, cost;

        public Goods(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }
    }
    static Scanner scan = new Scanner(System.in);

    static int n, m; // 노드 수, 간선 수
    static List<Edge>[] edges;
    static Map<Integer, Integer> revenues = new HashMap<>();
    static Map<Integer, Integer> dests = new HashMap<>();
    static Queue<Goods> goods;
    static int[] costs;


    public static void main(String[] args) {
        int q = scan.nextInt();
        while (q-- > 0) {
            int command = scan.nextInt();
            if (command == 100) {
                createLand();
            } else if (command == 200) {
                createGoods();
            } else if (command == 300) {
                deleteGoods();
            } else if (command == 400) {
                sellGoods();
            } else if (command == 500) {
                int start = scan.nextInt();
                changeStart(start);
            }
        }
    }

    // 2. 코드트리 랜드 건설
    // 2-3. 최초 1번만 수행
    private static void createLand() {
        // 2-1. 도시의 수 n, 간선의 수 m
        n = scan.nextInt();
        m = scan.nextInt();

        edges = new ArrayList[n];
        for (int idx = 0; idx < n; idx++) {
            edges[idx] = new ArrayList<>();
        }

        // 2-2. m 개의 간선에 해당하는 정보 (v, u, w) -> (출발지, 도착지, 비용)
        for (int idx = 0; idx < m; idx++) {
            int from = scan.nextInt();
            int to = scan.nextInt();
            int cost = scan.nextInt();
            edges[from].add(new Edge(to, cost));
            edges[to].add(new Edge(from, cost));
        }

        // 2-4. 미리 다익스트라를 돌려 최단경로를 구해둔다.
        changeStart(0);
        goods = new PriorityQueue<>((a, b) -> {
            if (b.cost == a.cost) {
                return a.id - b.id;
            }
            return b.cost - a.cost;
        });
    }

    // 3. 여행 상품 생성
    // 3-2. 최대 30000번 수행
    private static void createGoods() {
        // 3-1. (id, revenue, dest) -> (고유 id, 매출, 도착지)
        int id = scan.nextInt();
        int revenue = scan.nextInt();
        int dest = scan.nextInt();

        revenues.put(id, revenue);
        dests.put(id, dest);

        // 3-3. 이 때 미리 최적의 여행 상품을 계산한다.
        // 3-3-1. 상품 판매 시 마다 id 를 통해 순회하면 시간 복잡도 over
        if (costs[dest] != Integer.MAX_VALUE && revenue - costs[dest] >= 0) {
            goods.offer(new Goods(id, revenue - costs[dest]));
        }
    }

     // 4. 여행 상품 취소
     // 4-2. 최대 30000번 수행
     private static void deleteGoods() {
        int id = scan.nextInt();
        // 4-1. 고유 id 에 해당하는 상품 삭제
        if (revenues.containsKey(id)) {
            revenues.remove(id);
            dests.remove(id);
        }
    }

    // 5. 최적의 여행 상품 판매
    // 5-3. 최대 30000번 수행
    private static void sellGoods() {
        // 5-1. 최적의 여행 상품 = revenue - cost 가 최대인 상품
        // 5-2. 계산한 값이 동일하면 더 작은 id 가 우선순위
        while (!goods.isEmpty()) {
            Goods g = goods.poll();

            // 5-4. 이 때 취소 된 상품인지 확인해야한다.
            if (!revenues.containsKey(g.id)) {
                continue;
            }
            // 5-4-1. 확인 후 있는 상품이면, pq 에서 뽑는 것 뿐만 아니라 id 도 삭제해야한다.
            // 5-4-2. 그래야 출발지가 변경되었을 때 다시 생성되지 않는다.
            revenues.remove(g.id);
            dests.remove(g.id);
            System.out.println(g.id);
            return;
        }
        System.out.println(-1);
    }

    // 6. 여행 상품의 출발지 변경
    // 6-2. 최대 15번 수행
    private static void changeStart(int start) {
        // 6-1. 출발지를 변경
        costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.offer(new Edge(start, 0));
        costs[start] = 0;

        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            if (costs[e.v] < e.cost) {
                continue;
            }

            for (Edge edge : edges[e.v]) {
                int newCost = e.cost + edge.cost;
                if (costs[edge.v] > newCost) {
                    costs[edge.v] = newCost;
                    pq.offer(new Edge(edge.v, newCost));
                }
            }
        }

        // 6-3. 출발지가 바뀌었기 때문에 최적의 여행 상품을 다시 계산한다.
        goods = new PriorityQueue<>((a, b) -> {
            if (b.cost == a.cost) {
                return a.id - b.id;
            }
            return b.cost - a.cost;
        });
        for (Map.Entry<Integer, Integer> e : revenues.entrySet()) {
            int id = e.getKey();
            int revenue = e.getValue();
            int dest = dests.get(id);

            if (costs[dest] != Integer.MAX_VALUE && revenue - costs[dest] >= 0) {
                goods.offer(new Goods(id, revenue - costs[dest]));
            }
        }
    }
}
