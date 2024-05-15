package swea.d2;

import java.util.*;

/**
 * 새로운 불면증 치료법
 */
public class S1288 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();

            char[] ch = new char[10];
            for (int i = 1; ; i++) {
                String num = String.valueOf(n * i);
                for (char w : num.toCharArray()) {
                    ch[Character.getNumericValue(w)] = '1';
                }

                boolean flag = false;
                for (int j = 0; j <= 9; j++) {
                    if (ch[j] != '1') {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("#" + c + " " + n * i);
                    break;
                }
            }
        }
    }
}
