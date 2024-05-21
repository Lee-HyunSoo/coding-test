package swea.d3;

import java.util.*;

public class S11285 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            int[] score = {20, 40, 60, 80, 100, 120, 140, 160, 180, 200};

            int answer = 0;
            for (int i = 0; i < n; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();

                double pos = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
                if (pos > 200) {
                    continue;
                }

                int start = 0;
                int end = 9;
                while (start <= end) {
                    int mid = (start + end) / 2;

                    if (score[mid] > pos) {
                        end = mid - 1;
                    } else if (score[mid] < pos) {
                        start = mid + 1;
                    } else {
                        start = mid;
                        break;
                    }
                }
                if (start == 10) {
                    start--;
                }

                for (int p = 1; p <= 10; p++) {
                    if (20 * (11 - p) == score[start]) {
                        answer += p;
                        break;
                    }
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
