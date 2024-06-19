package swea.d3;

import java.util.*;

/**
 * 극장 좌석
 */
public class S8500 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scan.nextInt();
            }
            Arrays.sort(arr);

            Stack<Integer> s = new Stack<>();
            int answer = 0;
            for (int i = 0; i < n; i++) {
                if (!s.isEmpty()) {
                    if (s.peek() > arr[i]) {
                        answer += s.peek() + 1;
                    } else {
                        answer += arr[i] + 1;
                    }
                } else {
                    answer += arr[i] + 1;
                }
                s.push(arr[i]);
            }
            answer += s.peek();
            System.out.println("#" + c + " " + answer);
        }
    }
}

// ** 2 ** 2 *** 3 ***
// * 1 ** 2 *** 3 **** 4 ***** 5 *****
