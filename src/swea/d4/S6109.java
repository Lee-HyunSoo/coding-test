package swea.d4;

import java.util.*;

/**
 * 추억의 2048게임
 *
 * 1. 문제 정리
 * 	1-1. 상하좌우 방향을 정하면, 모든 타일이 해당 방향으로 밀린다.
 * 	1-2. 이 때 밀리는 방향에 다른 타일이 있고, 두 타일에 적힌 숫자가 같다면 두 타일은 합쳐진다.
 * 	1-3. 이렇게 합쳐져 만들어진 새로운 타일은 숫자가 같은 타일이 밀려와도 합쳐지면 안된다.
 * 	1-4. 만약 같은 숫자가 적힌 타일이 세 개 이상있을 때는 빨리 벽에 닿게 될 타일을 먼저 민다.
 * 	1-5. 2 2 4 2 2 2 를 왼쪽으로 밀면 4 4 4 2 0 0  이 된다.
 *
 * 2. 방향이 주어지면, 그 방향의 끝 + 1에서 시작한다.
 * 	2-1. 우선 중간에 0 을 없애기 위해 먼저 다 땡겨온다.
 * 	2-2. up: 1부터, down: size()-2 부터, left: 1 부터, right: size()-2 부터
 * 	2-3. 이전 칸이 자신과 동일하면 이전칸에 더하고, 자신은 0으로 변경
 * 	2-4. 최종적으로 만들어진 배열에서 0과 숫자를 세서 새 배열을 만든다. (다 땡겨온다.)
 * 		2-4-1. left 기준 왼쪽부터 배열에 채워넣고, 마지막에 0 의 개수만큼 0을 넣는다.
 */
public class S6109 {

