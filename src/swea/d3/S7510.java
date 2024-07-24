package swea.d3;

import java.util.*;

/**
 * 상원이의 연속 합
 *
 * 1. 투 포인터
 * 2. left - right
 */
public class S7510 {

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int T;
        T = scan.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)	{
            int input = scan.nextInt();

            int left = 1, right = 2;
            int sum = left, answer = 0;
            while(right < input) {
                if (sum < input) {
                    sum += right++;

                    if (sum == input) {
                        answer++;
                        sum -= left++;
                        sum += right++;
                    }

                } else if (sum > input) {
                    sum -= left++;

                    if (sum == input) {
                        answer++;
                        sum -= left++;
                        sum += right++;
                    }
                }
            }
            System.out.println("#" + test_case + " " + (answer + 1));
        }
    }
}