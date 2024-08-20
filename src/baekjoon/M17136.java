package baekjoon;

import java.util.*;

/**
 * 색종이 붙이기
 *
 * 1. 개수를 저장할 색종이 배열 [5]
 * 1-1. 각 색종이의 개수는 5개기 때문에 다 5로 초기화
 * 1-2. 0이 적힌 칸에는 색종이를 붙일 수 없다.
 *
 * 2. 현재 위치의 값이 1이라면
 *  2-1. 색종이가 남아있고 5*5, 4*4, 3*3, 2*2, 1*1 중 하나가 모두 1이라면
 *  2-2. 색종이를 붙이고, 남은 개수를 줄인다.
 *  2-3. 색종이를 붙였기 때문에 count + 1 -> 재귀
 *  2-4. 재귀에서 돌아오면 기존에 붙였던 색종이를 떼고, 남은 개수를 늘린다.
 *
 * 3. 현재 위치의 값이 1이 아니라면
 *  3-1. y 좌표를 1 늘리고 재귀
 *
 * 4. 종료 조건
 *  4-1. 마지막 좌표에 도달했다면 -> row >= 9, col == 10 (기본적으로 col 을 늘리는 방향으로 재귀를 돌기때문에)
 *  4-2. 여태 센 count 가 이전에 구한 answer 보다 크거나 같다면 (최소값을 구해야하기 때문에 더 탐색 필요 x)
 *  4-3. col == 10 이라면 다음 row 로 넘어가야함
 *
 * 5. find 메서드
 *  5-1. 좌표가 보드 안에 있지만 값이 0 이면 색종이를 붙일 수 없다.
 *  5-2. 좌표가 보드 안에 없다면 색종이를 붙일 수 없다.
 *
 * 6. check 메서드
 *  6-1. 위에서 find 메서드로 검증을 했기 때문에, 파라미터로 넘어온 값을 통해 색종이를 붙이거나 뗀다.
 */
public class M17136 {

    static int[][] board;
    static int[] paper;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        board = new int[10][10];
        paper = new int[5];
        answer = Integer.MAX_VALUE;
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                board[row][col] = scan.nextInt();
            }
        }
        // 1-1. 각 색종이의 개수는 5개기 때문에 다 5로 초기화
        Arrays.fill(paper, 5);

        combination(0, 0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void combination(int row, int col, int count) {
        // 4-1. 마지막 좌표에 도달했다면 -> row >= 9, col == 10 (기본적으로 col 을 늘리는 방향으로 재귀를 돌기때문에)
        if (row >= 9 && col == 10) {
            answer = Math.min(answer, count);
            return;
        }

        // 4-2. 여태 센 count 가 이전에 구한 answer 보다 크거나 같다면 (최소값을 구해야하기 때문에 더 탐색 필요 x)
        if (answer <= count) {
            return;
        }

        // 4-3. col == 10 이라면 다음 row 로 넘어가야함
        if (col == 10) {
            combination(row + 1, 0, count);
            return;
        }

        // 2. 현재 위치의 값이 1이라면
        if (board[row][col] == 1) {
            for (int size = 4; size >= 0; size--) {
                // 2-1. 색종이가 남아있고 5*5, 4*4, 3*3, 2*2, 1*1 중 하나가 모두 1이라면
                if (paper[size] > 0 && find(row, col, size)) {
                    // 2-2. 색종이를 붙이고, 남은 개수를 줄인다.
                    check(row, col, size, 0);
                    paper[size]--;
                    // 2-3. 색종이를 붙였기 때문에 count + 1 -> 재귀
                    combination(row, col + 1, count + 1);
                    // 2-4. 재귀에서 돌아오면 기존에 붙였던 색종이를 떼고, 남은 개수를 늘린다.
                    check(row, col, size, 1);
                    paper[size]++;
                }
            }
        } else {
            combination(row, col + 1, count);
        }
    }

    private static boolean find(int r, int c, int size) {
        for (int row = r; row <= r + size; row++) {
            for (int col = c; col <= c + size; col++) {
                // 5-1. 좌표가 보드 안에 있지만 값이 0 이면 색종이를 붙일 수 없다.
                if (0 <= row && row < 10 && 0 <= col && col < 10) {
                    if (board[row][col] == 0) {
                        return false;
                    }
                } else {
                    // 5-2. 좌표가 보드 안에 없다면 색종이를 붙일 수 없다.
                    return false;
                }
            }
        }
        return true;
    }

    private static void check(int r, int c, int size, int result) {
        for (int row = r; row <= r + size; row++) {
            for (int col = c; col <= c + size; col++) {
                // 6-1. 위에서 find 메서드로 검증을 했기 때문에, 파라미터로 넘어온 값을 통해 색종이를 붙이거나 뗀다.
                board[row][col] = result;
            }
        }
    }
}
