package swea.d3;

import java.util.*;

/**
 * 준환이의 운동관리
 *
 * 1주일에 L분 이상 U분 이하의 운동을 해야함, 이번주에 X분 운동
 * 제한시간 초과 운동을 했다 = -1
 * L ~ U 사이만큼 했다 = 0
 * 부족하게 했다 = L - X
 */
public class S3431 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int l = scan.nextInt();
            int u = scan.nextInt();
            int x = scan.nextInt();

            int answer;
            if (x > u) {
                answer = -1;
            } else if (l <= x && x <= u) {
                answer = 0;
            } else {
                answer = l - x;
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}