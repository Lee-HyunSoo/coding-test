package swea.d3;

import java.util.*;

/**
 * 두 수의 덧셈
 *
 * 두 수를 더하는데 두 수의 범위가 (1 <= a, b <= 10^100)
 * 문자열로 입력 -> 맨 끝에만 더하기
 * 더한게 10보다 크면 1 올림, 더하고 10보다 크면 1 올림 ...
 *  -- 두 문자열의 길이 중 긴거 뽑고, 짧은쪽은 앞에다 부족한만큼 0을 붙여줌
 *  -- 이를 통해 문자열 길이 맞추기
 *  -- 끝에서부터 더해야하므로 for 문이 - 방향으로 돌아야함
 *  -- sum == 자릿수별 합 + 이전에 올라온 올림값 합
 *  -- sum 이 10보다 크거나 같다면, 올림값 1로 변경
 *  -- 만약 올림값이 1인데 마지막 idx 라면, 마지막에 1 추가
 */
public class S3260 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int tc = 1; tc <= t; tc++) {
            String[] split = scan.nextLine().split(" ");
            int len = init(split);
            String s1 = split[0];
            String s2 = split[1];

            StringBuilder result = new StringBuilder(); // 하나씩 쌓는 결과값, 마지막에 뒤집어야함
            int up = 0; // 올림값
            for (int i = len - 1; i >= 0; i--) {
                int n1 = Character.getNumericValue(s1.charAt(i));
                int n2 = Character.getNumericValue(s2.charAt(i));
                int sum = n1 + n2 + up;
                up = 0;

                if (sum >= 10) {
                    String num = String.valueOf(sum);
                    result.append(num.charAt(1));
                    up = 1;

                    if (i == 0 && up == 1) {
                        result.append("1");
                    }
                } else {
                    result.append(String.valueOf(sum));
                }
            }
            String answer = result.reverse().toString();
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static int init(String[] split) {
        int len1 = split[0].length();
        int len2 = split[1].length();
        if (len1 < len2) {
            String tmp = "";
            for (int i = 0; i < len2 - len1; i++) {
                tmp += "0";
            }
            split[0] = tmp + split[0];
        } else if (len1 > len2){
            String tmp = "";
            for (int i = 0; i < len1 - len2; i++) {
                tmp += "0";
            }
            split[1] = tmp + split[1];
        }
        return Math.max(len1, len2);
    }
}