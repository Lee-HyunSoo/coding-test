package swea.d4;

import java.util.*;

/**
 * 키 순서
 *
 * 1. 문제 정리
 * 	1-1. 자신으로 들어오는 방향 탐색, 자신이 나가는 방향을 탐색해 모든 노드를 찾을 수 있다면 count + 1
 *
 * 2. 2개의 인접리스트를 활용한다.
 * 	2-1. 자신에서 나가는 방향의 인접리스트
 * 	2-2. 자신으로 들어오는 방향의 인접리스트
 * 	2-3. 각각의 인접리스트를 탐색한다.
 * 	2-4. 탐색 후 모든 정점에 방문 가능했다면, count + 1
 */
public class S5643 {

    static Scanner scan = new Scanner(System.in);
    static int n, m;
    // 2. 2개의 인접리스트를 활용한다.
    static List<Integer>[] out;
    static List<Integer>[] in;
    static boolean[] visit;

    public static void main(String[] args) {
        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            init();
            int totalCount = 0;
            for (int start = 1; start <= n; start++) {
                visit = new boolean[n + 1];
                // 2-3. 각각의 인접리스트를 탐색한다.
                searchOut(start);
                searchIn(start);
                visit[start] = true;
                // 2-4. 탐색 후 모든 정점에 방문 가능했다면, count + 1
                if (isPossible()) {
                    totalCount++;
                }
            }
            System.out.println("#" + tc + " " + totalCount);
        }
    }

    private static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        out = new ArrayList[n + 1];
        in = new ArrayList[n + 1];
        for (int idx = 1; idx <= n; idx++) {
            in[idx] = new ArrayList<>();
            out[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < m; idx++) {
            int start = scan.nextInt();
            int end = scan.nextInt();
            // 2-1. 자신에서 나가는 방향의 인접리스트
            out[start].add(end);
            // 2-2. 자신으로 들어오는 방향의 인접리스트
            in[end].add(start);
        }

    }

    private static void searchOut(int start) {
        for (int end : out[start]) {
            if (!visit[end]) {
                visit[end] = true;
                searchOut(end);
            }
        }
    }

    private static void searchIn(int start) {
        for (int end : in[start]) {
            if (!visit[end]) {
                visit[end] = true;
                searchIn(end);
            }
        }
    }

    private static boolean isPossible() {
        for (int idx = 1; idx <= n; idx++) {
            if (!visit[idx]) {
                return false;
            }
        }
        return true;
    }
}
