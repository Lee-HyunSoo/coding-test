package swea.d2;

import java.util.*;

/**
 * 가랏! RC카!
 */
public class S1940 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();

            int answer = 0;
            int speed = 0, accel = 0;
            for (int i = 0; i < n; i++) {
                int command = scan.nextInt();
                if (command != 0) {
                    accel = scan.nextInt();
                }

                if (command == 1) {
                    speed += accel;
                } else if (command == 2) {
                    speed -= accel;
                    if (speed < 0) {
                        speed = 0;
                    }
                }
                answer += speed;
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
