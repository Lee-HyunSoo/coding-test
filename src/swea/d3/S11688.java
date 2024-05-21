package swea.d3;

import java.util.*;

/**
 * Calkin-Wilf tree 1
 */
public class S11688 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String command = scan.nextLine();

            int a = 1;
            int b = 1;
            for (char ch : command.toCharArray()) {
                if (ch == 'L') {
                    b = a + b;
                } else if (ch == 'R') {
                    a = a + b;
                }
            }
            System.out.println("#" + c + " " + a + " " + b);
        }
    }
}
