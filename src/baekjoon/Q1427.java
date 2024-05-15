package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 소트인사이드
 */
public class Q1427 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        char[] num = String.valueOf(n).toCharArray();
        Arrays.sort(num);
        String answer = new StringBuilder(String.valueOf(num)).reverse().toString();
        System.out.println(answer);

//        String num = String.valueOf(n);
//        List<Integer> result = new ArrayList<>();
//        for (char c : num.toCharArray()) {
//            result.add(Character.getNumericValue(c));
//        }
////        Collections.sort(result, Collections.reverseOrder());
//        Collections.sort(result, (a, b) -> b - a);
//        for (int i : result) {
//            System.out.print(i);
//        }
    }
}
