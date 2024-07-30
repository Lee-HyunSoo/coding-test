package swea.d3;

import java.util.*;

/**
 * 직사각형 길이 찾기
 *
 * set 사용, 없으면 넣고 있으면 remove
 * 마지막에 남아있는 놈이 필요한 변
 */
public class S3456 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {

            Set<Integer> square = new HashSet<>();
            for (int i = 0; i < 3; i++) {
                int input = scan.nextInt();
                if (square.contains(input)) {
                    square.remove(input);
                } else {
                    square.add(input);
                }
            }
            for (int s : square) {
                System.out.println("#" + tc + " " + s);
            }
        }
    }
}