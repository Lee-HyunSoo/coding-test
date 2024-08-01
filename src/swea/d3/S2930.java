package swea.d3;

import java.util.*;

/**
 * 힙
 *
 * 1, 1 -> 1번 연산, 1을 최대힙에 push
 * 2 -> 2번 연산, 최대값 출력 후 해당 키 값 삭제
 *  -- 2번 연산을 수행하려는데 힙에 원소가 없으면 -1
 */
public class S2930 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int n = scan.nextInt(); // 연산 수
            scan.nextLine();

            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            System.out.print("#" + tc + " ");
            for (int i = 0; i < n; i++) {
                String[] split = scan.nextLine().split(" ");
                if (split[0].equals("1")) {
                    pq.offer(Integer.parseInt(split[1]));
                } else {
                    int answer = -1;
                    if (!pq.isEmpty()) {
                        answer = pq.poll();
                    }
                    System.out.print(answer + " ");
                }
            }
            System.out.println();
        }
    }
}
