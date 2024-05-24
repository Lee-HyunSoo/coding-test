package swea.d3;

import java.util.*;

/**
 * 유효기간
 */
public class S10059 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String input = scan.nextLine();

            int n1 = Integer.parseInt(input.substring(0, 2));
            int n2 = Integer.parseInt(input.substring(2));

            boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false;
            if (n1 == 0 || n1 > 12) { // n1 == YY
                flag1 = true;
            }
            if (n2 == 0 || n2 > 12) { // n2 == YY
                flag2 = true;
            }
            if (0 < n1 && n1 <= 12) { // n1 == MM
                flag3 = true;
            }
            if (0 < n2 && n2 <= 12) { // n2 == MM
                flag4 = true;
            }

            String answer = "AMBIGUOUS";
            if (flag1 && flag2) {
                answer = "NA";
            } else if (flag1 && flag4) {
                answer = "YYMM";
            } else if (flag2 && flag3) {
                answer = "MMYY";
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
