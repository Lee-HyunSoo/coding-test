package swea.d2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 중간 평균값 구하기
 */
public class S1984 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();

        for (int i = 1; i <= t; i++) {

            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                list.add(scan.nextInt());
            }
            Collections.sort(list);

            int sum = 0;
            for (int j = 1; j < 9; j++) {
                sum += list.get(j);
            }
            int answer = (int) Math.round((double)sum / 8);
            System.out.println("#" + i + " " + answer);
        }
    }
}
