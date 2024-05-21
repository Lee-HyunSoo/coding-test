package swea.d3;

import java.util.*;

/**
 * 교환학생
 */
public class S13038 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            int[] arr = new int[7];

            for (int i = 0; i < 7; i++) {
                arr[i] = scan.nextInt();
            }

            int answer = Integer.MAX_VALUE;
            for (int i = 0; i < 7; i++) {
                if (arr[i] == 1) {
                    int day = i;
                    int cnt = 0;

                    while (cnt < n) {
                        if (arr[day % 7] == 1) {
                            cnt++;
                        }
                        day++;
                    }
                    answer = Math.min(answer, day - i);
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
