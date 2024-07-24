package swea.d3;

import java.util.*;

/**
 * 세영이의 SEM력 연도
 */
public class S7532 {

    public static void main(String args[]) throws Exception {
        Scanner scan = new Scanner(System.in);
        int T;
        T = scan.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)	{
            int S = scan.nextInt();
            int E = scan.nextInt();
            int M = scan.nextInt();

            int answer = 1;
            int s = 1, e = 1, m = 1;
            while(true) {
                if (s == S && e == E && m == M) break;
                answer++;
                s++;
                e++;
                m++;
                if (s == 366) s = 1;
                if (e == 25) e = 1;
                if (m == 30) m = 1;
            }
            System.out.println("#" + test_case + " " + answer);
        }
    }
}
