package swea.swcompetency;

import java.util.*;

/**
 * 보호 필름
 *
 * 1. 문제 정리
 * 	1-1. k 가 1인 경우 어떻게 해도 검증이 성공하기 때문에, 아무것도 바꾸지 않아도 된다.
 * 	1-2. k 가 1이 아닌 경우만 부분집합을 구해, 검증해본다.
 *
 * 2. row 에 대한 부분집합을 구한다.
 * 	2-1. 이 때, 현재 answer 가 새로구한 부분집합의 크기보다 작으면 더이상 진행하지 않는다.
 * 	2-2. 같거나 더 큰 경우 연산을 진행하는 것이 의미가 없기 때문이다. (제일 작은 부분집합의 크기를 찾고있기 때문)
 * 	2-3. 부분집합의 크기가 더 작다면, 해당 부분집합의 부분집합을 구한다.
 *
 * 3. 부분집합에 대한 부분집합을 구한다.
 * 	3-1. 색칠 전 기존 배열을 복사한다.
 * 	3-2. 칠해야되는 색의 종류는 두 종류이다.
 * 	3-3. 선택 된 부분집합의 부분집합은 a 색, 선택되지 않은 것은 b로 칠한다.
 * 	3-4. 색을 칠한 이후 세로로 탐색하며, 검증한다.
 *
 * 4. 검증 후 갱신한다.
 * 	4-1. 2개의 count 변수를 활용한다.
 * 	4-2. 현재 위치가 1이면 count1++, count2 = 0
 * 	4-3. 현재 위치가 0이면 count2++, count1 = 0
 * 	4-4. 만약 count1 또는 count2 가 k보다 크거나 같으면 break, 다음 col로 이동한다.
 * 	4-5. 만약 탐색 종료 후 count1 과 count2 둘다 k 보다 작으면 검증 실패, return 한다.
 * 	4-6. 검증이 실패하지 않았다면 answer 를 갱신한다.
 */
public class S2112 {

    static int d, w, k;
    static int[][] graph;
    static List<Integer> set;
    static int[][] film;
    static boolean[] color;
    static int answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int tc = 1; tc <= t; tc++) {
            d = scan.nextInt();
            w = scan.nextInt();
            k = scan.nextInt();
            graph = new int[d][w];
            set = new ArrayList<>();
            answer = Integer.MAX_VALUE;
            for (int row = 0; row < d; row++) {
                for (int col = 0; col < w; col++) {
                    graph[row][col] = scan.nextInt();
                }
            }

            if (k == 1) {
                // 1-1. k 가 1인 경우 어떻게 해도 검증이 성공하기 때문에, 아무것도 바꾸지 않아도 된다.
                answer = 0;
            } else {
                // 1-2. k 가 1이 아닌 경우만 부분집합을 구해, 검증해본다.
                powerSet(0);
            }
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void powerSet(int level) {
        if (level == d) {
            // 2-1. 이 때, 현재 answer 가 새로구한 부분집합의 크기보다 작으면 더이상 진행하지 않는다.
            if (answer <= set.size()) {
                // 2-2. 같거나 더 큰 경우 연산을 진행하는 것이 의미가 없기 때문이다. (제일 작은 부분집합의 크기를 찾고있기 때문)
                return;
            }

            // 2-3. 부분집합의 크기가 더 작다면, 해당 부분집합의 부분집합을 구한다.
            color = new boolean[set.size()];
            subSet(0);
            return;
        }

        set.add(level);
        powerSet(level + 1);
        set.remove(set.size() - 1);
        powerSet(level + 1);
    }

    private static void subSet(int level) {
        if (level == set.size()) {
            // 3-1. 색칠 전 기존 배열을 복사한다.
            film = copy();
            // 3-2. 칠해야되는 색의 종류는 두 종류이다.
            for (int row = 0; row < set.size(); row++) {
                // 3-3. 선택 된 부분집합의 부분집합은 a 색, 선택되지 않은 것은 b로 칠한다.
                if (color[row]) {
                    Arrays.fill(film[set.get(row)], 0);
                } else {
                    Arrays.fill(film[set.get(row)], 1);
                }
            }

            // 3-4. 색을 칠한 이후 세로로 탐색하며, 검증한다.
            for (int col = 0; col < w; col++) {
                // 4-1. 2개의 count 변수를 활용한다.
                int count1 = 0, count2 = 0;
                for (int row = 0; row < d; row++) {
                    if (film[row][col] == 1) {
                        // 4-2. 현재 위치가 1이면 count1++, count2 = 0
                        count2 = 0;
                        count1++;
                    } else {
                        // 4-3. 현재 위치가 0이면 count2++, count1 = 0
                        count1 = 0;
                        count2++;
                    }
                    // 4-4. 만약 count1 또는 count2 가 k보다 크거나 같으면 break, 다음 col로 이동한다.
                    if (count1 >= k || count2 >= k) {
                        break;
                    }
                }
                // 4-5. 만약 탐색 종료 후 count1 과 count2 둘다 k 보다 작으면 검증 실패, return 한다.
                if (count1 < k && count2 < k) {
                    return;
                }
            }
            // 4-6. 검증이 실패하지 않았다면 answer 를 갱신한다.
            answer = Math.min(answer, set.size());
            return;
        }

        color[level] = true;
        subSet(level + 1);
        color[level] = false;
        subSet(level + 1);
    }

    private static int[][] copy() {
        int[][] copy = new int[d][w];
        for (int row = 0; row < d; row++) {
            for (int col = 0; col < w; col++) {
                copy[row][col] = graph[row][col];
            }
        }
        return copy;
    }
}