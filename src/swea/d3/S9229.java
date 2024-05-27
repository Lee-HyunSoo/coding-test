package swea.d3;

import java.util.*;

/**
 * 한빈이와 Spot Mart
 */
public class S9229 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            int m = scan.nextInt();

            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(scan.nextInt());
            }
            Collections.sort(list);

            int start = 0;
            int end = list.size() - 1;
            int answer = 0;
            while (start != end) {
                int sum = list.get(start) + list.get(end);
                if (sum > m) {
                    end--;
                } else {
                    start++;
                    answer = Math.max(answer, sum);
                }
            }

            if (answer == 0) {
                answer = -1;
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
