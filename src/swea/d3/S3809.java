package swea.d3;

import java.util.*;

/**
 * 화섭이의 정수 나열
 *
 * 정수열 = 순서를 바꾸면 안됨
 * 0 ~ 숫자를 늘려나가면서
 * 특정 구간의 수열이 목표 숫자인지 체크
 *  -- 입력을 한줄의 긴 String 으로 변환
 *  -- 해당 String 안에 그 숫자가 있는지 체크 (contains)
 */
public class S3809 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt();

            String nums = "";
            for (int i = 0; i < n; i++) {
                int input = scan.nextInt();
                nums += String.valueOf(input);
            }

            int answer = 0;
            while (true) {
                String num = String.valueOf(answer);
                if (!nums.contains(num)) {
                    break;
                }
                answer++;
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}