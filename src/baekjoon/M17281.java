package baekjoon;

import java.util.*;

/**
 * 야구
 * <p>
 * 1. 문제 정리
 *  1-1. 9명이 두 팀으로 나뉘어서 공 / 수 번갈아가면서 게임
 *  1-2. 총 N 이닝 동안 게임 진행
 *  1-3. 한 이닝에 3아웃이 발생하면 이닝 종료, 공수 교대
 *  1-4. 경기 시작 전 타순을 정하고, 경기 중에 변경은 불가능
 *      1-4-1. 9번 타자가 공을 쳤는데 3아웃이 발생하지 않은 상태면 이닝은 끝나지 않고, 1번 타자가 다시 타석에 선다.
 *      1-4-2. 타순은 닝이 변경되어도 순서를 유지해야한다. (2이닝에 6번타자가 마지막이었다면, 3이닝은 7번부터)
 *  1-5. 공격은 타자쪽 팀: 타자가 1->2->3루를 거쳐 홈으로 돌아와야 1점
 *  1-6. 루에 있는 선수를 주자라고 하고, 이닝이 시작될 때 주자는 없다.
 *  1-7. 타자가 공을 쳐서 얻을 수 있는 결과 (안타, 2루타, 3루타, 홈런, 아웃)
 *      1-7-1. 안타: 타자와 모든 주자가 한 루씩 진루
 *      1-7-2. 2루타: 타자와 모든 주자가 두 루씩 진루
 *      1-7-3. 3루타: 타자와 모든 주자가 세 루씩 진루
 *      1-7-4. 홈런: 타자와 모든 주자가 홈까지 진루
 *      1-7-5. 아웃: 모든 주자는 진루하지 못하고, 공격팀에 아웃이 하나 증가
 *  1-8. 1번 선수는 4번타자 고정, 나머지 2~8번 사이에서 타순을 정해야 함
 *  1-9. 가장 많은 특점을 하는 타순을 찾고, 그때의 득점
 * <p>
 * 2. 타순을 순열을 통해 모든 경우의 수를 구한다.
 * 3. 해당 타순을 통해 게임을 시작한다.
 * 	3-1. 한 이닝이 끝나도 타순은 유지되게 하기 위한 변수를 선언한다.
 * 	3-2. 한 이닝 당 기록할 out count 를 선언한다.
 * 	3-3. 한 이닝 당 몇루에 사람이 있는지 체크할 배열을 선언한다.
 * 	3-4. 이닝을 진행, 타자가 쳤을 때 0 이면 out count 를 증가시킨다.
 * 	3-5. 0이 아니면 1~3루 배열을 뒤에서부터 탐색한다.
 *      3-5-1. 배열 값이 1일 때 idx + 타자점수 >= 4 이면 전체점수++
 *      3-5-2. 4 미만이면 arr[idx + 타자점수] 를 true로 바꾼다.
 *      3-5-3. arr[idx] 를 false 로 바꾼다. (이미 사람이 이동했기 때문)
 *      3-5-4. 홈런을 쳤다면, 친 사람도 점수에 추가해준다.
 *      3-5-5. 홈런이 아니라면, 친 사람을 해당 루로 이동시킨다.
 *  3-6. 이 과정을 3 아웃이 나올 때까지 반복한다.
 */
public class M17281 {

    static int n;
    static int[][] graph;
    static int[] order;
    static boolean[] visit;
    static boolean[] ru;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        graph = new int[n][9];
        order = new int[9];
        visit = new boolean[9];
        answer = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < 9; col++) {
                graph[row][col] = scan.nextInt();
            }
        }
        order[3] = 0;
        visit[3] = true;
        // 2. 타순을 순열을 통해 모든 경우의 수를 구한다.
        permutation(1);
        System.out.println(answer);
    }

    private static void permutation(int level) {
        if (level == 9) {
            // 3. 해당 타순을 통해 게임을 시작한다.
            answer = Math.max(answer, baseball());
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (!visit[i]) {
                visit[i] = true;
                order[i] = level;
                permutation(level + 1);
                visit[i] = false;
            }
        }
    }

    private static int baseball() {
        int result = 0;
        // 3-1. 한 이닝이 끝나도 타순은 유지되게 하기 위한 변수를 선언한다.
        int bat = 0;
        for (int row = 0; row < n; row++) {
            // 3-2. 한 이닝 당 기록할 out count 를 선언한다.
            int out = 0;
            // 3-3. 한 이닝 당 몇루에 사람이 있는지 체크할 배열을 선언한다.
            ru = new boolean[4];
            // 3-6. 이 과정을 3 아웃이 나올 때까지 반복한다.
            while (out < 3) {
                int score = graph[row][order[bat++]];
                if (bat == 9) {
                    bat = 0;
                }

                if (score == 0) {
                    // 3-4. 이닝을 진행, 타자가 쳤을 때 0 이면 out count 를 증가시킨다.
                    out++;
                } else {
                    // 3-5. 0이 아니면 1~3루 배열을 뒤에서부터 탐색한다.
                    result += move(score);
                }
            }
        }
        return result;
    }

    private static int move(int score) {
        int result = 0;
        for (int idx = 3; idx > 0; idx--) {
            if (ru[idx]) {
                if (idx + score >= 4) {
                    // 3-5-1. 배열 값이 1일 때 idx + 타자점수 >= 4 이면 전체점수++
                    result++;
                } else {
                    // 3-5-2. 4 미만이면 arr[idx + 타자점수] 를 true로 바꾼다.
                    ru[idx + score] = true;
                }
                // 3-5-3. arr[idx] 를 false 로 바꾼다. (이미 사람이 이동했기 때문)
                ru[idx] = false;
            }
        }

        if (score == 4) {
            // 3-5-4. 홈런을 쳤다면, 친 사람도 점수에 추가해준다.
            result++;
        } else {
            // 3-5-5. 홈런이 아니라면, 친 사람을 해당 루로 이동시킨다.
            ru[score] = true;
        }
        return result;
    }
}
