package swea.d2;

import java.util.Scanner;

/**
 * 파스칼의 삼각형
 */
public class S2005 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for (int i = 1; i <= t; i++) {
            int input = scan.nextInt();

            System.out.println("#" + i);
            for (int j = 0; j < input; j++) {
                if (j == 0) {
                    System.out.println(1);
                } else if (j == 1) {
                    System.out.println(1 + " " + 1);
                } else {
                    for (int k = 0; k <= j; k++) {
                        System.out.print(calc(j, k) + " ");
                    }
                    System.out.println();
                }
            }
        }
    }

    public static int calc(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    public static int factorial(int num) {
        int result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}
