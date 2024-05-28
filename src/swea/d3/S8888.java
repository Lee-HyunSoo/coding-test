package swea.d3;

import java.util.*;

/**
 * 시험
 */
public class S8888 {

    static class Participant {
        int idx, cnt, score; // 참가번호, 푼 문제수, 점수

        public Participant(int idx, int cnt, int score) {
            this.idx = idx;
            this.cnt = cnt;
            this.score = score;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String[] split = scan.nextLine().split(" ");

            int N = Integer.parseInt(split[0]); // 참가인원
            int T = Integer.parseInt(split[1]); // 문제수
            int P = Integer.parseInt(split[2]); // 내번호

            int[][] arr = new int[N][T];
            for (int i = 0; i < N; i++) {
                String[] pt = scan.nextLine().split(" ");
                for (int j = 0; j < T; j++) {
                    arr[i][j] = Integer.parseInt(pt[j]); // 0: 틀림 1: 맞음
                }
            }

            int[] problem = new int[T];
            for (int j = 0; j < T; j++) {
                int score = 0;
                for (int i = 0; i < N; i++) {
                    if (arr[i][j] == 0) {
                        score++;
                    }
                }
                problem[j] = score;
            }

            List<Participant> participants = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                int score = 0, cnt = 0;
                for (int j = 0; j < T; j++) {
                    if (arr[i][j] == 1) {
                        score += problem[j];
                        cnt++;
                    }
                }
                participants.add(new Participant(i, cnt, score));
            }

            P--;
            int answer1 = participants.get(P).score; // 최종점수
            int answer2 = 1; // 등수
            for (int i = 0; i < participants.size(); i++) {
                if (i != P) {
                    if (participants.get(i).score > participants.get(P).score) {
                        answer2++;
                    } else if (participants.get(i).score == participants.get(P).score
                            && participants.get(i).cnt > participants.get(P).cnt) {
                        answer2++;
                    } else if (participants.get(i).score == participants.get(P).score
                            && participants.get(i).cnt == participants.get(P).cnt
                            && participants.get(i).idx < participants.get(P).idx) {
                        answer2++;
                    }
                }
            }
            System.out.println("#" + c + " " + answer1 + " " + answer2);
        }
    }
}
