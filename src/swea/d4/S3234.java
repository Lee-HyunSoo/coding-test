package swea.d4;

import java.util.*;

/**
 * 준환이의 양팔저울
 */
public class S3234 {

    static Scanner scan = new Scanner(System.in);
    static int n;
    static int[] arr;

    static int[] permu;
    static boolean[] visit;
    static int total;

    public static void main(String[] args) {
        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            init();

            permutation(0);
            System.out.println("#" + tc + " " + total);
        }
    }

    private static void init() {
        n = scan.nextInt();
        arr = new int[n];
        permu = new int[n];
        visit = new boolean[n];
        total = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
    }

    private static void permutation(int level) {
        if (level == permu.length) {
            subSet(0, 0, 0);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                permu[level] = arr[i];
                permutation(level + 1);
                visit[i] = false;
            }
        }
    }

    private static void subSet(int level, int left, int right) {
        if (left < right) {
            return;
        }

        if (level == n) {
            total++;
            return;
        }

        subSet(level + 1, left + permu[level], right);
        subSet(level + 1, left, right + permu[level]);
    }
}