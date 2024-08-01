package swea.d3;

import java.util.*;

/**
 * 문자열 교집합
 *
 * n = 첫번째 집합의 원소 개수
 * m = 두번째 집합의 원소 개수
 * 두 집합에서 공통으로 나오는 문자열 count
 *  -- 입력 된 각 집합의 문자열로 set
 *  -- 교집합을 구한 후 set 의 크기 출력
 */
public class S2948 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            scan.nextLine();
            String[] s1 = scan.nextLine().split(" ");
            String[] s2 = scan.nextLine().split(" ");

            Set<String> set1 = new HashSet<>();
            Set<String> set2 = new HashSet<>();
            for (int i = 0; i < n; i++) {
                set1.add(s1[i]);
            }
            for (int i = 0; i < m; i++) {
                set2.add(s2[i]);
            }

            set1.retainAll(set2);
            System.out.println("#" + tc + " " + set1.size());
        }
    }
}
