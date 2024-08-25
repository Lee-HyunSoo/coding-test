package swea.swcompetency;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 무선 충전
 *
 * 1. 문제 정리
 * 	1-1. 지도의 가로, 세로는 10으로 고정이다.
 * 	1-2. 사용자 A 는 (1,1), 사용자 B 는 (10,10) 에서 출발한다.
 * 	1-3. 사용자는 초기 위치(0초) 부터 충전이 가능하다.
 * 	1-4. 같은 위치에 2개 이상의 BC 가 설치 된 경우는 없다.
 * 	1-5. 사용자 A, B 는 서로 같은 위치로 이동할 수 있고, 지도 밖으로 이동하는 경우는 없다.
 * 	1-6. 사용자의 이동정보 [0, 1, 2, 3, 4] == [정지, 상, 우, 하, 좌]
 * 	1-7. BC 는 겹치는 부분이 있을 수 있다. 이 경우 사용자는 둘중 하나를 선택해 접속 가능하다.
 * 	1-8. 만약 두 사용자가 같은 BC 를 사용하면, 충전량을 반반씩 나눠갖는다.
 *
 * 2. 충전기의 정보를 저장하는 bcs 배열을 만든다.
 * 3. A 의 좌표, B 의 좌표를 초기화 한다.
 * 4. 초기 위치가 충전 가능지역이면 충전을 해야하기 때문에, 먼저 체크해본다.
 * 5. 시간에 따라 좌표를 이동하며 충전한다.
 * 	5-1. 해당 좌표에 대해 모든 충전기에 대한 경우의 수를 찾아본다.
 * 	5-2. 사용할 수 있는 충전기라면 해당 충전기의 power 를, 충전지역이 아니면 0 을 반환
 * 	5-3. 서로 다른 충전기인 경우, 두 충전기를 더한 값으로 max 를 갱신한다.
 * 	5-4. 서로 같은 충전기인 경우, 둘 중 power가 큰쪽 하나를 통해 max 를 갱신한다.
 * 		5-4-1. 같은 충전기를 사용하더라도 한쪽은 충전지역이 아닐 수 있기 때문에 비교가 꼭 필요
 * 	5-5. 가장 최대값을 answer 에 더해준다.
 */
public class S5644 {

    static class BC {
        int x, y, size, power;

        public BC(int x, int y, int size, int power) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.power = power;
        }
    }

    static int m, a;
    static int[] A, B;
    static List<BC> bcs;
    static int aRow, aCol, bRow, bCol;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            m = scan.nextInt(); // 총 이동시간
            a = scan.nextInt(); // bc 의 개수

            A = new int[m]; // A 의 이동정보
            B = new int[m]; // B 의 이동정보
            for (int idx = 0; idx < m; idx++) {
                A[idx] = scan.nextInt();
            }
            for (int idx = 0; idx < m; idx++) {
                B[idx] = scan.nextInt();
            }

            // 2. 충전기의 정보를 저장하는 bcs 배열을 만든다.
            bcs = new ArrayList<>();
            for (int idx = 0; idx < a; idx++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                int size = scan.nextInt();
                int power = scan.nextInt();
                bcs.add(new BC(y - 1, x - 1, size, power));
            }

            // 3. A 의 좌표, B 의 좌표를 초기화 한다.
            aRow = 0; aCol = 0; bRow = 9; bCol = 9;
            answer = 0;
            move();
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void move() {
        // 4. 초기 위치가 충전 가능지역이면 충전을 해야하기 때문에, 먼저 체크해본다.
        charge();
        // 5. 시간에 따라 좌표를 이동하며 충전한다.
        for (int time = 0; time < m; time++) {
            if (A[time] == 1) {
                aRow -= 1;
            } else if (A[time] == 2) {
                aCol += 1;
            } else if (A[time] == 3) {
                aRow += 1;
            } else if (A[time] == 4) {
                aCol -= 1;
            }

            if (B[time] == 1) {
                bRow -= 1;
            } else if (B[time] == 2) {
                bCol += 1;
            } else if (B[time] == 3) {
                bRow += 1;
            } else if (B[time] == 4) {
                bCol -= 1;
            }
            charge();
        }
    }

    private static void charge() {
        int max = 0;
        // 5-1. 해당 좌표에 대해 모든 충전기에 대한 경우의 수를 찾아본다.
        for (int a = 0; a < bcs.size(); a++) {
            for (int b = 0; b < bcs.size(); b++) {
                // 5-2. 사용할 수 있는 충전기라면 해당 충전기의 power 를, 충전지역이 아니면 0 을 반환
                int powerA = isCharge(a, aRow, aCol) ? bcs.get(a).power : 0;
                int powerB = isCharge(b, bRow, bCol) ? bcs.get(b).power : 0;

                if (a != b) {
                    // 5-3. 서로 다른 충전기인 경우, 두 충전기를 더한 값으로 max 를 갱신한다.
                    max = Math.max(max, powerA + powerB);
                } else {
                    // 5-4. 서로 같은 충전기인 경우, 둘 중 power가 큰쪽 하나를 통해 max 를 갱신한다.
                    // 5-4-1. 같은 충전기를 사용하더라도 한쪽은 충전지역이 아닐 수 있기 때문에 비교가 꼭 필요
                    max = Math.max(max, Math.max(powerA, powerB));
                }
            }
        }
        // 5-5. 가장 최대값을 answer 에 더해준다.
        answer += max;
    }

    private static boolean isCharge(int idx, int row, int col) {
        int result = Math.abs(bcs.get(idx).x - row) + Math.abs(bcs.get(idx).y - col);
        return result <= bcs.get(idx).size ? true : false;
    }
}