package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 야구
 * <p>
 * 1. 문제 조건
 * 1-1. 9명이 두 팀으로 나뉘어서 공 / 수 번갈아가면서 게임
 * 1-2. 총 N 이닝 동안 게임 진행
 * 1-3. 한 이닝에 3아웃이 발생하면 이닝 종료, 공수 교대
 * 1-4. 경기 시작 전 타순을 정하고, 경기 중에 변경은 불가능
 * 1-4-1. 9번 타자가 공을 쳤는데 3아웃이 발생하지 않은 상태면 이닝은 끝나지 않고, 1번 타자가 다시 타석에 선다.
 * 1-4-2. 타순은 닝이 변경되어도 순서를 유지해야한다. (2이닝에 6번타자가 마지막이었다면, 3이닝은 7번부터)
 * 1-5. 공격은 타자쪽 팀: 타자가 1->2->3루를 거쳐 홈으로 돌아와야 1점
 * 1-6. 루에 있는 선수를 주자라고 하고, 이닝이 시작될 때 주자는 없다.
 * 1-7. 타자가 공을 쳐서 얻을 수 있는 결과 (안타, 2루타, 3루타, 홈런, 아웃)
 * 1-7-1. 안타: 타자와 모든 주자가 한 루씩 진루
 * 1-7-2. 2루타: 타자와 모든 주자가 두 루씩 진루
 * 1-7-3. 3루타: 타자와 모든 주자가 세 루씩 진루
 * 1-7-4. 홈런: 타자와 모든 주자가 홈까지 진루
 * 1-7-5. 아웃: 모든 주자는 진루하지 못하고, 공격팀에 아웃이 하나 증가
 * 1-8. 1번 선수는 4번타자 고정, 나머지 2~8번 사이에서 타순을 정해야 함
 * 1-9. 가장 많은 특점을 하는 타순을 찾고, 그때의 득점
 * <p>
 * 2. 1~4루 까지 저장하는 배열을 만든다.
 * 3. 타순은 조합으로 구한다.
 * 3-1. 이 때 4번 타순은 1번 타자로 고정한다.
 * 3-2. 타자가 쳤을 때 0이면 아웃 cnt 가 증가한다.
 * 3-3. 아웃 카운트가 3이 되면 해당 이닝을 종료한다.
 * 3-4. 0이 아니면 1~4루 배열을 뒤에서부터 탐색한다.
 * 3-4-1. 배열 값이 0이면 continue
 * 3-4-2. 배열 값이 1이면 idx + 타자점수 >= 4 이면 전체점수++, 해당 idx 를 0으로 바꾼다.
 * 3-4-3. 4 미만이면 arr[idx + 타자점수] 를 1로, arr[idx] 는 0으로 바꾼다.
 * 3-5. 이 과정을 3 아웃이 나올 때까지 반복한다.
 */
public class Q17281 {

    static int n;
    static int[][] graph;
    static boolean[] ru;
    static int[] attack;
    static int score;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        graph = new int[n][9];
        ru = new boolean[4];
        attack = new int[9];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                graph[row][col] = scan.nextInt();
            }
        }

        int answer = 0;
        for (int row = 0; row < n; row++) {
            score = 0;
            attack[3] = graph[row][0];
            combination(row, 0, 0);
            answer += score;
        }
        System.out.println(answer);
    }

    private static void combination(int row, int level, int idx) {
        if (level == 9) {
            System.out.println(Arrays.toString(attack));

            int sum = 0;
            int out = 0;

            int index = 0;
            while (true) {
                if (index == 10) {
                    index = 0;
                }

                if (out == 3) {
                    break;
                }

                if (attack[index] == 0) {
                    out++;
                    continue;
                }

                if (attack[index] == 4) {
                    for (int i = 3; i >= 0; i--) {
                        if (ru[i]) {
                            sum++;
                            ru[i] = false;
                        }
                    }
                } else {
                    for (int i = 3; i >= 0; i--) {
                        if (ru[i]) {
                            ru[i] = false;
                            if (i + attack[index] >= 4) {
                                sum++;
                            } else {
                                ru[i + attack[index]] = true;
                            }
                        }
                    }
                }
                index++;
            }
            score = Math.max(score, sum);
            return;
        }

        for (int i = idx; i < 9; i++) {
            if (level == 3) {
                combination(row, level + 1, i + 1);
                continue;
            }
            attack[level] = graph[row][i];
            combination(row, level + 1, i + 1);
        }
    }
}
