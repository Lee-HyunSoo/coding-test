package swea.d3;

import java.util.*;

/**
 * [S/W 문제해결 기본] 7일차 - 암호생성기
 *
 * 1. 사이클
 *  1-1. 8개의 숫자 입력, 맨 앞수는 1 감소한 뒤 맨 뒤로, 두번째 수는 2 감소한 뒤 맨 뒤로 ...
 *  1-2. 한 사이클 = 5 -> 한 사이클이 지나면 1으로 초기화
 *  1-3. 숫자가 감소한 값이 0 보다 작으면 0으로 한 뒤 맨 뒤로, 프로그램 종료
 */
public class S1225 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        for (int tc = 1; tc <= 10; tc++) {
            int t = scan.nextInt();
            Deque<Integer> dataList = new ArrayDeque<>();

            for (int idx = 0; idx < 8; idx++) {
                dataList.offer(scan.nextInt());
            }

            int cycle = 1;
            while(true) {
                // 1-1. 8개의 숫자 입력, 맨 앞수는 1 감소한 뒤 맨 뒤로, 두번째 수는 2 감소한 뒤 맨 뒤로 ...
                int first = dataList.poll();
                first -= cycle++;

                // 1-2. 한 사이클 = 5 -> 한 사이클이 지나면 1로 초기화
                if (cycle > 5) {
                    cycle = 1;
                }

                // 1-3. 숫자가 감소한 값이 0 보다 작거나 같으면 0으로 한 뒤 맨 뒤로, 프로그램 종료
                if (first <= 0) {
                    first = 0;
                    dataList.add(first);
                    break;
                }
                dataList.add(first);
            }

            System.out.print("#" + tc + " ");
            for (int data : dataList) {
                System.out.print(data + " ");
            }
            System.out.println();
        }
    }
}