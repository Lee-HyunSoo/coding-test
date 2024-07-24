package swea.d3;

import java.util.*;

/**
 * 2016년 요일 맞추기
 *
 * 2016년 1월 1일은 금요일, 2016년은 윤년 -> 2월 29일 포함
 * 2016년의 m월 d일?
 *  -- 월별 날짜 배열
 *  -- 날짜 % 7 == 1 이면 금요일 (4)
 *  -- (0,3), (1,4), (2,5), (3,6), (4,0), (5,1), (6,2)
 */
public class S5515 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Integer> month = Arrays.asList(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        List<Integer> dayOfWeek = Arrays.asList(3, 4, 5, 6, 0, 1, 2);

        int t = scan.nextInt();
        scan.nextLine();
        for (int tc = 1; tc <= t; tc++) {
            int m = scan.nextInt();
            int d = scan.nextInt();

            int day = d;
            for (int idx = 0; idx < m - 1; idx++) {
                day += month.get(idx);
            }

            int answer = dayOfWeek.get((day % 7));
            System.out.println("#" + tc + " " + answer);
        }
    }
}