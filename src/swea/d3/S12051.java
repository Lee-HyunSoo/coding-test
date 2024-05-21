package swea.d3;

import java.util.*;

/**
 * 프리셀 통계
 */
public class S12051 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            long n = scan.nextInt();
            int pd = scan.nextInt();
            int pg = scan.nextInt();

            /*
            총 g판, 승률 = 승 / (승 + 패 = 전체판수 = g = n + x)
            pg / 100 = (n * pd / 100) + x / n + x
            pg = (n * pd) + 100x / n + x
            pg(n + x) = (n * pd) + 100x
            100x - pgx = pgn - pdn
            x = n(pg - pd) / (100 - pg) -> g >= n >= d

            1000(2) / 17

             */

            String answer = "Possible";

            System.out.println(10 / 3);




        }
    }
}
