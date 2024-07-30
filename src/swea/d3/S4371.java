package swea.d3;

import java.util.*;

/**
 * 항구에 들어오는 배
 *
 * 모든 배들이 항구에 들어온 날 - 1일차
 * 배가 한척이라도 항구에 들렀던 날 = 즐거운 날
 *
 * 배는 주기적으로 항구에 옴 (주기 3 -> 1일차, 4일차, 7일차 ...)
 * 즐거운 날의 목록이 주어질 때, 항구에 들렀던 배 종류의 최소 수
 *
 * 1, 7, 10, 13, 19 ...
 * (7 - 1) = 주기 6
 * 남은거 (10 - 1) = 주기 9
 *
 * 1번 ~ n번까지 순회하며
 * set 에 이미 있는 값이면 continue
 * 아니면 현재값 ~ n번까지 주기만큼 더하며 set 에 다 집어넣음
 *  -- 오름차순으로 입력되기 때문에 n번이 가장 큰 수
 */
public class S4371 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt();

            int[] port = new int[n];
            for (int i = 0; i < n; i++) {
                port[i] = scan.nextInt();
            }

            Set<Integer> set = new HashSet<>();
            int answer = 0;
            for (int i = 1; i < n; i++) {
                if (set.contains(port[i])) {
                    continue;
                }

                int diff = port[i] - 1; // 주기
                for (int j = port[i]; j <= port[n - 1]; j += diff) {
                    set.add(j);
                }
                answer++;
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}
