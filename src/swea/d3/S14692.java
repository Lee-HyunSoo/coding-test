package swea.d3;

import java.util.*;

/**
 * 통나무 자르기
 */
public class S14692 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            if (n % 2 == 0) {
                System.out.println("#" + c + " Alice");
            } else {
                System.out.println("#" + c + " Bob");
            }
        }
    }
}
