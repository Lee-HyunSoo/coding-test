package baekjoon;

import java.util.*;

/**
 * 같이 눈사람 만들래?
 *
 * 1. 문제 정리
 *  1-1. N개의 눈덩이가 있다.
 *  1-2. 각 눈덩이 i (1 ≤ i ≤ N)의 지름은 Hi 이다.
 *  1-3. 하나의 눈사람은 두 개의 눈덩이로 구성되며, 눈덩이 하나를 아래에 두고,
 *  1-4. 그 눈덩이보다 크지 않은 다른 눈덩이를 쌓아올리는 방식으로 만들 수 있다.
 *  1-5. 이때, 눈사람의 키는 두 눈덩이 지름의 합과 같다.
 *  1-6. 엘자와 안나는 눈덩이 N개 중 서로 다른 4개를 골라서 눈사람을 각각 1개씩, 총 2개를 만들려고 한다.
 *  1-8. 주어진 N개의 눈덩이를 이용하여 만들 수 있는 두 눈사람의 키 차이 중 최솟값을 구하는 프로그램을 작성하시오.
 *  1-9. N (4 ≤ N ≤ 600)
 *
 * 2. 두 점으로 만들 수 있는 모든 경우의수를 리스트로 만든다.
 * 	2-1. 두 점을 만들며 비교하면 4중 for loop 가 되기 때문에 이를 줄이기 위해 생성한다.
 *
 * 3. 리스트를 눈사람 높이 순으로 정렬한다.
 * 	3-1. 이 후 탐색하며 중복되지 않는 지점 A, B 를 뽑았을 때, 정렬을 해놨기 때문에 그 값이 A 를 기준으로 가장 차이가 적은 눈사람 B 가 된다.
 * 	3-2. 때문에 중복되지 않은 지점을 찾으면 answer 를 갱신하고 break, 다음 기준으로 잡을 A 로 넘어간다.
 */
public class M20366 {

    static class Snowman {
        int low, high, sum;
        Snowman(int low, int high, int sum) {
            this.low = low;
            this.high = high;
            this.sum = sum;
        }
    }

    static Scanner scan = new Scanner(System.in);
    static int n, answer;
    static int[] snow;
    static List<Snowman> snowman;

    public static void main(String[] args) {
        init();
        setCoordinate();
        findMin();
        System.out.println(answer);
    }

    private static void init() {
        n = scan.nextInt();
        answer = Integer.MAX_VALUE;
        snow = new int[n];
        for (int idx = 0; idx < n; idx++) {
            snow[idx] = scan.nextInt();
        }
        snowman = new ArrayList<>();
    }

    private static void setCoordinate() {
        // 2. 두 점으로 만들 수 있는 모든 경우의수를 리스트로 만든다.
        // 2-1. 두 점을 만들며 비교하면 4중 for loop 가 되기 때문에 이를 줄이기 위해 생성한다.
        for (int idx1 = 0; idx1 < n; idx1++) {
            for (int idx2 = 0; idx2 < n; idx2++) {
                if (idx1 == idx2) continue;
                snowman.add(new Snowman(idx1, idx2, snow[idx1] + snow[idx2]));
            }
        }
        // 3. 리스트를 눈사람 높이 순으로 정렬한다.
        snowman.sort((a, b) -> a.sum - b.sum);
    }

    private static void findMin() {
        // 3-1. 이 후 탐색하며 중복되지 않는 지점 A, B 를 뽑았을 때, 정렬을 해놨기 때문에 그 값이 A 를 기준으로 가장 차이가 적은 눈사람 B 가 된다.
        for (int idx1 = 0; idx1 < snowman.size(); idx1++) {
            for (int idx2 = idx1 + 1; idx2 < snowman.size(); idx2++) {
                // 3-2. 때문에 중복되지 않은 지점을 찾으면 answer 를 갱신하고 break, 다음 기준으로 잡을 A 로 넘어간다.
                Snowman s1 = snowman.get(idx1);
                Snowman s2 = snowman.get(idx2);
                if (s1.low == s2.low || s1.low == s2.high
                        || s1.high == s2.low || s1.high == s2.high) continue;
                answer = Math.min(answer, Math.abs(s1.sum - s2.sum));
                break;
            }
        }
    }
}
