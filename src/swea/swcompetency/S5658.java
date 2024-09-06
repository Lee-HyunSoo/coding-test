package swea.swcompetency;

import java.util.*;

/**
 * 보물상자 비밀번호
 *
 * 1. 문제 정리
 * 	1-1. 4의 배수 n 이 들어온다.
 * 	1-2. n / 4 만큼 문자열을 회전한다.
 * 	1-3. 회전한 문자열을 (n / 4) 크기씩 잘라 비밀번호를 만든다.
 *
 * 2. 문자열 회전
 * 	2-1. 비밀번호의 마지막 글자를 저장한다.
 * 	2-2. 비밀번호의 마지막을 지운다.
 * 	2-3. StringBuilder 를 통해 마지막 글자 + 비밀번호를 더해 새 비밀번호를 만든다.
 * 	2-4. 새 비밀번호를 (n / 4) 씩 자르며, set 에 추가한다.
 * 	2-5. set 을 list 로 변경 후 정렬한다.
 * 	2-6. 정렬한 list 의 k 번째 값을 출력한다.
 */
public class S5658 {

    static Scanner scan = new Scanner(System.in);
    static int n, k;
    static StringBuilder pw;
    static Set<Integer> pwSet;
    static List<Integer> pwList;

    public static void main(String[] args) {
        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            init();
            getNewPw();
            sortPwList();
            // 2-6. 정렬한 list 의 k 번째 값을 출력한다.
            System.out.println("#" + tc + " " + pwList.get(k - 1));
        }
    }

    private static void init() {
        n = scan.nextInt();
        k = scan.nextInt();
        scan.nextLine();
        String input = scan.nextLine();

        pw = new StringBuilder(input);
        pwSet = new HashSet<>();
    }

    private static void getNewPw() {
        for (int initStart = 0; initStart < (n / 4); initStart++) {
            StringBuilder newPw = new StringBuilder();
            // 2-1. 비밀번호의 마지막 글자를 저장한다.
            char end = pw.charAt(n - 1);
            // 2-2. 비밀번호의 마지막을 지운다.
            pw.deleteCharAt(n - 1);
            // 2-3. StringBuilder 를 통해 마지막 글자 + 비밀번호를 더해 새 비밀번호를 만든다.
            newPw.append(end).append(pw);

            setPwSet(newPw);
            pw = newPw;
        }
    }

    private static void setPwSet(StringBuilder pw) {
        int start = 0;
        int end = start + (n / 4);
        // 2-4. 새 비밀번호를 (n / 4) 씩 자르며, set 에 추가한다.
        while (start < n) {
            String sub = pw.substring(start, end);
            pwSet.add(Integer.parseInt(sub, 16));
            start = end;
            end = start + (n / 4);
        }
    }

    private static void sortPwList() {
        // 2-5. set 을 list 로 변경 후 정렬한다.
        pwList = new ArrayList<>(pwSet);
        pwList.sort((a, b) -> b - a);
    }
}