package swea.d3;

import java.util.*;

/**
 * 영준이와 신비한 뿔의 숲
 *
 * 뿔이 한개달린 유니콘, 두개달린 트윈혼
 * n 개의 뿔, m 마리의 짐승
 * 유니콘의 수, 트윈혼의 수 ?
 *
 * 5, 3
 * (x * 1) + (y * 2) = n
 * (x + y) = m -> y = (m - x)
 *  -- 2m - x = n
 *  -- x = 2m - n
 *  -- y = n - m
 */
public class S3142 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt();
            int m = scan.nextInt();

            int uni = 2 * m - n;
            int twin = n - m;
            System.out.println("#" + tc + " " + uni + " " + twin);
        }
    }
}
