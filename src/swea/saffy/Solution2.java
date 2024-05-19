package swea.saffy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 모자
 */
public class Solution2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            List<Integer> peo = new ArrayList<>();
            List<Integer> hat = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                peo.add(scan.nextInt());
            }
            for (int i = 0; i < n; i++) {
                hat.add(scan.nextInt());
            }
            Collections.sort(peo, Collections.reverseOrder());
            Collections.sort(hat, Collections.reverseOrder());

            int x = 0, y = 0;
            int answer = 0;
            while (x < n && y < n) {
                int diff = Math.abs(peo.get(x) - hat.get(y));
                if (diff <= 3) {
                    answer++;
                    x++;
                    y++;
                } else {
                    if (hat.get(y) > peo.get(x)) {
                        y++;
                    } else {
                        x++;
                    }
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}

/*
3
3
4 8 2
6 10 7
4
1 2 3 4
4 3 2 1
6
5 8 3 4 2 1
6 3 7 9 5 2
 */