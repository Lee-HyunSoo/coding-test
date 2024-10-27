package baekjoon;

import java.util.*;

/**
 * LCS
 *
 * 1. 문제 정리
 *  1-1. LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때,
 *  1-2. 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.
 *  1-3. 예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.
 *
 * 2. 기저조건
 * 	2-1. 파라미터가 0보다 작아지면 (index 범위밖이면) return 0
 * 	2-2. 이미 계산 된 곳이면 return 계산된 값
 *
 * 3. dp 조건
 * 	3-1. 두 글자가 같으면 두 인덱스를 다 줄이고, 연산결과 + 1
 * 	3-2. 두 글자가 다르면 한쪽 인덱스씩 줄이고, 그 중 큰 결과를 연산결과에 저장
 */
public class M9251 {

    static Scanner scan = new Scanner(System.in);
    static String s1, s2;
    static int[][] memo;

    public static void main(String[] args) {
        init();
        int answer = topDown(s1.length() - 1, s2.length() - 1);
        System.out.println(answer);
    }

    private static void init() {
        s1 = scan.nextLine();
        s2 = scan.nextLine();
        memo = new int[s1.length()][s2.length()];
        for (int row = 0; row < s1.length(); row++) {
            for (int col = 0; col < s2.length(); col++) {
                memo[row][col] = -1;
            }
        }
    }

    private static int topDown(int idx1, int idx2) {
        // 2. 기저조건
        // 2-1. 파라미터가 0보다 작아지면 (index 범위밖이면) return 0
        if (idx1 < 0 || idx2 < 0) {
            return 0;
        }
        // 2-2. 이미 계산 된 곳이면 return 계산된 값
        if (memo[idx1][idx2] != -1) {
            return memo[idx1][idx2];
        }

        // 3. dp 조건
        if (s1.charAt(idx1) == s2.charAt(idx2)) {
            // 3-1. 두 글자가 같으면 두 인덱스를 다 줄이고, 연산결과 + 1
            memo[idx1][idx2] = topDown(idx1 - 1, idx2 - 1) + 1;
        } else {
            // 3-2. 두 글자가 다르면 한쪽 인덱스씩 줄이고, 그 중 큰 결과를 연산결과에 저장
            memo[idx1][idx2] = Math.max(topDown(idx1 - 1, idx2), topDown(idx1, idx2 - 1));
        }
        return memo[idx1][idx2];
    }
}

