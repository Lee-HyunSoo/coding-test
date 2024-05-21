package swea.d3;

import java.util.*;

/**
 * 두 전구
 */
public class S12741 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int A = scan.nextInt();
            int B = scan.nextInt();
            int C = scan.nextInt();
            int D = scan.nextInt();

            int[] x = new int[101];
            for (int i = A; i < B; i++) {
                x[i] = 1;
            }
            int[] y = new int[101];
            for (int i = C; i < D; i++) {
                y[i] = 1;
            }

            int min = Math.min(A, C);
            int max = Math.max(B, D);
            int answer = 0;
            for (int i = min; i < max; i++) {
                if (x[i] == 1 && y[i] == 1) {
                    answer++;
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
