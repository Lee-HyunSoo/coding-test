package swea.d3;

import java.util.*;

/**
 * 전봇대
 */
public class S10580 {

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();

            List<Pair> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int a = scan.nextInt();
                int b = scan.nextInt();
                list.add(new Pair(a, b));
            }
            Collections.sort(list, (a, b) -> a.x - b.x);

            int answer = 0;
            for (int i = 0; i < n; i++) {
                Pair pi = list.get(i);
                for (int j = i + 1; j < n; j++) {
                    Pair pj = list.get(j);

                    if (pi.x < pj.x && pj.y < pi.y) {
                        answer++;
                    }
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
