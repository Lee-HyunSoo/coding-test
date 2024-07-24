package swea.saffy.d1;

import java.util.Scanner;

/**
 * 1대1 가위바위보
 */
public class D1Q3 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] s = scan.nextLine().split(" ");

        if (s[0].equals("1") && s[1].equals("2")) {
            System.out.println("B");
        } else if (s[0].equals("1") && s[1].equals("3")) {
            System.out.println("A");
        } else if (s[0].equals("2") && s[1].equals("1")) {
            System.out.println("A");
        } else if (s[0].equals("2") && s[1].equals("3")) {
            System.out.println("B");
        } else if (s[0].equals("3") && s[1].equals("1")) {
            System.out.println("B");
        } else {
            System.out.println("A");
        }
    }
}
