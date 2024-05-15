package swea.d2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 조교의 성적 매기기
 */
public class S1983 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] grade = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};
        int t = scan.nextInt();
        for (int i = 1; i <= t; i++) {
            int n = scan.nextInt();
            int k = scan.nextInt();

            List<Double> list = new ArrayList<>();
            for (int j = 1; j <= n; j++) {
                int mid = scan.nextInt();
                int end = scan.nextInt();
                int homework = scan.nextInt();

                double score = mid * 0.35 + end * 0.45 + homework * 0.2;
                list.add(score);
            }
            double result = list.get(k - 1);
            Collections.sort(list, Collections.reverseOrder());

            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) == result) {
                    System.out.println("#" + i + " " + grade[j / (n / 10)]);
                    // n이 20일때 각 점수는 두명씩 가능
                    // 0 / 2 -> 0, 1 / 2 -> 0
                    // 2 / 2 -> 1, 3 / 2 -> 1 ...
                }
            }
        }
    }
}
