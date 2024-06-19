package swea.d3;

import java.util.*;

/**
 * 조 만들기
 */
public class S8104 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            int k = scan.nextInt();

            int[] result = new int[k];
            for (int i = 1; i <= n * k; i += k) {
                if ((i / k) % 2 == 0) {
                    for (int j = 0; j < k; j++) {
                        result[j] += (j + i);
                    }
                } else {
                    for (int j = k - 1; j >= 0; j--) {
                        result[k - j - 1] += (j + i);
                    }
                }
            }

            System.out.print("#" + c + " ");
            for (int i = 0; i < k; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
        }
    }
}

/*
1 ~ 6 -> 1 2 3 4 5 6
12 ~ 7 -> 12 11 10 9 8 7
13 ~ 18 -> 13 14 15 16 17 18
24 ~ 19 -> 24 23 22 21 20 19
 */
