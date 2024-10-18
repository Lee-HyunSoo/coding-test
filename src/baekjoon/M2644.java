package baekjoon;

import java.util.*;

/**
 * 촌수계산
 */
public class M2644 {

    static Scanner scan = new Scanner(System.in);
    static int n, a, b, m;
    static List<Integer>[] family;

    public static void main(String[] args) {
        init();
        System.out.println(findDist());
    }

    private static void init() {
        n = scan.nextInt();
        a = scan.nextInt();
        b = scan.nextInt();
        family = new ArrayList[n + 1];
        for (int idx = 1; idx <= n; idx++) {
            family[idx] = new ArrayList<>();
        }

        m = scan.nextInt();
        for (int idx = 0; idx < m; idx++) {
            int parent = scan.nextInt();
            int child = scan.nextInt();
            family[parent].add(child);
            family[child].add(parent);
        }
    }

    private static int findDist() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(a);
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[a] = 0;

        while (!q.isEmpty()) {
            int parent = q.poll();
            for (int child : family[parent]) {
                if (distance[child] == Integer.MAX_VALUE) {
                    distance[child] = distance[parent] + 1;
                    q.offer(child);
                }
            }
        }
        return distance[b] == Integer.MAX_VALUE ? -1 : distance[b];
    }
}
