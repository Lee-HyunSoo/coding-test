package baekjoon;

import java.util.*;

/**
 * 줄 세우기
 *
 * 1. 정점 n, 간선 정보가 주어진다.
 * 2. 정보를 바탕으로 위상정렬을 사용한다.
 * 	2-1. 입력 시 진입차수를 count 한다.
 * 	2-2. 진입 차수가 0인 정점을 저장한다.
 * 	2-3. 하나씩 poll 하며, 다음 정점의 진입 차수를 줄인다.
 * 	2-4. 줄인 후 진입 차수가 0 이됐으면 가장 우선순위라는 뜻이기 때문에 배열에 저장한다.
 */
public class M2252 {

    static int n, m;
    static List<Integer>[] adj;
    static int[] indegree;
    static List<Integer> answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        adj = new ArrayList[n + 1];
        indegree = new int[n + 1];
        answer = new ArrayList<>();
        for (int idx = 0; idx <= n; idx++) {
            adj[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < m; idx++) {
            int from = scan.nextInt();
            int to = scan.nextInt();
            adj[from].add(to);
            // 2-1. 입력 시 진입차수를 count 한다.
            indegree[to]++;
        }

        topologicalSort();
        for (int student : answer) {
            System.out.print(student + " ");
        }
    }

    private static void topologicalSort() {
        Queue<Integer> q = new ArrayDeque<>();
        for (int idx = 1; idx <= n; idx++) {
            // 2-2. 진입 차수가 0인 정점을 저장한다.
            if (indegree[idx] == 0) {
                q.offer(idx);
            }
        }

        while(!q.isEmpty()) {
            int node = q.poll();
            answer.add(node);
            // 2-3. 하나씩 poll 하며, 다음 정점의 진입 차수를 줄인다.
            for (int vertex : adj[node]) {
                indegree[vertex]--;
                // 2-4. 줄인 후 진입 차수가 0 이됐으면 가장 우선순위라는 뜻이기 때문에 배열에 저장한다.
                if (indegree[vertex] == 0) {
                    q.offer(vertex);
                }
            }
        }
    }
}
