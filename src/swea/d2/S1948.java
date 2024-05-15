package swea.d2;

import java.util.*;

/**
 * 날짜 계산기
 */
public class S1948 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int m1 = scan.nextInt();
            int d1 = scan.nextInt();
            int m2 = scan.nextInt();
            int d2 = scan.nextInt();

            Map<Integer, Integer> map = new HashMap<>();
            map.put(1, 31);
            map.put(2, 28);
            map.put(3, 31);
            map.put(4, 30);
            map.put(5, 31);
            map.put(6, 30);
            map.put(7, 31);
            map.put(8, 31);
            map.put(9, 30);
            map.put(10, 31);
            map.put(11, 30);
            map.put(12, 31);

            int answer = 0;
            if (m1 == m2) {
                answer = d2 - d1 + 1;
            } else {
                answer += map.get(m1) - d1 + 1;
                answer += d2;

                for (int i = m1 + 1; i < m2; i++) {
                    answer += map.get(i);
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
