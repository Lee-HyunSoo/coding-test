package swea.d3;

import java.util.*;

/**
 * 성공적인 공연 기획
 *
 * i 번째 숫자 = 기립박수를 하고 있는 사람이 i-1 명 이상일 때 기립박수를 하는사람 수
 *  -- 5번째 숫자 = 기립박수를 하고 있는 사람이 4명 이상일 때 기립박수를 하는사람 수
 *  -- 즉, 5번째가 박수를 치려면 이전에 박수를 치고있던 사람이 4명 이상이어야함.
 *  -- 모자르면 그만큼 외부에서 고용해옴
 *
 *  count = 0
 *  count < i - 1 이면, 고용을 해와야 함
 *   -- 고용인원 += (i - 1) - count
 *  고용을 했으면, (i - 1) - count 만큼 count 에도 추가 (박수치는 사람이 늘었기 때문)
 *  모든 인원이 박수를 치길 원하기 때문에 박수치는 사람은 무조건 더한다
 */
public class S4789 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int tc = 1; tc <= t; tc++) {
            String input = scan.nextLine();
            char[] clap = new char[input.length() + 1];
            for (int idx = 1; idx < clap.length; idx++) {
                clap[idx] = input.charAt(idx - 1);
            }

            int count = 0, answer = 0;
            for (int idx = 1; idx < clap.length; idx++) {
                if (count < idx - 1) {
                    answer += (idx - 1) - count;
                    count += (idx - 1) - count;
                }
                count += Character.getNumericValue(clap[idx]);
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}
