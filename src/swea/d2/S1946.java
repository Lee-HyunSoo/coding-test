package swea.d2;

import java.util.*;

/**
 * 간단한 압축 풀기
 */
public class S1946 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();

            List<List<String>> answer = new ArrayList<>();
            List<String> result = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String w = scan.next();
                int num = scan.nextInt();

                for (int j = 0; j < num; j++) {
                    result.add(w);
                    if (result.size() == 10) {
                        answer.add(result);
                        result = new ArrayList<>();
                    }
                }
            }
            answer.add(result);

            System.out.println("#" + c);
            for (List<String> list : answer) {
                for (String s : list) {
                    System.out.print(s);
                }
                System.out.println();
            }
        }
    }
}
