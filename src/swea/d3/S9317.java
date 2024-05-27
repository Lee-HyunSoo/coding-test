package swea.d3;

import java.util.*;

/**
 * 석찬이의 받아쓰기
 */
public class S9317 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scan.nextInt();
            scan.nextLine();
            String str1 = scan.nextLine(); // 따라 적어야하는 문자열
            String str2 = scan.nextLine(); // 따라 적은 문자

            int answer = 0;
            for (int j = 0; j < n; j++) {
                if (str1.charAt(j) == str2.charAt(j)) {
                    answer++;
                }
            }
            System.out.println("#" + c + " " + answer);
        }
    }
}
