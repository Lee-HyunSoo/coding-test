package baekjoon;

import java.util.Scanner;

/**
 * 빵집
 *
 * 1. 문제 정리
 *  1-1. '.', 'x' 로 구분되어 있는 그래프가 주어진다.
 *  1-2. 맨왼쪽줄 ~ 맨 오른쪽줄을 서로 파이프로 연결한다. 이때 x 는 연결할 수 없는 지점이다.
 *  1-3. 파이프는 오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선으로 연결 가능하다.
 *
 * 2. 위에서 아래로 내려가며 연결하기 때문에 파이프 위치 탐색 시 최대한 위쪽 먼저 탐색한다.
 *  2-1. 즉, 델타 배열이 우상단, 우, 우하단 순으로 정해져야 가능성이 더 높아진다.
 *  2-2. 델타배열로 다음 좌표를 구한다.
 *  2-3. 다음 좌표가 그래프 밖의 점이면, continue
 *  2-4. 다음 좌표가 x 라면 continue
 *  2-5. 아니라면 탐색 진행, 해당 칸을 x 로 만든다.
 *  2-6. 이 때, 끝까지 도달했으면 일단 처음으로 되돌아와야 하기 때문에 flag 를 사용한다.
 *
 * 3. 종료 조건
 *  3-1. 끝까지 도달했다면 answer + 1, flag 를 true, return
 */
public class M3109 {

    static int r, c, answer;
    static char[][] graph;

    // 2. 위에서 아래로 내려가며 연결하기 때문에 파이프 위치 탐색 시 최대한 위쪽 먼저 탐색한다.
    // 2-1. 즉, 델타 배열이 우상단, 우, 우하단 순으로 정해져야 가능성이 더 높아진다.
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};
    static boolean flag;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        r = scan.nextInt();
        c = scan.nextInt();
        scan.nextLine();

        graph = new char[r][c];
        answer = 0;
        for (int row = 0; row < r; row++) {
            String input = scan.nextLine();
            for (int col = 0; col < c; col++) {
                graph[row][col] = input.charAt(col);
            }
        }

        for (int row = 0; row < r; row++) {
            flag = false;
            graph[row][0] = 'x';
            setPipe(row, 0);
        }
        System.out.println(answer);
    }

    private static void setPipe(int row, int col) {
        // 3-1. 끝까지 도달했다면 answer + 1, flag 를 true, return
        if (col == c - 1) {
            answer++;
            flag = true;
            return;
        }

        for (int idx = 0; idx < 3; idx++) {
            // 2-2. 델타배열로 다음 좌표를 구한다.
            int nx = row + dx[idx];
            int ny = col + dy[idx];

            // 2-3. 다음 좌표가 그래프 밖의 점이면, continue
            if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                continue;
            }

            // 2-4. 다음 좌표가 x 라면 continue
            if (graph[nx][ny] == 'x') {
                continue;
            }

            // 2-5. 아니라면 탐색 진행, 해당 칸을 x 로 만든다.
            // 2-6. 이 때, 끝까지 도달했으면 일단 처음으로 되돌아와야 하기 때문에 flag 를 사용한다.
            if (!flag) {
                setPipe(nx, ny);
                graph[nx][ny] = 'x';
            }
        }
    }
}
