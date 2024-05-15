package swea.d2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class S1966 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();

        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();

            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(scan.nextInt());
            }
            Collections.sort(list);

            System.out.print("#" + c + " ");
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
