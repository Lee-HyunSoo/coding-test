package swea.d3;

import java.util.*;

/**
 * 아바바바
 */
public class S8840 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            long L = scan.nextLong();
            L = (L - 1) / 2;
            System.out.println("#" + c + " " + L * L);
        }
    }
}
