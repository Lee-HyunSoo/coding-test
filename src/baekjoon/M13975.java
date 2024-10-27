package baekjoon;

import java.util.*;

/**
 * 파일 합치기 3
 *
 * 1. 문제정리
 * 	1-1. 두 개의 파일을 합쳐서 하나의 임시파일을 만들고,
 * 	1-2. 이 임시파일이나 원래의 파일을 계속 두 개씩 합쳐서 파일을 합쳐나가고,
 * 	1-3. 최종적으로는 하나의 파일로 합친다.
 * 	1-4. 두 개의 파일을 합칠 때 필요한 비용(시간 등)이 두 파일 크기의 합이라고 가정할 때,
 * 	1-5. 최종적인 한 개의 파일을 완성하는데 필요한 비용의 총 합을 계산
 *
 * 2. min heap 으로 입력을 받는다.
 * 3. 2장씩 뽑아야하므로 Queue 의 크기가 1 초과할 때만 반복문
 * 4. 2장씩 뽑아 더하고, 다시 Queue 에 추가한다.
 */
public class M13975 {

    static Scanner scan = new Scanner(System.in);
    static int k;
    static Queue<Long> pages;
    static long answer;

    public static void main(String[] args) {
        int t = scan.nextInt();
        while(t-- > 0) {
            init();
            union();
            System.out.println(answer);
        }
    }

    private static void init() {
        k = scan.nextInt();
        // 2. min heap 으로 입력을 받는다.
        pages = new PriorityQueue<>();
        for (int idx = 0; idx < k; idx++) {
            pages.offer(scan.nextLong());
        }
        answer = 0;
    }

    private static void union() {
        // 3. 2장씩 뽑아야하므로 Queue 의 크기가 1 초과할 때만 반복문
        while(pages.size() > 1) {
            long first = pages.poll();
            long second = pages.poll();
            // 4. 2장씩 뽑아 더하고, 다시 Queue 에 추가한다.
            long result = first + second;
            answer += result;
            pages.offer(result);
        }
    }
}
