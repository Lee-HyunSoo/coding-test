package baekjoon;

import java.util.*;

/**
 * 듣보잡
 *
 * 1. 문제 정리
 *  1-1. 듣도 못한 사람의 명단과, 보도 못한 사람의 명단이 주어질 때, 듣도 보도 못한 사람의 명단을 구하는 프로그램을 작성하시오.
 *
 * 2. 두 집합의 교집합을 구한다.
 */
public class M1764 {

    static Scanner scan = new Scanner(System.in);
    static int n, m;
    static Set<String> listen, look;

    public static void main(String[] args) {
        init();
        System.out.println(listen.size());
        for (String s : listen) {
            System.out.println(s);
        }
    }

    private static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        scan.nextLine();
        listen = new TreeSet<>();
        for (int idx = 0; idx < n; idx++) {
            listen.add(scan.nextLine());
        }
        look = new TreeSet<>();
        for (int idx = 0; idx < m; idx++) {
            look.add(scan.nextLine());
        }
        listen.retainAll(look);
    }
}
