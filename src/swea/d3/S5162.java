package swea.d3;

import java.util.*;

/**
 * 두가지 빵의 딜레마
 *
 * 같은 종류를 여러개 사도 됨
 *  -- 제일 싼거로 여러개 사면 됨
 */
public class S5162 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            int c = scan.nextInt();

            int price = Math.min(a,  b);
            System.out.println("#" + tc + " " + c / price);
        }
    }
}
