package swea.d3;

import java.util.*;

/**
 * 원 안의 점
 */
public class S16910 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        /*
        1 0
        2 1
        3 3
        4 5
        5 7
        6 9
        7 11
        8 13
        9 14
        10 15
         */

        int num = 1, sum = 0;
        for (int i = 2; i <= 9; i++) {
            sum += num;
            num += 2;
        }
        sum *= 4;
        sum += 41;
        System.out.println(sum);



        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
        }
    }
}
