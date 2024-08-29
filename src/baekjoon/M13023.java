package baekjoon;

import java.util.*;

/**
 * ABCDE
 *
 * 1. 문제 정리
 * 	1-1. n 명의 사람이 있다.
 * 	1-2. 이 사람들 중 a -> b, b -> c, c -> d, d -> e 가 성립하는지 확인
 * 	1-3. 즉 n 명중 5명이 기차가 된다면 성공이다.
 *
 * 2. 인접리스트 사용
 * 	2-1. 시간초과를 방지하기 위해 인접리스트를 사용한다.
 * 	2-2. a -> b 라는 것은 b -> a 도 가능하다는 뜻이다.
 *
 * 3. dfs 탐색
 * 	3-1. 0번 사람부터 탐색 시작
 * 	3-2. 파라미터로 넘어온 사람에 연결 된 모든 사람을 탐색한다.
 * 	3-3. 탐색을 진행하다가 5명이 기차가되면 (level == 4, 0부터 시작했기 때문) 종료
 */
public class M13023 {

    static int n, m;
    static List<Integer>[] adj;
    static boolean[] visit;
    static boolean flag;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        visit = new boolean[n];
        adj = new ArrayList[n];
        flag = false;
        // 2-1. 시간초과를 방지하기 위해 인접리스트를 사용한다.
        for (int idx = 0; idx < n; idx++) {
            adj[idx] = new ArrayList<>();
        }
        // 2-2. a -> b 라는 것은 b -> a 도 가능하다는 뜻이다.
        for (int idx = 0; idx < m; idx++) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }

        for (int row = 0; row < n; row++) {
            // 3-1. 0번 사람부터 탐색 시작
            friends(0, row);
            if (flag) {
                break;
            }
        }
        System.out.println(flag ? 1 : 0);
    }

    private static void friends(int level, int row) {
        // 3-3. 탐색을 진행하다가 5명이 기차가되면 (level == 4, 0부터 시작했기 때문) 종료
        if (level == 4) {
            flag = true;
            return;
        }

        visit[row] = true;
        for (int col : adj[row]) {
            // 3-2. 파라미터로 넘어온 사람에 연결 된 모든 사람을 탐색한다.
            if (!visit[col]) {
                friends(level + 1, col);
            }
        }
        visit[row] = false;
    }
}
