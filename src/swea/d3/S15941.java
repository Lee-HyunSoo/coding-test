package swea.d3;

import java.util.*;

/**
 * 평행사변형
 */
public class S15941 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            System.out.println("#" + c + " " + n * n);
        }
    }
}
