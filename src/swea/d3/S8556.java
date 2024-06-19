package swea.d3;

import java.util.*;

/**
 * 북북서
 */
public class S8556 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String str = scan.nextLine();

        }
    }
}

/*
북(dir = 북) = 0
서(dir = 서) = 90
북서 = 북(dir = 서) = 북의 서방향 -> dir - (90/2^n) -> 90 - (90/2^n)
북북서 = 북북(dir = 서) = 북의 북서방향 -> dir - (90/2^n) -> 45 - (90/2^n)

 */