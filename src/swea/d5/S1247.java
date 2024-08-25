package swea.d5;

import java.util.*;

/**
 * 최적 경로
 *
 * 1. 문제 정리
 * 	1-1. 회사에서 출발, n 명의 고객을 방문 후 집으로 돌아간다.
 * 	1-2. 회사좌표, 집의좌표, 고객좌표가 주어진다.
 * 	1-3. 회사에서 출발해 모든 고객을 거쳐, 집으로 돌아오는 최단거리
 *
 * 2. 순열을 통해 유저를 거치는 모든 경우의 수를 구한다.
 * 3. 경우의 수를 구할 때마다 거리를 구하고, 최소의 거리로 갱신한다.
 */
public class S1247 {

    static class Pair {
        int x, y;
        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static Pair company, house;
    static Pair[] customer, permu;
    static boolean[] visit;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = scan.nextInt();
            company = new Pair(scan.nextInt(), scan.nextInt());
            house = new Pair(scan.nextInt(), scan.nextInt());
            customer = new Pair[n];
            permu = new Pair[n];
            visit = new boolean[n];
            answer = Integer.MAX_VALUE;
            for (int idx = 0; idx < n; idx++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                customer[idx] = new Pair(x, y);
            }
            permutation(0);
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void permutation(int level) {
        // 3. 경우의 수를 구할 때마다 거리를 구하고, 최소의 거리로 갱신한다.
        if (level == n) {
            int dist = 0;
            Pair start = company;
            for (int i = 0; i < n; i++) {
                Pair end = permu[i];
                dist += Math.abs(start.x - end.x) + Math.abs(start.y - end.y);
                start = end;
            }
            dist += Math.abs(start.x - house.x) + Math.abs(start.y - house.y);
            answer = Math.min(answer, dist);
            return;
        }

        // 2. 순열을 통해 유저를 거치는 모든 경우의 수를 구한다.
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                permu[level] = customer[i];
                permutation(level + 1);
                visit[i] = false;
            }
        }
    }
}