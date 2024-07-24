package swea.d3;

import java.util.*;

/**
 * 직사각형과 점
 *
 * 직사각형의 네 꼭지점이 주어지고, input 으로 판별해야 할 점이 주어짐
 *
 * 완전히 직사각형 내부의 점, 직사각형의 네 변 중 하나의 위에 있는 점, 직사각형 외부에 있는 점
 *  -- 완전히 내부 : minX < x < maxX && minY < y < maxY
 *  -- 네 변 중 하나 : minX <= x <= maxX && y = minY or maxY
 *  				minY <= y <= maxY && x = minX or maxX
 *  -- 외부의 점 : x < minX or maxX < x || y < minY or maxY < y
 */
public class S6853 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int x1 = scan.nextInt();
            int y1 = scan.nextInt();
            int x2 = scan.nextInt();
            int y2 = scan.nextInt();

            int minX = Math.min(x1, x2);
            int maxX = Math.max(x1, x2);
            int minY = Math.min(y1, y2);
            int maxY = Math.max(y1, y2);

            int n = scan.nextInt();
            int in = 0, side = 0, out = 0;
            for (int i = 0; i < n; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                if (minX < x && x < maxX && minY < y && y < maxY) {
                    in++;
                } else if (minX <= x && x <= maxX && (y == minY || y == maxY)) {
                    side++;
                } else if (minY <= y && y <= maxY && (x == minX || x == maxX)) {
                    side++;
                } else {
                    out++;
                }
            }
            System.out.println("#" + tc + " " + in + " " + side + " " + out);
        }
    }
}