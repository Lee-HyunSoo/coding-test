package swea.d3;

import java.util.Scanner;

/**
 * 문자열의 거울상
 */
public class S10804 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String s = scan.nextLine();

            char[] ch = s.toCharArray();
            for (int i = 0; i < ch.length; i++) {
                if (ch[i] == 'q') {
                    ch[i] = 'p';
                } else if (ch[i] == 'b') {
                    ch[i] = 'd';
                } else if (ch[i] == 'd') {
                    ch[i] = 'b';
                } else {
                    ch[i] = 'q';
                }
            }
            String answer = new StringBuilder(String.valueOf(ch)).reverse().toString();
            System.out.println("#" + c + " " + answer);
        }
    }
}
