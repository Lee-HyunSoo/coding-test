package baekjoon;

import java.util.*;

/**
 * 월드컵
 *
 * 1. 문제 정리
 * 	1-1. 6개의 팀이 각각 5번씩 경기를 치룬다.
 * 	1-2. 총 경기수 15번, 경기가 가능한지 판별
 *
 * 2. 팀을 입력받는데, 승 + 비김 + 패가 5가 아니면 5판을 할 수없기 때문에 실패
 * 3. 팀이 6개로 고정되있으므로, 가능한 모든 매치들을 찾는다. (arr[15][2])
 * 4. dfs 를 통해 경기 진행
 * 	4-1. a 팀이 이기고 b 팀이 진 경우
 * 	4-2. a 팀, b 팀이 비긴 경우
 * 	4-3. a 팀이 지고, b 팀이 이긴 경우
 * 	4-4. 끝까지 진행해 15판이 됐다면 종료한다.
 */
public class M6987 {

    static class Team {
        int win, draw, lose;

        Team (int win, int draw, int lose) {
            this.win = win;
            this.draw = draw;
            this.lose = lose;
        }
    }

    static Team[] teams;
    static int[][] matches;
    static boolean isPossible;
    static boolean flag;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        for (int tc = 0; tc < 4; tc++) {
            flag = false;
            String[] split = scan.nextLine().split(" ");
            // 2. 팀을 입력받는데, 승 + 비김 + 패가 5가 아니면 5판을 할 수없기 때문에 실패
            setTeams(split);
            // 3. 팀이 6개로 고정되있으므로, 가능한 모든 매치들을 찾는다. (arr[15][2])
            setMatches();

            if (!isPossible) {
                // 4. dfs 를 통해 경기 진행
                worldCup(0);
            }
            System.out.print((flag ? 1 : 0) + " ");
        }
    }

    private static void worldCup(int level) {
        if (flag) {
            return;
        }

        // 4-4. 끝까지 진행해 15판이 됐다면 종료한다.
        if (level == 15) {
            flag = true;
            return;
        }

        Team a = teams[matches[level][0]];
        Team b = teams[matches[level][1]];

        // 4-1. a 팀이 이기고 b 팀이 진 경우
        if (a.win > 0 && b.lose > 0) {
            a.win--;
            b.lose--;
            worldCup(level + 1);
            a.win++;
            b.lose++;
        }

        // 4-2. a 팀, b 팀이 비긴 경우
        if (a.draw > 0 && b.draw > 0) {
            a.draw--;
            b.draw--;
            worldCup(level + 1);
            a.draw++;
            b.draw++;
        }

        // 4-3. a 팀이 지고, b 팀이 이긴 경우
        if (a.lose > 0 && b.win > 0) {
            a.lose--;
            b.win--;
            worldCup(level + 1);
            a.lose++;
            b.win++;
        }
    }

    private static void setMatches() {
        matches = new int[15][2]; // 최대 15판까지 가능
        int index = 0;
        for (int row = 0; row < 6; row++) {
            for (int col = row + 1; col < 6; col++) {
                matches[index][0] = row;
                matches[index][1] = col;
                index++;
            }
        }
    }

    private static void setTeams(String[] split) {
        teams = new Team[6];
        isPossible = false;
        int index = 0;
        for (int idx = 0; idx < split.length; idx += 3) {
            teams[index] = new Team(
                    Integer.parseInt(split[idx]),
                    Integer.parseInt(split[idx + 1]),
                    Integer.parseInt(split[idx + 2]));

            // 2. 팀을 입력받는데, 승 + 비김 + 패가 5가 아니면 5판을 할 수없기 때문에 실패
            if (teams[index].win + teams[index].draw + teams[index].lose != 5) {
                isPossible = true;
                break;
            }
            index++;
        }
    }
}
