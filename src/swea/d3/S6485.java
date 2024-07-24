package swea.d3;

import java.util.*;

/**
 * 삼성시의 버스 노선
 *
 * 총 5000개의 버스 정류장 (1 ~ 5000)
 * N 개의 버스 노선
 *  -- i 번째 버스 노선은 번호가 Ai 이상, Bi 이하인 정류장만을 다니는 노선
 * P 개의 버스 정류장에 대해 각 정류장에 몇 개의 버스 노선이 다니는지?
 *
 * 1 3 -> 1번째 버스 노선, 1 ~ 3번 정류장 다님
 * 2 5 -> 2번째 버스 노선, 2 ~ 5번 정류장 다님
 * P == 1 -> 1개, P == 2 -> 2개 ...
 *  -- arr[1] = 1, arr[2] = 2, arr[3] = 2 ..
 *  -- 배열을 사용해 정류장 표현
 */
public class S6485 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt();

            int[] busStation = new int[5001];
            for (int idx = 0; idx < n; idx++) {
                int start = scan.nextInt();
                int end = scan.nextInt();

                for (int st = start; st <= end; st++) {
                    busStation[st] += 1;
                }
            }

            int p = scan.nextInt();
            System.out.print("#" + tc + " ");
            for (int idx = 0; idx < p; idx++) {
                int j = scan.nextInt();
                System.out.print(busStation[j] + " ");
            }
            System.out.println();
        }
    }
}