package swea.d3;

import java.util.*;

/**
 * 24시간
 */
public class S12368 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            System.out.println("#" + c + " " + (a + b) % 24);
        }
    }
}
