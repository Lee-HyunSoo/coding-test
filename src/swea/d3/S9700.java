package swea.d3;

import java.util.*;

/**
 * USB 꽂기의 미스터리
 */
public class S9700 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            // 한번 = 뒤집어서 해서 안꽂힘 -> 제대로해서 꽂힘 : (1 - p) * q
            // 두번 = 처음제대로해서 안꽂힘 -> 뒤집어서 해서 안꽂힘 -> 처음제대로해서 꽂힘 : p * (1 - q) * q
            double p = scan.nextDouble();
            double q = scan.nextDouble();

            double s1 = (1 - p) * q;
            double s2 = p * (1 - q) * q;
            if (s1 < s2) {
                System.out.println("#" + c + " YES");
            } else {
                System.out.println("#" + c + " NO");
            }
        }
    }
}
