package swea.d3;

import java.util.*;

/**
 * 두문자어
 */
public class S8741 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String[] split = scan.nextLine().split(" ");
            String answer = "";
            answer += Character.toUpperCase(split[0].charAt(0));
            answer += Character.toUpperCase(split[1].charAt(0));
            answer += Character.toUpperCase(split[2].charAt(0));
            System.out.println("#" + c + " " + answer);
        }
    }
}
