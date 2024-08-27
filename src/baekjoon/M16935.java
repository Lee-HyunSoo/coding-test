package baekjoon;

import java.util.*;

/**
 * 배열 돌리기 3
 *
 * 1. 문제 정리
 * 	1-1. 상하 반전, 좌우 반전, 90도 회전, 사각형범위 회전 구현
 * 	1-2. 주어지는 n, m 은 모두 짝수이다.
 *
 * 2. 상하 반전
 * 	2-1. 0 <-> n-1, 1 <-> n-2 ...
 *
 * 3. 좌우 반전
 * 	3-1. 0 <-> m-1, 1 <-> m-2 ...
 *
 * 4. 오른쪽 90도 회전
 * 	4-1. 기존 배열을 위에서 아래로 세로로 읽어서 새 배열에 덮어씌운다.
 *
 * 5. 왼쪽 90도 회전
 * 	5-1. 기존 배열을 맨 오른쪽 col 부터 아래서 위로 읽어서 새 배열에 덮어씌운다.
 *
 * 6. 오른쪽 사각형범위 회전
 * 	6-1. row (0 ~ n/2) 고정, col (0 ~ m/2 ~ m) 변화
 * 	6-2. row (0 ~ n/2 ~ n) 변화, col (m/2 ~ m) 고정
 * 	6-3. row (n/2 ~ n) 고정, col (0 ~ m/2 ~ m) 변화
 * 	6-4. row (0 ~ n/2 ~ n) 변화, col (0 ~ m/2) 고정
 *  6-5. 해당 범위만 읽어 새 배열에 덮어 씌운다.
 *
 * 7. 왼쪽 사각형범위 회전
 * 	7-1. row (0 ~ n/2 ~ n) 변화, col (0 ~ m/2) 고정
 * 	7-2. row (n/2 ~ n) 고정, col (0 ~ m/2 ~ m) 변화
 * 	7-3. row (0 ~ n/2 ~ n) 변화, col (m/2 ~ m) 고정
 * 	7-4. row (0 ~ n/2) 고정, col (0 ~ m/2 ~ m) 변화
 */
public class M16935 {

