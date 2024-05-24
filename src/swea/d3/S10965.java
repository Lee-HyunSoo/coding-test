package swea.d3;

import java.util.*;

/**
 * 제곱수 만들기
 */
public class S10965 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int a = scan.nextInt();

            boolean[] prime = new boolean[a + 1];
            prime[1] = true;
            for (int i = 2; i < Math.sqrt(a); i++) {
                if (!prime[i]) {
                    for (int j = i * i; j <= a; j += i) {
                        prime[j] = true;
                    }
                }
            }

            int b = 1;
            if (!prime[a]) {
                b = a;
            } else {

                while (Math.sqrt(a * b) % 1 != 0) {
                    b++;
                }
            }
            System.out.println("#" + c + " " + b);
        }
    }
}
