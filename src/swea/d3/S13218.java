package swea.d3;

import java.util.*;

/**
 * 조별과제
 */
public class S13218 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            System.out.println("#" + c + " " + n / 3);
        }
    }
}