    static int n, m, r;
    static int[][] graph;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        r = scan.nextInt();
        graph = new int[n][m];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                graph[row][col] = scan.nextInt();
            }
        }
        for (int idx = 0; idx < r; idx++) {
            int command = scan.nextInt();
            switch(command) {
                case 1:
                    upDown();
                    break;
                case 2:
                    leftRight();
                    break;
                case 3:
                    rightRotate();
                    break;
                case 4:
                    leftRotate();
                    break;
                case 5:
                    rightSquare();
                    break;
                case 6:
                    leftSquare();
                    break;
            }
        }

        for (int row = 0; row < graph.length; row++) {
            for (int col = 0; col < graph[0].length; col++) {
                System.out.print(graph[row][col] + " ");
            }
            System.out.println();
        }
    }

    // 2. 상하 반전
    private static void upDown() {
        int n = graph.length;
        int m = graph[0].length;
        int[][] arr = new int[n][m];
        // 2-1. 0 <-> n-1, 1 <-> n-2 ...
        for (int prev = n - 1, next = 0; prev >= n / 2; prev--, next++) {
            for (int col = 0; col < m; col++) {
                int num = graph[next][col];
                graph[next][col] = graph[prev][col];
                graph[prev][col] = num;
            }
        }
    }

    // 3. 좌우 반전
    private static void leftRight() {
        int n = graph.length;
        int m = graph[0].length;
        int[][] arr = new int[n][m];
        // 3-1. 0 <-> m-1, 1 <-> m-2 ...
        for (int row = 0; row < n; row++) {
            for (int prev = m - 1, next = 0; prev >= m / 2; prev--, next++) {
                int num = graph[row][next];
                graph[row][next] = graph[row][prev];
                graph[row][prev] = num;
            }
        }
    }

    // 4. 오른쪽 90도 회전
    private static void rightRotate() {
        int n = graph.length;
        int m = graph[0].length;
        int[][] arr = new int[m][n];
        // 4-1. 기존 배열을 위에서 아래로 세로로 읽어서 새 배열에 덮어씌운다.
        for (int col = 0; col < m; col++) {
            for (int prev = n - 1, next = 0; prev >= 0; prev--, next++) {
                arr[col][next] = graph[prev][col];
            }
        }
        graph = arr;
    }

    // 5. 왼쪽 90도 회전
    private static void leftRotate() {
        int n = graph.length;
        int m = graph[0].length;
        int[][] arr = new int[m][n];
        // 5-1. 기존 배열을 맨 오른쪽 col 부터 아래서 위로 읽어서 새 배열에 덮어씌운다.
        for (int prev = m - 1, next = 0; prev >= 0; prev--, next++) {
            for (int row = 0; row < n; row++) {
                arr[next][row] = graph[row][prev];
            }
        }
        graph = arr;
    }

    // 6. 오른쪽 사각형범위 회전
    private static void rightSquare() {
        int n = graph.length;
        int m = graph[0].length;
        int[][] arr = new int[n][m];
        // 6-1. row (0 ~ n/2) 고정, col (0 ~ m/2 ~ m) 변화
        for (int row = 0; row < n / 2; row++) {
            for (int prev = 0, next = m / 2; prev < m / 2; prev++, next++) {
                arr[row][next] = graph[row][prev];
            }
        }
        // 6-2. row (0 ~ n/2 ~ n) 변화, col (m/2 ~ m) 고정
        for (int prev = 0, next = n / 2; prev < n / 2; prev++, next++) {
            for (int col = m / 2; col < m; col++) {
                arr[next][col] = graph[prev][col];
            }
        }

        // 6-3. row (n/2 ~ n) 고정, col (0 ~ m/2 ~ m) 변화
        for (int row = n / 2; row < n; row++) {
            for (int prev = m / 2, next = 0; prev < m; prev++, next++) {
                arr[row][next] = graph[row][prev];
            }
        }

        // 6-4. row (0 ~ n/2 ~ n) 변화, col (0 ~ m/2) 고정
        for (int prev = n / 2, next = 0; prev < n; prev++, next++) {
            for (int col = 0; col < m / 2; col++) {
                arr[next][col] = graph[prev][col];
            }
        }
        graph = arr;
    }

    // 7. 왼쪽 사각형범위 회전
    private static void leftSquare() {
        int n = graph.length;
        int m = graph[0].length;
        int[][] arr = new int[n][m];
        // 7-1. row (0 ~ n/2 ~ n) 변화, col (0 ~ m/2) 고정
        for (int prev = 0, next = n / 2; prev < n / 2; prev++, next++) {
            for (int col = 0; col < m / 2; col++) {
                arr[next][col] = graph[prev][col];
            }
        }

        // 7-2. row (n/2 ~ n) 고정, col (0 ~ m/2 ~ m) 변화
        for (int row = n / 2; row < n; row++) {
            for (int prev = 0, next = m / 2; prev < m / 2; prev++, next++) {
                arr[row][next] = graph[row][prev];
            }
        }

        // 7-3. row (0 ~ n/2 ~ n) 변화, col (m/2 ~ m) 고정
        for (int prev = n / 2, next = 0; prev < n; prev++, next++) {
            for (int col = m / 2; col < m; col++) {
                arr[next][col] = graph[prev][col];
            }
        }

        // 7-4. row (0 ~ n/2) 고정, col (0 ~ m/2 ~ m) 변화
        for (int row = 0; row < n / 2; row++) {
            for (int prev = m / 2, next = 0; prev < m; prev++, next++) {
                arr[row][next] = graph[row][prev];
            }
        }
        graph = arr;
    }
}
