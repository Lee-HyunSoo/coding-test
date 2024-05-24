package swea.d3;

import java.util.*;

/**
 * 소득 불균형
 */

public class S10505 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();

            int sum = 0;
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int input = scan.nextInt();
                list.add(input);
                sum += input;
            }

            int answer = 0;
            for (int i = 0; i < n; i++) {
                if (list.get(i) <= (double) sum / n) {
                    answer++;
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
