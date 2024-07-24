package swea.d3;

import java.util.*;

/**
 * 다솔이의 다이아몬드 장식
 *
 * 1, 5줄 : (..) + (#...) * len - 1 + (#..)
 * 2, 4줄 : (.#) * (len * 2) + (.)
 * 3줄 : [#. + (A) + (.)] + (#)
 */
public class S4751 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int tc = 1; tc <= t; tc++) {
            String input = scan.nextLine();

            StringBuilder sb1 = new StringBuilder();
            sb1.append("..");
            for (int idx = 0; idx < input.length() - 1; idx++) {
                sb1.append("#...");
            }
            sb1.append("#..");

            StringBuilder sb2 = new StringBuilder();
            for (int idx = 0; idx < input.length() * 2; idx++) {
                sb2.append(".#");
            }
            sb2.append(".");

            StringBuilder sb3 = new StringBuilder();
            for (int idx = 0; idx < input.length(); idx++) {
                sb3.append("#.").append(input.charAt(idx)).append(".");
            }
            sb3.append("#");

            System.out.println(sb1.toString());
            System.out.println(sb2.toString());
            System.out.println(sb3.toString());
            System.out.println(sb2.toString());
            System.out.println(sb1.toString());
        }
    }
}
