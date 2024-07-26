import java.util.*;

/**
 * 최대 성적표 만들기
 *
 * n 개의 과목, k 개 선택
 * 선택한 과목의 합이 가장 최대인 경우
 *  -- 반대로 정렬 -> 위에서 부터 넣기
 */
public class S4466 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt();
            int k = scan.nextInt();

            Integer[] score = new Integer[n];
            for (int idx = 0; idx < n; idx++) {
                score[idx] = scan.nextInt();
            }
            Arrays.sort(score, (a, b) -> b - a);

            int answer = 0;
            for (int idx = 0; idx < k; idx++) {
                answer += score[idx];
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}