    static int n;
    static String s;
    static int[][] graph;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            scan.nextLine();
            String[] input = scan.nextLine().split(" ");
            n = Integer.parseInt(input[0]);
            s = input[1];
            graph = new int[n][n];
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    graph[row][col] = scan.nextInt();
                }
            }

            if (s.equals("up")) {
                up();
            } else if (s.equals("down")) {
                down();
            } else if (s.equals("left")) {
                left();
            } else if (s.equals("right")) {
                right();
            }

            System.out.println("#" + tc);
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    System.out.print(graph[row][col] + " ");
                }
                System.out.println();
            }
        }
    }

    private static void up() {
        for (int col = 0; col < n; col++) {
            // 2-1. 우선 중간에 0 을 없애기 위해 먼저 다 앞으로 땡겨온다.
            upChange(col);
            // 2. 방향이 주어지면, 그 방향의 끝 + 1에서 시작한다.
            // 2-2. up: 1부터, down: size()-2 부터, left: 1 부터, right: size()-2 부터
            for (int row = 1; row < n; row++) {
                // 2-3. 이전 칸이 자신과 동일하면 이전칸에 더하고, 자신은 0으로 변경
                if (graph[row - 1][col] == graph[row][col]) {
                    graph[row - 1][col] += graph[row][col];
                    graph[row][col] = 0;
                }
            }
            // 2-4. 최종적으로 만들어진 배열에서 0과 숫자를 세서 새 배열을 만든다. (다 땡겨온다.)
            upChange(col);
        }
    }

    private static void upChange(int col) {
        int[] change = new int[n];
        int idx = 0;
        for (int row = 0; row < n; row++) {
            if (graph[row][col] != 0) {
                change[idx++] = graph[row][col];
            }
        }
        // 2-4-1. left 기준 왼쪽부터 배열에 채워넣고, 마지막에 0 의 개수만큼 0을 넣는다.
        for (; idx < n; idx++) {
            change[idx] = 0;
        }
        for (int row = 0; row < n; row++) {
            graph[row][col] = change[row];
        }
    }

    private static void down() {
        for (int col = 0; col < n; col++) {
            // 2-1. 우선 중간에 0 을 없애기 위해 먼저 다 앞으로 땡겨온다.
            downChange(col);
            // 2. 방향이 주어지면, 그 방향의 끝 + 1에서 시작한다.
            // 2-2. up: 1부터, down: size()-2 부터, left: 1 부터, right: size()-2 부터
            for (int row = n - 2; row >= 0; row--) {
                // 2-3. 이전 칸이 자신과 동일하면 이전칸에 더하고, 자신은 0으로 변경
                if (graph[row + 1][col] == graph[row][col]) {
                    graph[row + 1][col] += graph[row][col];
                    graph[row][col] = 0;
                }
            }
            // 2-4. 최종적으로 만들어진 배열에서 0과 숫자를 세서 새 배열을 만든다. (다 땡겨온다.)
            downChange(col);
        }
    }

    private static void downChange(int col) {
        int[] change = new int[n];
        int idx = 0;
        for (int row = n - 1; row >= 0; row--) {
            if (graph[row][col] != 0) {
                change[idx++] = graph[row][col];
            }
        }
        // 2-4-1. left 기준 왼쪽부터 배열에 채워넣고, 마지막에 0 의 개수만큼 0을 넣는다.
        for (; idx < n; idx++) {
            change[idx] = 0;
        }
        for (int row = n - 1; row >= 0; row--) {
            graph[row][col] = change[n - 1 - row];
        }
    }

    private static void left() {
        for (int row = 0; row < n; row++) {
            // 2-1. 우선 중간에 0 을 없애기 위해 먼저 다 앞으로 땡겨온다.
            leftChange(row);
            // 2. 방향이 주어지면, 그 방향의 끝 + 1에서 시작한다.
            // 2-2. up: 1부터, down: size()-2 부터, left: 1 부터, right: size()-2 부터
            for (int col = 1; col < n; col++) {
                // 2-3. 이전 칸이 자신과 동일하면 이전칸에 더하고, 자신은 0으로 변경
                if (graph[row][col - 1] == graph[row][col]) {
                    graph[row][col - 1] += graph[row][col];
                    graph[row][col] = 0;
                }
            }
            // 2-4. 최종적으로 만들어진 배열에서 0과 숫자를 세서 새 배열을 만든다. (다 땡겨온다.)
            leftChange(row);
        }
    }

    private static void leftChange(int row) {
        int[] change = new int[n];
        int idx = 0;
        for (int col = 0; col < n; col++) {
            if (graph[row][col] != 0) {
                change[idx++] = graph[row][col];
            }
        }
        // 2-4-1. left 기준 왼쪽부터 배열에 채워넣고, 마지막에 0 의 개수만큼 0을 넣는다.
        for (; idx < n; idx++) {
            change[idx] = 0;
        }
        for (int col = 0; col < n; col++) {
            graph[row][col] = change[col];
        }
    }

    private static void right() {
        for (int row = 0; row < n; row++) {
            // 2-1. 우선 중간에 0 을 없애기 위해 먼저 다 앞으로 땡겨온다.
            rightChange(row);
            // 2. 방향이 주어지면, 그 방향의 끝 + 1에서 시작한다.
            // 2-2. up: 1부터, down: size()-2 부터, left: 1 부터, right: size()-2 부터
            for (int col = n - 2; col >= 0; col--) {
                // 2-3. 이전 칸이 자신과 동일하면 이전칸에 더하고, 자신은 0으로 변경
                if (graph[row][col + 1] == graph[row][col]) {
                    graph[row][col + 1] += graph[row][col];
                    graph[row][col] = 0;
                }
            }
            // 2-4. 최종적으로 만들어진 배열에서 0과 숫자를 세서 새 배열을 만든다. (다 땡겨온다.)
            rightChange(row);
        }
    }

    private static void rightChange(int row) {
        int[] change = new int[n];
        int idx = 0;
        for (int col = n - 1; col >= 0; col--) {
            if (graph[row][col] != 0) {
                change[idx++] = graph[row][col];
            }
        }
        // 2-4-1. left 기준 왼쪽부터 배열에 채워넣고, 마지막에 0 의 개수만큼 0을 넣는다.
        for (; idx < n; idx++) {
            change[idx] = 0;
        }
        for (int col = n - 1; col >= 0; col--) {
            graph[row][col] = change[n - 1 - col];
        }
    }
}