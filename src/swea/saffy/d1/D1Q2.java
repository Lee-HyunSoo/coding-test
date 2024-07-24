package swea.saffy.d1;

import java.util.Scanner;

/**
 * 자릿수 더하기
 */
public class D1Q2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        int answer = 0;
        for (char c : input.toCharArray()) {
            answer += Character.getNumericValue(c);
        }
        System.out.println(answer);
    }
}
