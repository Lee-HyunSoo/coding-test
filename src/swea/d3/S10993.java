package swea.d3;

import java.util.*;

/**
 * 군주제와 공화제
 */
public class S10993 {

    static class City {
        int x, y, s;

        public City(int x, int y, int s) {
            this.x = x;
            this.y = y;
            this.s = s;
        }
    }

    static class Inf implements Comparable<Inf> {
        int idx;
        double influence;

        public Inf(int idx, double influence) {
            this.idx = idx;
            this.influence = influence;
        }

        @Override
        public int compareTo(Inf inf) {
            return (int) (inf.influence - this.influence);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();

        }
    }
}
