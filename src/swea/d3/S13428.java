package swea.d3;

import java.util.*;

/**
 * 숫자 조작
 */
public class S13428 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String n = scan.nextLine();
            if (n.equals("0")) {
                System.out.println("#" + c + " " + 0 + " " + 0);
                continue;
            }

            int min = Integer.MAX_VALUE, max = 0;
            for (int i = 0; i < n.length(); i++) {
                for (int j = 0; j < n.length(); j++) {
                    char[] ch = n.toCharArray();
                    char tmp = ch[i];
                    ch[i] = ch[j];
                    ch[j] = tmp;

                    if (ch[0] != '0') {
                        int num = Integer.parseInt(String.valueOf(ch));
                        if (min > num) {
                            min = num;
                        }
                        if (max < num) {
                            max = num;
                        }
                    }
                }
            }
            System.out.println("#" + c + " " + min + " " + max);
        }
    }
}
