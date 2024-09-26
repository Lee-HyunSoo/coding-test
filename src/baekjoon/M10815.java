package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 숫자 카드
 *
 * 1. 입력 배열을 정렬한다.
 * 2. 이진탐색으로 값이 있으면 1, 없으면 0 을 넣는다.
 */
public class M10815 {

    static Scanner scan = new Scanner(System.in);
    static int n, m;
    static int[] cards, numbers;
    static StringBuilder answer;

    private static void init() {
        n = scan.nextInt();
        cards = new int[n];
        for (int idx = 0; idx < n; idx++) {
            cards[idx] = scan.nextInt();
        }
        // 1. 입력 배열을 정렬한다.
        Arrays.sort(cards);

        m = scan.nextInt();
        numbers = new int[m];
        for (int idx = 0; idx < m; idx++) {
            numbers[idx] = scan.nextInt();
        }

        answer = new StringBuilder();
    }

    private static void binarySearch(int target) {
        int start = 0;
        int end = cards.length - 1;

        while(start <= end) {
            int mid = (start + end) / 2;

            if (cards[mid] < target) {
                start = mid + 1;
            } else if (cards[mid] > target) {
                end = mid - 1;
            } else if (cards[mid] == target) {
                // 2. 이진탐색으로 값이 있으면 1, 없으면 0 을 넣는다.
                answer.append(1).append(" ");
                return;
            }
        }
        answer.append(0).append(" ");
    }

    public static void main(String[] args) {
        init();
        for (int number : numbers) {
            binarySearch(number);
        }
        System.out.println(answer);
    }
}