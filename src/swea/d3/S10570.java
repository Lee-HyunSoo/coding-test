package swea.d3;

import java.util.*;

/**
 * 제곱 팰린드롬 수
 */
public class S10570 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            int a = scan.nextInt();
            int b = scan.nextInt();

            int answer = 0;
            for (int i = a; i <= b; i++) {
                if (Math.sqrt(i) % 1 == 0) {
                    String s1 = String.valueOf(i);
                    String s2 = String.valueOf((int) Math.sqrt(i));

                    if (s1.equals(new StringBuilder(s1).reverse().toString())
                            && s2.equals(new StringBuilder(s2).reverse().toString())) {
                        answer++;
                    }
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
