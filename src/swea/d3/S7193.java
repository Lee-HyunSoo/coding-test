package swea.d3;

import java.util.*;

/**
 * 승현이의 수학공부
 *
 * 아래 주석 된 메서드처럼 각 자리별로 더한 후 계산하면 시간초과
 * input 으로 입력 된 값을 각각 더하고, (n - 1) 로 나머지 구하면 됨
 *  -- 9, 234 -> (2 + 3 + 4) % (9 - 1)
 */
public class S7193 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int tc = 1; tc <= t; tc++) {
            String[] split = scan.nextLine().split(" ");

            int answer = 0;
            for (char ch : split[1].toCharArray()) {
                answer += Character.getNumericValue(ch);
            }
            answer %= (Integer.parseInt(split[0]) - 1);
            System.out.println("#" + tc + " " + answer);
        }
    }

//	private static int calc(int base, String num) {
//		char[] ch = new StringBuilder(num).reverse().toString().toCharArray();
//
//		int sum = 0;
//		for (int i = 0; i < ch.length; i++) {
//			sum += Character.getNumericValue(ch[i]) * (int) Math.pow(base, i) % (base - 1);
//		}
//		return sum % (base - 1);
//	}
}