package codeplus.math;

import java.util.Scanner;

/**
 * 나머지
 *
 * (A+B)%C는 ((A%C) + (B%C))%C 와 같을까?
 * (A×B)%C는 ((A%C) × (B%C))%C 와 같을까?
 *
 * 첫째 줄에 (A+B)%C, 둘째 줄에 ((A%C) + (B%C))%C, 셋째 줄에 (A×B)%C, 넷째 줄에 ((A%C) × (B%C))%C를 출력
 */
public class M10430 {

    static Scanner scan = new Scanner(System.in);
    static int a, b, c;

    public static void main(String[] args) {
        init();
        mod();
    }

    private static void init() {
        a = scan.nextInt();
        b = scan.nextInt();
        c = scan.nextInt();
    }

    private static void mod() {
        System.out.println((a + b) % c);
        System.out.println(((a % c) + (b % c)) % c);
        System.out.println((a * b) % c);
        System.out.println(((a % c) * (b % c)) % c);
    }
}
