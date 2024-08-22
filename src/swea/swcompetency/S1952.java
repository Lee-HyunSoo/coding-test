package swea.swcompetency;

import java.util.Scanner;

/**
 * 수영장
 *
 * 1. 문제 정리
 *  1-1. 수영장을 이용할 1 ~ 12월 까지의 계획표가 주어진다.
 *  1-2. 1일 이용권, 1달 이용권, 3달 이용권, 1년 이용권이 주어진다.
 *  1-3. 이용권을 사용해서 가장 싸게 이용할 수 있게 하자.
 *
 * 2. 일일권만 사용 / 한달권만 사용 / 일일권 + 한달권 사용 / 일일권 + 한달권 + 세달권 사용 / 1년권 사용 으로 구별해 구할 수 있다.
 *  2-1. 우선 초기 결과를 (일일권만 사용, 1달권만 사용, 1년권 사용) 셋 중 가장 최소값으로 구해둔다.
 *  2-2. 첫 재귀는 각 달에 일일권만 사용했을 때, 한달권을 사용했을 때 중 최소값으로 더해나간다.
 *  2-3. 두번째 재귀는 삼개월 권을 사용했을 경우이다. 때문에 idx 를 3씩 올린다.
 *  2-4. 만약 idx 가 12가 넘어가면 1년이 끝났다는 뜻, answer 와 sum 을 비교해 최소값을 찾는다.
 */
public class S1952 {

    static int[] price;
    static int[] month;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            price = new int[4];
            month = new int[12];

            for (int idx = 0; idx < 4; idx++) {
                price[idx] = scan.nextInt();
            }

            // 2. 일일권만 사용 / 한달권만 사용 / 일일권 + 한달권 사용 / 일일권 + 한달권 + 세달권 사용 / 1년권 사용 으로 구별해 구할 수 있다.
            int useDay = 0, useMonth = 0;
            for (int idx = 0; idx < 12; idx++) {
                month[idx] = scan.nextInt();
                useDay += month[idx];

                if (month[idx] > 0) {
                    useMonth++;
                }
            }
            useDay *= price[0];
            useMonth *= price[1];

            // 2-1. 우선 초기 결과를 (일일권만 사용, 1달권만 사용, 1년권 사용) 셋 중 가장 최소값으로 구해둔다.
            answer = Math.min(Math.min(useDay, useMonth), price[3]);

            plan(0, 0);
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void plan(int idx, int sum) {
        // 2-4. 만약 idx 가 12가 넘어가면 1년이 끝났다는 뜻, answer 와 sum 을 비교해 최소값을 찾는다.
        if (idx >= 12) {
            answer = Math.min(answer, sum);
            return;
        }

        // 2-2. 첫 재귀는 각 달에 일일권만 사용했을 때, 한달권을 사용했을 때 중 최소값으로 더해나간다.
        plan(idx + 1, sum + (Math.min(month[idx] * price[0], price[1])));
        // 2-3. 두번째 재귀는 삼개월권을 사용했을 경우이다. 때문에 idx 를 3씩 올린다.
        plan(idx + 3, sum + price[2]);
    }
}
