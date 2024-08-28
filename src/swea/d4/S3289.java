package swea.d4;

import java.util.*;

/**
 * 서로소 집합
 *
 * 1. 문제 정리
 * 	1-1. 0 이 입력되면 union
 * 	1-2. 1 이 입력되면 find
 *
 * 2. find
 * 	2-1. v 가 대표자라면 v 를 return 한다.
 * 	2-2. 대표자가 아니라면, 대표자를 찾으러 가는 길에 있는 모든 v 의 부모를 대표자로 설정한다. (경로압축)
 *
 * 3. union
 * 	3-1. 두 노드의 대표자를 구한다.
 * 	3-2. 두 노드의 대표자가 다르다면, 대표자를 하나로 합쳐준다.
 */
public class S3289 {

    static int[] unf;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            unf = new int[n + 1];
            for (int idx = 1; idx <= n; idx++) {
                unf[idx] = idx;
            }

            StringBuilder answer = new StringBuilder();
            for (int idx = 0; idx < m; idx++) {
                int command = scan.nextInt();
                int a = scan.nextInt();
                int b = scan.nextInt();

                if (command == 0) {
                    if (find(a) != find(b)) {
                        union(a, b);
                    }
                } else {
                    if (find(a) == find(b)) {
                        answer.append("1");
                    } else {
                        answer.append("0");
                    }
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static int find(int v) {
        // 2-1. v 가 대표자라면 v 를 return 한다.
        if (v == unf[v]) {
            return v;
        }
        // 2-2. 대표자가 아니라면, 대표자를 찾으러 가는 길에 있는 모든 v 의 부모를 대표자로 설정한다. (경로압축)
        return unf[v] = find(unf[v]);
    }

    private static void union(int a, int b) {
        // 3-1. 두 노드의 대표자를 구한다.
        int fa = find(a);
        int fb = find(b);
        // 3-2. 두 노드의 대표자가 다르다면, 대표자를 하나로 합쳐준다.
        if (fa != fb) {
            unf[fa] = fb;
        }
    }
}
