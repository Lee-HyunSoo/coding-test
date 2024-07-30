package swea.d3;

import java.util.*;

/**
 * 퍼펙트 셔플
 *
 * 리스트를 반으로 쪼갬 (1번 / 2번)
 * 서로 교차해서 뽑아서 새로운 리스트 생성
 *  -- 0 ~ n/2, n/2 ~ n 까지 교차선택
 *  -- n 이 홀수면 먼저 놓는 쪽에 한장 더 들어가게
 *  -- 즉, 홀수일 때는 idx 조심
 */
public class S3499 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt();
            scan.nextLine();
            String[] split = scan.nextLine().split(" ");
            List<String> answer = new ArrayList<>();

            int len = (n % 2 == 0) ? n / 2 : (n / 2) + 1;
            for (int i = 0; i < len; i++) {
                answer.add(split[i]);
                if (i + len < n) {
                    answer.add(split[i + len]);
                }
            }

            System.out.print("#" + tc + " ");
            for (String s : answer) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}