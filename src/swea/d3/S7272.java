package swea.d3;

import java.util.*;

/**
 * 안경이 없어!
 *
 * 1. 'A' ~ 'Z' 까지 구멍의 개수에 따라 각 value 저장
 * 2. 문자열의 길이가 서로 다르면 DIFF
 * 3. 같다면, 같은 idx의 값을 비교
 * 4. 같은 idx인데 value가 다르면 DIFF
 */
public class S7272 {

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int T;
        T = scan.nextInt();
        scan.nextLine();

        Map<Character, Integer> value = new HashMap<>();
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (ch == 'B') {
                value.put(ch, 2);
            } else if (ch == 'A' || ch == 'D' || ch == 'O' || ch == 'P' || ch == 'Q' || ch == 'R') {
                value.put(ch, 1);
            } else {
                value.put(ch, 0);
            }
        }

        for(int test_case = 1; test_case <= T; test_case++)	{
            String[] split = scan.nextLine().split(" ");

            if (split[0].length() != split[1].length()) {
                System.out.println("#" + test_case + " DIFF");
            } else {
                boolean flag = false;
                for (int row = 0; row < split[0].length(); row++) {
                    if (value.get(split[0].charAt(row)) != value.get(split[1].charAt(row))) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("#" + test_case + " SAME");
                } else {
                    System.out.println("#" + test_case + " DIFF");
                }
            }
        }
    }
}
