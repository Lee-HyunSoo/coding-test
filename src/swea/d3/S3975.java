package swea.d3;

import java.util.*;

/**
 * 승률 비교하기
 *
 * 앨리스 - B 전 A 승 , 밥 - D 전 C 승
 */
public class S3975 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            int c = scan.nextInt();
            int d = scan.nextInt();

            double alice = (double) a / b;
            double bob = (double) c / d;

            if (alice > bob) {
                System.out.println("#" + tc + " ALICE");
            } else if (bob > alice) {
                System.out.println("#" + tc + " BOB");
            } else {
                System.out.println("#" + tc + " DRAW");
            }
        }
    }
}