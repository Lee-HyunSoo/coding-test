package baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * 스택 수열
 */
public class Q1874 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        Stack<Integer> s = new Stack<>();
        List<Character> answer = new ArrayList<>();

        /*
        1 +
        2 +
        3 +
        4 +
        4 - [ 1 2 3 ]
        3 - [ 1 2 ]
        5 +
        6 +
        6 - [ 1 2 5 ]
        7 +
        8 +
        8 - [ 1 2 5 7 ]
        7 - [ 1 2 5 ]
        5 - [ 1 2 ]
        2 - [ 1 ]
        1 - [ ]

        1
        1 -
        2
        2 -
        3 +
        4 +
        5 +
        5 -
        3 [ 3 4 ]
         */

        int num = 1;
        for (int i = 0; i < n; i++) {
            int input = scan.nextInt(); // 3

            while (num <= input) { // 5 <= 3
                s.push(num++); // 1 2 3 4
                answer.add('+');
            }

            if (!s.isEmpty() && s.peek() == input) {
                s.pop();
                answer.add('-');
            }
            // 1 2 3

            if (!s.isEmpty() && s.peek() > input) {
                System.out.println("NO");
                return;
            }
        }

        for (char c : answer) {
            System.out.println(c);
        }
    }
}
