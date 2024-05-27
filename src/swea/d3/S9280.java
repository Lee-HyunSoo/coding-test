package swea.d3;

import java.util.*;

/**
 * 진용이네 주차타워
 */
public class S9280 {

    static class Space {
        int car, price; // 주차중인 차, 주차장 가격

        public Space(int car, int price) {
            this.car = car;
            this.price = price;
        }
    }

    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt(); // n개의 주차공간
            int m = scan.nextInt(); // m대의 차량

            List<Space> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int price = scan.nextInt(); // 각 공간의 요금
                list.add(new Space(-1, price));
            }

            int[] cars = new int[m + 1];
            for (int i = 1; i <= m; i++) {
                cars[i] = scan.nextInt(); // 각 차량의 무게
            }

            Queue<Integer> q = new LinkedList<>();
            Queue<Integer> wait = new LinkedList<>();
            for (int i = 0; i < m * 2; i++) {
                q.offer(scan.nextInt());
            }

            answer = 0;
            while (!q.isEmpty()) {
                int carIdx = q.poll();

                boolean flag = false;
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).car == -1) {
                        flag = true;
                        break;
                    }
                }

                if (!flag && carIdx > 0) {
                    wait.offer(carIdx);
                    continue;
                }

                if (carIdx > 0) { // 주차
                    inCar(list, cars, carIdx);
                } else { // 출차
                    outCar(list, carIdx);

                    if (!wait.isEmpty()) {
                        int waitCar = wait.poll();
                        inCar(list, cars, waitCar);
                    }
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }

    private static void inCar(List<Space> list, int[] cars, int carIdx) {
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j).car == -1) {
                answer += (list.get(j).price * cars[carIdx]);
                list.get(j).car = carIdx;
                break;
            }
        }
    }

    private static void outCar(List<Space> list, int carIdx) {
        carIdx = Math.abs(carIdx);
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j).car == carIdx) {
                list.get(j).car = -1;
                break;
            }
        }
    }
}
