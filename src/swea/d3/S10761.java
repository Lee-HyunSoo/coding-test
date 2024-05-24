package swea.d3;

import java.util.*;

/**
 * 신뢰
 */
public class S10761 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String[] input = scan.nextLine().split(" ");

            List<Integer> bCom = new ArrayList<>();
            List<Integer> oCom = new ArrayList<>();
            for (int i = 1; i < input.length; i += 2) {
                if (input[i].equals("B")) {
                    bCom.add(Integer.parseInt(input[i + 1]));
                } else {
                    oCom.add(Integer.parseInt(input[i + 1]));
                }
            }

            int bIdx = 0, oIdx = 0;
            int b = 1, o = 1, time = 0;
            while (bIdx < bCom.size() || oIdx < oCom.size()) {
                time++;

                if (bIdx < bCom.size()) {
                    if (b < bCom.get(bIdx)) {
                        b++;
                    } else if (b > bCom.get(bIdx)){
                        b--;
                    } else {
                        bIdx++;
                    }
                }

                if (oIdx < oCom.size()) {
                    if (o < oCom.get(oIdx)) {
                        o++;
                    } else if (o > oCom.get(oIdx)) {
                        o--;
                    } else {
                        if (b != o) {
                            oIdx++;
                        }
                    }
                }
            }
            System.out.println("#" + c + " " + time);
        }
    }
}
/*
1초 b 1 0 1
2초 b 2 0 1 0 누름
3초 b 2 0 2 b 누름
4초 b 3 0 2 0 누름
5초 b 4 0 2
6초 b 4 누름
 */
