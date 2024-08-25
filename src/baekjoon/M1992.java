package baekjoon;

import java.util.*;

/**
 * 쿼드트리
 *
 * 1. 문제 정리
 * 	1-1. 한 사분면이 모두 0이면 0, 1이면 1
 * 	1-2. 0, 1 둘다 있으면 분할한다.
 *
 * 2. 분할정복 사용
 * 	2-1. 현재 사분면을 탐색, 내부의 값을 모두 더한다.
 * 		2-1-1. 더한 값이 0이면 모두 0, size*size 면 모두 1이라는 의미가 된다.
 * 	2-2. 더한 값이 0이면 0 추가
 * 	2-3. 더한 값이 1이면 1 추가
 * 	2-4. 아니라면 분할, 분할 시작할 때 '(' 를, 분할이 종료되는 시점에 ')' 를 추가한다.
 */
public class M1992 {

    static int[][] graph;
    static StringBuilder answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();
        graph = new int[n][n];
        answer = new StringBuilder();

        for (int row = 0; row < n; row++) {
            String input = scan.nextLine();
            for (int col = 0; col < n; col++) {
                graph[row][col] = input.charAt(col) - '0';
            }
        }
        divConquer(0, 0, n);
        System.out.println(answer);
    }

    private static void divConquer(int row, int col, int size) {
        // 2-1. 현재 사분면을 탐색, 내부의 값을 모두 더한다.
        int sum = 0;
        for (int r = row; r < row + size; r++) {
            for (int c = col; c < col + size; c++) {
                // 2-1-1. 더한 값이 0이면 모두 0, size*size 면 모두 1이라는 의미가 된다.
                sum += graph[r][c];
            }
        }

        if (sum == 0) {
            // 2-2. 더한 값이 0이면 0 추가
            answer.append("0");
        } else if (sum == size * size) {
            // 2-3. 더한 값이 1이면 1 추가
            answer.append("1");
        } else {
            // 2-4. 아니라면 분할, 분할 시작할 때 '(' 를, 분할이 종료되는 시점에 ')' 를 추가한다.
            answer.append("(");
            int half = size / 2;
            divConquer(row, col, half);
            divConquer(row, col + half, half);
            divConquer(row + half, col, half);
            divConquer(row + half, col + half, half);
            answer.append(")");
        }
    }
}
