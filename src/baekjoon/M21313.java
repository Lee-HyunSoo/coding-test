package baekjoon;

import java.util.*;

/**
 * 문어
 *
 * 1. 문제 정리
 * 	1-1. 문어들은 정월 대보름을 맞아 강강술래를 하려고 한다.
 * 	1-2. 각 문어는 양 옆의 서로 다른 두 문어와 손을 맞잡아 원을 만든다.
 * 	1-3. 문어끼리 손을 잡을 때 지켜야 할 예절이 있다.
 * 		1-3-1. 서로 같은 번호의 손을 잡아야 한다.
 * 		1-3-2. 한 문어와 둘 이상의 손을 잡을 수 없다.
 * 		1-3-3. 한 손으로 여러 문어의 손을 잡을 수 없다.
 *
 * 2. n 이 양수일 때는 1, 2, 1, 2 ... 순으로 손을 잡는다.
 * 3. n 이 홀수일 때는 1, 2, 1, 2 .. 순으로 n - 1 번까지 잡고, 마지막은 3으로 잡는다.
 * 	3-1. 마지막이 1이되면 맨 앞 문어와 같은 수를 쓰기 때문
 *
 */
public class M21313 {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scan.nextInt();

        // 2. n 이 양수일 때는 1, 2, 1, 2 ... 순으로 손을 잡는다.
        if (n % 2 == 0) {
            for (int idx = 1; idx <= n; idx++) {
                if (idx % 2 != 0) {
                    System.out.print(1 + " ");
                } else {
                    System.out.print(2 + " ");
                }
            }
        }
        // 3. n 이 홀수일 때는 1, 2, 1, 2 .. 순으로 n - 1 번까지 잡고, 마지막은 3으로 잡는다.
        else {
            for (int idx = 1; idx < n; idx++) {
                if (idx % 2 != 0) {
                    System.out.print(1 + " ");
                } else {
                    System.out.print(2 + " ");
                }
            }
            // 3-1. 마지막이 1이되면 맨 앞 문어와 같은 수를 쓰기 때문
            System.out.print(3);
        }
    }
}