package swea.d3;

import java.util.*;

/**
 * 코딩 토너먼트1
 */
public class S8673 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int k = scan.nextInt();
            int arrSize = (int) Math.pow(2, k);

            int[] arr = new int[arrSize];
            for (int i = 0; i < arrSize; i++) {
                arr[i] = scan.nextInt();
            }

            int answer = 0;
            while (arr.length > 1) {
                int[] win = new int[arrSize / 2];
                int winIdx = 0;
                for (int i = 1; i < arrSize; i += 2) {
                    answer += Math.abs(arr[i] - arr[i - 1]);
                    win[winIdx++] = Math.max(arr[i], arr[i - 1]);
                }
                arr = win;
                arrSize = arr.length;
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
