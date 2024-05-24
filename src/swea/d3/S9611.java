package swea.d3;

import java.util.*;

/**
 * 명진이와 동휘의 숫자 맞추기
 */
public class S9611 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            scan.nextLine();

            int[] number = new int[10];
            for (int i = 0; i < n; i++) {
                String[] str = scan.nextLine().split(" ");

                if (str[4].equals("YES")) {
                    for (int j = 0; j < 4; j++) {
                        int idx = Integer.parseInt(str[j]);
                        if (number[idx] != -1) {
                            number[idx]++;
                        }
                    }
                } else {
                    for (int j = 0; j < 4; j++) {
                        int idx = Integer.parseInt(str[j]);
                        number[idx] = -1;
                    }
                }
            }

            int value = -1, answer = 0;
            for (int i = 0; i <= 9; i++) {
                if (value < number[i]) {
                    value = number[i];
                    answer = i;
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
