package swea.d3;

import java.util.*;

/**
 * 구구단 1
 */
public class S12004 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();

            String answer = "No";
            for (int i = 1; i <= 9; i++) {
                for (int j = 1; j <= 9; j++) {
                    if (i * j == n) {
                        answer = "Yes";
                        break;
                    }
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
