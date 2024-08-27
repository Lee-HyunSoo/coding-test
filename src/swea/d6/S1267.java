package swea.d6;

import java.util.*;

/**
 * 작업 순서
 *
 * 1. 문제 정리
 * 	1-1. 싸이클이 없는 v 개의 작업들이 있다.
 * 	1-2. 어떤 작업은 특정 작업이 끝나야 시작될 수 있다. 즉 선행 작업이 있다.
 * 	1-3. 위상정렬 사용
 *
 * 2. 위상정렬
 * 	2-1. 인접리스트, 진입차수를 저장할 배열을 선언한다.
 * 	2-2. 인접리스트 초기화 시 to 쪽의 진입차수를 1씩 증가시킨다.
 * 	2-3. 큐를 선언하고, 진입차수가 0인 노드들을 넣어 초기화한다. (진입차수 낮을수록 우선순위)
 * 	2-4. 큐에서 노드를 뽑는다. (출발지점)
 * 		2-4-1. 이때 뽑은 노드를 하나씩 리스트에 넣는다. (결과 배열)
 * 	2-5. 해당 노드를 통해 도착지를 구하고, 해당 도착지의 진입차수를 1씩 감소한다.
 * 	2-6. 감소 후 진입차수가 0이 됐다면, q에 넣는다.
 */
public class S1267 {

    static int v, e;
    static List<Integer>[] adj;
    static int[] indegree;
    static List<Integer> answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        for (int tc = 1; tc <= 10; tc++) {
            v = scan.nextInt();
            e = scan.nextInt();
            // 2-1. 인접리스트, 진입차수를 저장할 배열을 선언한다.
            adj = new ArrayList[v + 1];
            indegree = new int[v + 1];
            answer = new ArrayList<>();
            for (int idx = 0; idx <= v; idx++) {
                adj[idx] = new ArrayList<>();
            }

            for (int idx = 0; idx < e; idx++) {
                int from = scan.nextInt();
                int to = scan.nextInt();
                // 2-2. 인접리스트 초기화 시 to 쪽의 진입차수를 1씩 증가시킨다.
                adj[from].add(to);
                indegree[to]++;
            }

            topologicalSort();
            System.out.print("#" + tc + " ");
            for (int idx = 0; idx < answer.size(); idx++) {
                System.out.print(answer.get(idx) + " ");
            }
            System.out.println();
        }
    }

    private static void topologicalSort() {
        // 2-3. 큐를 선언하고, 진입차수가 0인 노드들을 넣어 초기화한다. (진입차수 낮을수록 우선순위)
        Queue<Integer> q = new ArrayDeque<>();
        for (int idx = 1; idx <= v; idx++) {
            if (indegree[idx] == 0) {
                q.offer(idx);
            }
        }

        while(!q.isEmpty()) {
            // 2-4. 큐에서 노드를 뽑는다. (출발지점)
            int node = q.poll();
            // 2-4-1. 이때 뽑은 노드를 하나씩 리스트에 넣는다. (결과 배열)
            answer.add(node);

            // 2-5. 해당 노드를 통해 도착지를 구하고, 해당 도착지의 진입차수를 1씩 감소한다.
            for (int vertex : adj[node]) {
                indegree[vertex]--;

                // 2-6. 감소 후 진입차수가 0이 됐다면, q에 넣는다.
                if (indegree[vertex] == 0) {
                    q.offer(vertex);
                }
            }
        }
    }
}
