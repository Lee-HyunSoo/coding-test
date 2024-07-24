package swea.d3;

import java.util.*;

/**
 * 부먹왕국의 차원 관문
 * 0의 개수 count
 * 0의 개수가 d 이면 관문 하나 설치, 0의 개수 0으로 초기화
 * 0의 개수가 d가 되기 전에 관문을 만나면 0의 개수 0으로 초기화
 */
public class S7964 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt();
            int d = scan.nextInt();

            int[] kingdom = new int[n];
            for (int i = 0; i < n; i++) {
                kingdom[i] = scan.nextInt();
            }

            int cnt = 0, answer = 0;
            for (int idx = 0; idx < kingdom.length; idx++) {
                if (kingdom[idx] == 0) {
                    cnt++;
                    if (cnt == d) {
                        answer++;
                        cnt = 0;
                    }
                } else {
                    cnt = 0;
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}
