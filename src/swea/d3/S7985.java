package swea.d3;

import java.util.*;

/**
 * Rooted Binary Tree 재구성
 */
public class S7985 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int k = scan.nextInt();

            int size = (int) Math.pow(2, k) - 1;
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = scan.nextInt();
            }

            int idx = size / 2;
            System.out.println("#" + c + " " + arr[idx]);
            while (true) {

            }

        }
    }
}

/*
3* 2 7* 5 6* 1 4*
3
1 5
0 2 4 6
 */

/*
5* 3 6* 2 8* 7 9* 5 10* 6 11* 1 12* 4 13*
7
5 9
4 6 8 10
3 11


 */