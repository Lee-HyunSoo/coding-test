package swea.d3;

import java.util.*;

public class S19185 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            scan.nextLine();

            String[] sYear = new String[n + 1];
            String[] tYear = new String[m + 1];
            String[] s1 = scan.nextLine().split(" ");
            String[] s2 = scan.nextLine().split(" ");

            for (int i = 1; i <= n; i++) {
                sYear[i] = s1[i - 1];
            }
            for (int i = 1; i <= m; i++) {
                tYear[i] = s2[i - 1];
            }

            int q = scan.nextInt();
            System.out.print("#" + c + " ");
            for (int i = 0; i < q; i++) {
                int num = scan.nextInt();

                String answer = "";
                if (num % n == 0) {
                    answer += sYear[n];
                } else {
                    answer += sYear[num % n];
                }
                if (num % m == 0) {
                    answer += tYear[m];
                } else {
                    answer += tYear[num % m];
                }
                System.out.print(answer + " ");
            }
            System.out.println();
        }
    }
}
