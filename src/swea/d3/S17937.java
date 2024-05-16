package swea.d3;

import java.util.*;

/**
 * 큰 수의 최대공약수
 */
public class S17937 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String s = scan.nextLine();
            String[] split = s.split(" ");

            if (split[0].equals(split[1])) {
                System.out.println("#" + c + " " + split[0]);
            } else {
                System.out.println("#" + c + " " + 1);
            }
        }
    }

//    public static long gcd(long a, long b) {
//        if (b == 0) {
//            return a;
//        } else {
//            return gcd(b, a % b);
//        }
//        // lcm = a * b / gcd(a, b)
//    }
}
