package swea.d3;

import java.util.*;

/**
 * 이진수 표현
 */
public class S10726 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            int m = scan.nextInt();

            List<Integer> list = new ArrayList<>();
            while (m > 0) {
                list.add(m % 2);
                m /= 2;
            }

            String answer = "ON";
            if (list.size() >= n) {
                for (int i = 0; i < n; i++) {
                    if (list.get(i) == 0) {
                        answer = "OFF";
                        break;
                    }
                }
            } else {
                answer = "OFF";
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
