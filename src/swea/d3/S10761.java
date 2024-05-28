package swea.d3;

import java.util.*;

/**
 * 신뢰
 */
public class S10761 {

    static class Pair {
        String s;
        int idx;
        int dest;

        public Pair(String s, int idx, int dest) {
            this.s = s;
            this.idx = idx;
            this.dest = dest;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String[] input = scan.nextLine().split(" ");
        }
    }
}

/*
1초 : B 2 O 1 - O 누름
2초 : B 2 O 1 - B 누름
3초 : B 3 O 2 - O 누름
4초 : B 4 O 2
5초 : B 4 O 2 - B 누름
*/
