package swea.d2;

import java.util.*;

/**
 * 수도 요금 경쟁
 */
public class S1284 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int p = scan.nextInt(); // a사 요금, 1리터 당 내야함
            int q = scan.nextInt(); // b사 기본요금
            int r = scan.nextInt(); // b사 기본 제공 물
            int s = scan.nextInt(); // 기본요금 q 이상 사용 시 1리터당 s
            int w = scan.nextInt(); // 한달 간 사용한 물

            int a = p * w;
            int b = 0;
            if (r >= w) {
                b = q;
            } else {
                b = q + (w - r) * s;
            }
            int answer = Math.min(a, b);
            System.out.println("#" + c + " " + answer);
        }
    }
}
