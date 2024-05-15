package swea.d2;

import java.util.*;

/**
 * 아름이의 돌 던지기
 */
public class S1285 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();

            int distance = Integer.MAX_VALUE;
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int stone = scan.nextInt();
                distance = Math.min(distance, Math.abs(stone));
                list.add(stone);
            }

            int count = 0;
            for (int i : list) {
                if (Math.abs(i) == distance) {
                    count++;
                }
            }
            System.out.println("#" + c + " " + distance + " " + count);
        }
    }
}
