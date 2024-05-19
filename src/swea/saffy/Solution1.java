package swea.saffy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution1 {

    static class Pair implements Comparable<Pair> {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int sum() {
            return this.x + this.y;
        }

        @Override
        public int compareTo(Pair p) {
            if (this.x == p.x) {
                return this.y - p.y;
            }
            return this.x - p.x;
        }
    }

    static int[] per;
    static int[] arr;
    static List<Pair> answer;
    static int n, k;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            n = scan.nextInt();
            k = scan.nextInt();

            per = new int[2];
            arr = new int[n];
            answer = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                arr[i] = scan.nextInt();
            }

            dfs(0);
            Collections.sort(answer);
            System.out.println("#" + c + " " + answer.get(k - 1).sum());
        }
    }

    public static void dfs(int l) {
        if (l == 2) {
            answer.add(new Pair(per[0], per[1]));
            return;
        }

        for (int i = 0; i < n; i++) {
            per[l] = arr[i];
            dfs(l + 1);
        }
    }
}

/*
3
3 7
3 2 3
5 6
1 2 3 4 5
5 10
3 2 1 1 3
 */