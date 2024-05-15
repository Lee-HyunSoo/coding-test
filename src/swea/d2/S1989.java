package swea.d2;

import java.util.Scanner;

/**
 * 초심자의 회문 검사
 */
public class S1989 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int i = 1; i <= t; i++) {
            String s1 = scan.nextLine();
            String s2 = new StringBuilder(s1).reverse().toString();

            if (s1.equals(s2)) {
                System.out.println("#" + i + " " + 1);
            } else {
                System.out.println("#" + i + " " + 0);
            }
        }
    }
}
