package swea.d4;

import java.util.*;

/**
 * 창용 마을 무리의 개수
 *
 * 1. 문제 정리
 * 	1-1. 1~n 까지 번호가 붙어있는 사람이 있다.
 * 	1-2. 이들은 무리를 이루고 있는데, 무리가 총 몇개인가?
 *
 * 2. init
 * 	2-1. 대표자를 관리하는 배열을 초기화한다.
 *
 * 3. find
 * 	3-1. v 가 대표자라면 v 를 return 한다.
 * 	3-2. 대표자가 아니라면, 대표자를 찾으러 가는 길에 있는 모든 v 의 부모를 대표자로 설정한다. (경로압축)
 *
 * 4. union
 * 	4-1. 두 노드의 대표자를 구한다.
 * 	4-2. 두 노드의 대표자가 다르다면, 대표자를 하나로 합쳐준다.
 *
 * 5. 대표자 배열을 set 을 통해 통합한다.
 *  5-1. 경로 압축은 가지 않은 노드에 대해서는 압축되지 않는다.
 * 	5-2. 통합한 대표자 배열의 원소들을  각각 find 를 돌려 set 에 저장한다.
 */
public class S7465 {

    static int n, m;
    static int[] unf;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = scan.nextInt();
            m = scan.nextInt();
            unf = new int[n + 1];
            init();

            for (int idx = 0; idx < m; idx++) {
                int a = scan.nextInt();
                int b = scan.nextInt();
                union(a, b);
            }
            // 5. 대표자 배열을 set 을 통해 통합한다.
            // 5-1. 경로 압축은 가지 않은 노드에 대해서는 압축되지 않는다.
            Set<Integer> answer = new HashSet<>();
            for (int idx = 1; idx <= n; idx++) {
                // 5-2. 통합한 대표자 배열의 원소들을  각각 find 를 돌려 set 에 저장한다.
                answer.add(find(unf[idx]));
            }
            System.out.println("#" + tc + " " + answer.size());
        }
    }

    private static void init() {
        // 2-1. 대표자를 관리하는 배열을 초기화한다.
        for (int idx = 1; idx <= n; idx++) {
            unf[idx] = idx;
        }
    }

    private static int find(int v) {
        // 3-1. v 가 대표자라면 v 를 return 한다.
        if (v == unf[v]) {
            return v;
        }
        // 3-2. 대표자가 아니라면, 대표자를 찾으러 가는 길에 있는 모든 v 의 부모를 대표자로 설정한다. (경로압축)
        return unf[v] = find(unf[v]);
    }

    private static void union(int a, int b) {
        // 4-1. 두 노드의 대표자를 구한다.
        int fa = find(a);
        int fb = find(b);
        // 4-2. 두 노드의 대표자가 다르다면, 대표자를 하나로 합쳐준다.
        if (fa != fb) {
            unf[fa] = fb;
        }
    }
}
