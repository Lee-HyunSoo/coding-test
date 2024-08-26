package baekjoon;

import java.util.*;

/**
 * 알파벳
 *
 * 1. 문제 정리
 * 	1-1. (0,0) 에서 출발, 사방으로 나가며 방문하지 않은 점만 나간다.
 * 	1-2. 이 때 최대로 나갈 수 있는 거리
 *
 * 2. 알파벳 숫자를 통해 visit 배열을 사용한다.
 * 3. 델타 배열을 사용, 사방으로 dfs 를 뻗어 나간다.
 * 4. dfs 를 지날 때마다 가장 최대로 진출한 값으로 갱신한다.
 * 5. 알파벳 - 'A' 를 통해 visit 의 인덱스에 접근한다.
 */
public class M1987 {

    static int r, c;
    static char[][] graph;
    static boolean[] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        r = scan.nextInt();
        c = scan.nextInt();
        graph = new char[r][c];
        // 2. 알파벳 숫자를 통해 visit 배열을 사용한다.
        visit = new boolean[26];
        answer = 1;
        scan.nextLine();

        for (int row = 0; row < r; row++) {
            String input = scan.nextLine();
            for (int col = 0; col < c; col++) {
                graph[row][col] = input.charAt(col);
            }
        }

        // 3. 델타 배열을 사용, 사방으로 dfs 를 뻗어 나간다.
        // 5. 알파벳 - 'A' 를 통해 visit 의 인덱스에 접근한다.
        int visitIdx = graph[0][0] - 'A';
        visit[visitIdx] = true;
        for (int idx = 0; idx < 4; idx++) {
            int nx = dx[idx];
            int ny = dy[idx];

            if (0 <= nx && nx < r && 0 <= ny && ny < c) {
                dfs(nx, ny, 1);
            }
        }
        System.out.println(answer);
    }

    private static void dfs(int x, int y, int count) {
        // 4. dfs 를 지날 때마다 가장 최대로 진출한 값으로 갱신한다.
        answer = Math.max(answer, count);

        // 5. 알파벳 - 'A' 를 통해 visit 의 인덱스에 접근한다.
        int visitIdx = graph[x][y] - 'A';
        for (int idx = 0; idx < 4; idx++) {
            int nx = x + dx[idx];
            int ny = y + dy[idx];

            if (0 <= nx && nx < r && 0 <= ny && ny < c) {
                if (!visit[visitIdx]) {
                    visit[visitIdx] = true;
                    dfs(nx, ny, count + 1);
                    visit[visitIdx] = false;
                }
            }
        }
    }
}
