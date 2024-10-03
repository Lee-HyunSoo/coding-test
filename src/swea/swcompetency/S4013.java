package swea.swcompetency;

import java.util.Scanner;

/**
 * 특이한 자석
 *
 * 1. 문제 정리
 * 	1-1. 모든 회전이 끝난 후 0번 index 가 0이면 0점 (N극 : 0)
 * 	1-2. 1번 index 가 1이면 1 / 2 / 4 / 8 (S극 : 1)
 *	1-3. 하나의 자석이 1칸 회전할 때, 붙어있는 날이 다를 때만 반대로 1칸 회전
 *		1-3-1. 붙어있는 날 : 이전자석의 6번 index, 현재 자석의 2번 index
 *	1-4. 시계방향 : 1, 반시계방향 : -1
 */
public class S4013 {

    static Scanner scan = new Scanner(System.in);
    static int k, magnetNum, rotate;
    static int[][] magnet;
    static final int HEAD = 0;
    static final int LEFT_POS = 6;
    static final int RIGHT_POS = 2;
    static final int MAX_IDX = 8;

    public static void main(String[] args) {
        int t = scan.nextInt();

        for (int tc = 1; tc <= t; tc++) {
            init();

            while (k-- > 0) {
                magnetNum = scan.nextInt(); // 자석 번호
                rotate = scan.nextInt();    // 회전 방향
                performRotation();
            }

            int answer = 0;
            if (magnet[1][HEAD] == 1) answer += 1;
            if (magnet[2][HEAD] == 1) answer += 2;
            if (magnet[3][HEAD] == 1) answer += 4;
            if (magnet[4][HEAD] == 1) answer += 8;

            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void performRotation() {
        // 자석별 회전 방향 기록 배열 (0: 회전 안 함, 1: 시계, -1: 반시계)
        int[] rotationDir = new int[5];
        rotationDir[magnetNum] = rotate; // 현재 자석의 회전 방향 설정

        // 왼쪽으로 연쇄적으로 자석 회전 방향 결정
        for (int idx = magnetNum; idx > 1; idx--) {
            if (magnet[idx][LEFT_POS] != magnet[idx - 1][RIGHT_POS]) {
                rotationDir[idx - 1] = -rotationDir[idx]; // 반대 방향 회전
            } else {
                break; // 자석 극이 같으면 더 이상 회전하지 않음
            }
        }

        // 오른쪽으로 연쇄적으로 자석 회전 방향 결정
        for (int idx = magnetNum; idx < 4; idx++) {
            if (magnet[idx][RIGHT_POS] != magnet[idx + 1][LEFT_POS]) {
                rotationDir[idx + 1] = -rotationDir[idx]; // 반대 방향 회전
            } else {
                break; // 자석 극이 같으면 더 이상 회전하지 않음
            }
        }

        // 모든 자석에 대해 회전 적용
        for (int i = 1; i <= 4; i++) {
            if (rotationDir[i] == 1) {
                moveRight(magnet[i]); // 시계방향 회전
            } else if (rotationDir[i] == -1) {
                moveLeft(magnet[i]); // 반시계방향 회전
            }
        }
    }

    private static void moveRight(int[] magnet) {
        int last = magnet[MAX_IDX - 1];
        for (int idx = MAX_IDX - 1; idx > 0; idx--) {
            magnet[idx] = magnet[idx - 1];
        }
        magnet[0] = last;
    }

    private static void moveLeft(int[] magnet) {
        int first = magnet[0];
        for (int idx = 0; idx < MAX_IDX - 1; idx++) {
            magnet[idx] = magnet[idx + 1];
        }
        magnet[MAX_IDX - 1] = first;
    }

    private static void init() {
        k = scan.nextInt(); // 자석을 회전시키는 횟수
        magnet = new int[5][MAX_IDX];
        for (int row = 1; row <= 4; row++) {
            for (int col = 0; col < MAX_IDX; col++) {
                magnet[row][col] = scan.nextInt();
            }
        }
    }
}

