package swea.d2;

import java.util.Scanner;

/**
 * 쉬운 거스름돈
 */
public class S1970 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        int[] coin = {50000, 10000, 5000, 1000, 500, 100, 50, 10};

        for (int c = 1; c <= t; c++) {
            int money = scan.nextInt();
            System.out.println("#" + c + " ");
            for (int i = 0; i < coin.length; i++) {
                int answer = money / coin[i];
                money %= coin[i];
                System.out.print(answer + " ");
            }
            System.out.println();
        }
    }
}
