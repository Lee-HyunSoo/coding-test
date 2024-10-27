package baekjoon;

import java.util.*;

/**
 * 체스판 다시 칠하기
 *
 * 1. 문제 정리
 * 	1-1. 체스판은 검은색과 흰색이 번갈아서 칠해져 있어야 한다.
 *  1-2. 구체적으로, 각 칸이 검은색과 흰색 중 하나로 색칠되어 있고,
 *  1-3. 변을 공유하는 두 개의 사각형은 다른 색으로 칠해져 있어야 한다.
 *  1-4. 이 정의를 따르면 체스판을 색칠하는 경우는 두 가지뿐이다.
 *  	1-4-1. 하나는 맨 왼쪽 위 칸이 흰색인 경우, 하나는 검은색인 경우이다.
 *
 * 2. 주어진 그래프에서 8*8 체스판을 세팅한다.
 * 3. 8*8 체스판의 처음이 WHITE 일 때, 처음이 BLACK 일 때 두가지로 나눠서 탐색
 * 4. 이를 위해 8*8 체스판을 2개 복사한다.
 * 5. 복사 후 BFS 를 총 2번 진행한다.
 */
public class M1018 {

    static class Pair {
        int x, y, color;
        Pair (int x, int y, int color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    static Scanner scan = new Scanner(System.in);
    static int n, m;
    static int[][] graph; // 0: white 1: black
    static int[][] chess;
    static int answer;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static final int WHITE = 0;
    static final int BLACK = 1;
    static final int SIZE = 8;

    public static void main(String[] args) {
        init();

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (row + SIZE <= n && col + SIZE <= m) {
                    // 2. 주어진 그래프에서 8*8 체스판을 세팅한다.
                    setChess(row, col);
                    // 3. 8*8 체스판의 처음이 WHITE 일 때, 처음이 BLACK 일 때 두가지로 나눠서 탐색
                    findAnswer();
                }
            }
        }
        System.out.println(answer);
    }

    private static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        scan.nextLine();
        graph = new int[n][m];

        for (int row = 0; row < n; row++) {
            String input = scan.nextLine();
            for (int col = 0; col < m; col++) {
                if (input.charAt(col) == 'B') {
                    graph[row][col] = BLACK;
                } else {
                    graph[row][col] = WHITE;
                }
            }
        }
        answer = Integer.MAX_VALUE;
    }

    private static void setChess(int row, int col) {
        chess = new int[SIZE][SIZE];
        int crow = 0;
        for (int r = row; r < row + SIZE; r++) {
            int ccol = 0;
            for (int c = col; c < col + SIZE; c++) {
                chess[crow][ccol] = graph[r][c];
                ccol++;
            }
            crow++;
        }
    }

    private static int[][] copy() {
        int[][] copy = new int[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                copy[row][col] = chess[row][col];
            }
        }
        return copy;
    }

    private static void findAnswer() {
        // 4. 이를 위해 8*8 체스판을 2개 복사한다.
        int[][] copy1 = copy();
        int[][] copy2 = copy();
        // 5. 복사 후 BFS 를 총 2번 진행한다.
        if (chess[0][0] == WHITE) {
            answer = Math.min(answer, findError(copy1));
            copy2[0][0] = BLACK;
            answer = Math.min(answer, findError(copy2) + 1);
        } else {
            answer = Math.min(answer, findError(copy1));
            copy2[0][0] = WHITE;
            answer = Math.min(answer, findError(copy2) + 1);
        }
    }

    private static int findError(int[][] chess) {
        Queue<Pair> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[SIZE][SIZE];
        q.offer(new Pair(0, 0, chess[0][0]));
        visit[0][0] = true;

        int result = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0) {
                Pair p = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];

                    if (nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE) {
                        continue;
                    }
                    if (visit[nx][ny]) {
                        continue;
                    }
                    if (p.color == WHITE && chess[nx][ny] == WHITE) {
                        chess[nx][ny] = BLACK;
                        result++;
                    }
                    if (p.color == BLACK && chess[nx][ny] == BLACK) {
                        chess[nx][ny] = WHITE;
                        result++;
                    }
                    visit[nx][ny] = true;
                    q.offer(new Pair(nx, ny, chess[nx][ny]));
                }
            }
        }
        return result;
    }
}
