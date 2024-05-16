package swea.d3;

import java.util.*;

public class S17642 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            long a = scan.nextLong();
            long b = scan.nextLong();
            long diff = Math.abs(a - b);

            System.out.print("#" + c + " ");
            if (a == b) {
                System.out.print(0);
            } else if (a > b || diff == 1) {
                System.out.print(-1);
            } else if (diff % 2 == 0) {
                // 차이가 짝수인 경우 10 -> (2 3 5) (2 3 2 3) (5 5) (2 2 2 2 2) (3 7)
                System.out.print(diff / 2);
            } else {
                // 차이가 홀수인 경우
                System.out.print((diff - 1) / 2);
            }
            System.out.println();
        }
    }
}
