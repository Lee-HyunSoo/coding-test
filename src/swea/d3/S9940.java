package swea.d3;

import java.util.*;

/**
 * 순열
 */
public class S9940 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();

            Set<Integer> set = new HashSet<>();
            int sum1 = 0;
            for (int i = 0; i < n; i++) {
                set.add(scan.nextInt());
                sum1 += i + 1;

            }

            int sum2 = 0;
            for (int i : set) {
                sum2 += i;
            }

            if (sum1 == sum2) {
                System.out.println("#" + c + " Yes");
            } else {
                System.out.println("#" + c + " No");
            }
        }
    }
}
