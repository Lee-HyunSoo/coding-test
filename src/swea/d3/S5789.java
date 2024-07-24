package swea.d3;

import java.util.*;

/**
 * 현주의 상자 바꾸기
 *
 * i = 1 -> 1, 3 -> idx 1 ~ 3 까지 1
 * i = 2 -> 2, 4 -> idx 2 ~ 4 까지 1
 */
public class S5789 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt();
            int q = scan.nextInt();

            int[] box = new int[n];
            for (int i = 0; i < q; i++) {
                int start = scan.nextInt();
                int end = scan.nextInt();

                for (int idx = start - 1; idx <= end - 1; idx++) {
                    box[idx] = i + 1;
                }
            }

            System.out.print("#" + tc + " ");
            for (int b : box) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
    }
}