package baekjoon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 스타트 링크
 */
public class M5014 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int f = scan.nextInt(); // 총 몇층
        int s = scan.nextInt(); // 시작 지점
        int g = scan.nextInt(); // 도착 지점
        int u = scan.nextInt(); // 위로 u 만큼
        int d = scan.nextInt(); // 아래로 d 만큼

        int[] graph = new int[f + 1];
        boolean[] visit = new boolean[f + 1];
        Arrays.fill(graph, Integer.MAX_VALUE);

        Queue<Integer> q = new LinkedList<>();
        graph[s] = 0;
        q.offer(s);
        while (!q.isEmpty()) {
            int len = q.size();

            boolean flag = false;
            for (int i = 0; i < len; i++) {
                int pos = q.poll();

                if (pos == g) {
                    flag = true;
                    break;
                }

                int up = pos + u;
                int down = pos - d;
                if (up <= f && !visit[up]) {
                    visit[up] = true;
                    graph[up] = Math.min(graph[up], graph[pos] + 1);
                    q.offer(up);
                }
                if (1 <= down && !visit[down]) {
                    visit[down] = true;
                    graph[down] = Math.min(graph[down], graph[pos] + 1);
                    q.offer(down);
                }
            }

            if (flag) {
                break;
            }
        }

        if (graph[g] == Integer.MAX_VALUE) {
            System.out.println("use the stairs");
        } else {
            System.out.println(graph[g]);
        }
    }
}
