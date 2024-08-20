package baekjoon;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 절대값 힙
 */
public class M11286 {

    static class Pair implements Comparable<Pair> {
        int n, flag; // 음수 0, 양수 1

        public Pair(int n, int flag) {
            this.n = n;
            this.flag = flag;
        }

        @Override
        public int compareTo(Pair p) {
            int n1 = Math.abs(this.n); // -1 -> 1
            int n2 = Math.abs(p.n); // 1 -> 1

            // 0 1
            if (n1 == n2) {
                // 0 - 1 -> 음수 -> 오름차순
                return this.flag - p.flag;
            } else {
                // |-1| |-2| -> 1, 2 -> 1 2
                return n1 - n2;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        while (n-- > 0) {
            int input = scan.nextInt();

            if (input == 0) {
                if (pq.isEmpty()) {
                    System.out.println(0);
                } else {
                    Pair p = pq.poll();
                    System.out.println(p.n);
                }
            } else {
                Pair p;
                if (input > 0) {
                    p = new Pair(input, 1);
                } else {
                    p = new Pair(input, 0);
                }
                pq.offer(p);
            }
        }
    }
}
