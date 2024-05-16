package swea.d3;

import java.util.*;

/**
 * 증가하는 사탕 수열
 */
public class S20551 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int A = scan.nextInt();
            int B = scan.nextInt();
            int C = scan.nextInt();

            int answer = 0;
            boolean flag = false;
            while (B >= C) {
                B--;
                if (B == 0) {
                    flag = true;
                    break;
                }
                answer++;
            }
            while (A >= B) {
                A--;
                if (A == 0) {
                    flag = true;
                    break;
                }
                answer++;
            }
            if (flag) {
                System.out.println("#" + c + " " + -1);
            } else {
                System.out.println("#" + c + " " + answer);
            }
        }
    }
}
