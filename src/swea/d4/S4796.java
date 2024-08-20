package swea.d4;

import java.util.Scanner;

/**
 * 의석이의 우뚝 선 산
 *
 * 1. 문제 정리
 *  1-1. 산이 일렬로 늘어져있고, 같은 높이를 가지는 산은 없다.
 *  1-2. 우뚝선 산
 *      1-2-1. 어떤 구간 i, j 가 있다. (i < k < j)
 *      1-2-2. i <= l < k 인 모든 l 에 대해 hl < h(l + 1) 이 성립한다.
 *      1-2-3. k <= l < j 인 모든 l 에 대해 hl > h(l + 1) 이 성립한다.
 *      1-2-4. 두지점 i 와 i+1 은 우뚝 선 산이 될 수 없다.
 *
 * 2. 우뚝 선 산이 될 수 있는 구간의 수
 *  2-1. 현재 산이 이전 산보다 높다면 up++
 *      2-1-1. 현재 산이 이전 산보다 높은데, down 이 0이 아니라면 새로운 peek 라는 뜻이다.
 *      2-1-2. 모든 경우의 수를 구해야하므로, 가장 높은지점 왼쪽의 산의 개수 *  가장높은지점 오른쪽의 산의 개수
 *      2-1-3. 즉, 하나의 peek 당 좌우 산의 개수를 세고, 새로운 peek 가 나왔을 때 정산한다.
 *      2-1-4. (좌 * 우) 를 더하고, 0으로 초기화한다.
 *  2-2. 아니라면 down++
 *  2-3. 마지막 idx 가 새로운 peek 가 아닐 수 있기 때문에, 반복문 종료 후 한번 더 계산한다.
 */
public class S4796 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt();
            int[] mountain = new int[n];
            for (int idx = 0; idx < n; idx++) {
                mountain[idx] = scan.nextInt();
            }

            int answer = 0;
            int up = 0, down = 0;
            for (int idx = 1; idx < n; idx++) {
                // 2-1. 현재 산이 이전 산보다 높다면 up++
                if (mountain[idx - 1] < mountain[idx]) {
                    // 2-1-1. 현재 산이 이전 산보다 높은데, down 이 0이 아니라면 새로운 peek 라는 뜻이다.
                    if (down > 0) {
                        // 2-1-2. 모든 경우의 수를 구해야하므로, 가장 높은지점 왼쪽의 산의 개수 *  가장높은지점 오른쪽의 산의 개수
                        // 2-1-3. 즉, 하나의 peek 당 좌우 산의 개수를 세고, 새로운 peek 가 나왔을 때 정산한다.
                        answer += (up * down);
                        // 2-1-4. (좌 * 우) 를 더하고, 0으로 초기화한다.
                        up = 0;
                        down = 0;
                    }
                    up++;
                } else {
                    // 2-2. 아니라면 down++
                    down++;
                }
            }
            // 2-3. 마지막 idx 가 새로운 peek 가 아닐 수 있기 때문에, 반복문 종료 후 한번 더 계산한다.
            answer += (up * down);
            System.out.println("#" + tc + " " + answer);
        }
    }
}
