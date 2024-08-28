package swea.d4;

import java.util.*;

/**
 * Contact
 *
 * 1. 문제 정리
 * 	1-1. 시작점이 주어지고, 이어진 진출노드에 연락한다.
 * 	1-2. 이어진 진출노드를 새로운 시작점으로, 또 이어진 진출노드에 연락한다.
 * 	1-3. 마지막까지 연락한 진출노드들 중 최대값?
 *
 * 2. 인원이 최대 100명이기 때문에, 1~100을 저장할 인접리스트를 생성한다.
 * 3. bfs 를 사용한다.
 * 	3-1. 출발점을 poll 한다.
 * 	3-2. 이 때 poll 한 출발점들 중 최대값을 구해 갱신한다.
 * 	3-3. 출발점과 이어진 진출노드가 방문한적이 없는 지점이면, q 에 넣는다.
 */
public class S1238 {

    static int n, m;
    static List<Integer>[] adj;
    static boolean[] visit;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        for (int tc = 1; tc <= 10; tc++) {
            n = scan.nextInt();
            m = scan.nextInt();
            adj = new ArrayList[101];
            visit = new boolean[101];
            // 2. 인원이 최대 100명이기 때문에, 1~100을 저장할 인접리스트를 생성한다.
            for (int idx = 1; idx <= 100; idx++) {
                adj[idx] = new ArrayList<>();
            }

            for (int idx = 0; idx < n / 2; idx++) {
                int from = scan.nextInt();
                int to = scan.nextInt();
                adj[from].add(to);
            }
            // 3. bfs 를 사용한다.
            contact();
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void contact() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(m);
        visit[m] = true;

        while(!q.isEmpty()) {
            int size = q.size();
            answer = 0;
            while(size-- > 0) {
                // 3-1. 출발점을 poll 한다.
                int from = q.poll();
                // 3-2. 이 때 poll 한 출발점들 중 최대값을 구해 갱신한다.
                answer = Math.max(answer, from);
                // 3-3. 출발점과 이어진 진출노드가 방문한적이 없는 지점이면, q 에 넣는다.
                for (int to : adj[from]) {
                    if (!visit[to]) {
                        visit[to] = true;
                        q.offer(to);
                    }
                }
            }
        }
    }
}
