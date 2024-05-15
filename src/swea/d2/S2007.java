package swea.d2;

import java.util.Scanner;

/**
 * 패턴 마디의 길이
 */
public class S2007 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int i = 1; i <= t; i++) {
            String str = scan.nextLine();
            for (int j = 1; j < str.length(); j++) {
                if (str.substring(0, j).equals(str.substring(j, j * 2))) {
                    System.out.println("#" + i + " " + j);
                    break;
                }
            }
        }
    }
}
