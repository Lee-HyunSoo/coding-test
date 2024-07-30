package swea.d3;

import java.util.*;

/**
 * 보충학습과 평균
 *
 * 점수 40 이상은 점수 그대로, 40 미만은 보충수업 -> 40으로 상승
 *
 */
public class S3314 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {

            int sum = 0;
            for (int i = 0; i < 5; i++) {
                int input = scan.nextInt();
                if (input < 40) {
                    input = 40;
                }
                sum += input;
            }
            System.out.println("#" + tc + " " + sum / 5);
        }
    }
}