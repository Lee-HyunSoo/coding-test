package swea.swcompetency;

import java.util.*;

/**
 * 홈 방범 서비스
 *
 * 1. 문제 정리
 * 	1-1. n*n 크기의 그래프
 * 	1-2. 다이아몬드 모양 서비스 영역 (k)
 * 		1-2-1. 운영 비용 = k*k + (k-1)*(k-1)
 * 	1-3. 각 집마다 지불해야할 비용 m
 * 	1-4. 총 지불비용 - 운영비용이 >= 0 인 경우 집의 최대 개수
 *
 * 2. 각 집들의 좌표를 리스트로 만들어 담는다.
 *
 * 3. k의 최대 크기는 n + 1 이다. (n*n 크기의 배열을 모두 덮기위한 마름모 크기)
 * 	3-1. k 의 크기를 줄여가며 연산한다.
 * 	3-2. 운영 비용을 구한다. (k*k + (k-1)*(k-1))
 * 	3-3. 이중 for-loop 를 통해 그래프의 한 좌표을 기준점으로 잡는다.
 * 	3-4. 집을 탐색한다.
 *	3-5. 기준점과 집 사이의 거리를 구한다.
 *	3-6. 거리가 k 보다 작거나 같다면 같은 범위에 있는 집이기 때문에 count
 *	3-7. 최종 count 를 한 후 총 지불비용을 구한다. (m * count)
 *	3-8. 총 지불비용 - 운영비용이 >= 0 이면 answer 를 최대값으로 갱신한다.
 */
public class S2117 {

    static class Pair {
        int x, y;
        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Scanner scan = new Scanner(System.in);
    static int n, m;
    static int[][] graph;
    static List<Pair> house;
    static int maxHouseCount;

    public static void main(String[] args) {
        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            init();
            calcOperateCost();
            System.out.println("#" + tc + " " + maxHouseCount);
        }
    }

    private static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        graph = new int[n][n];
        house = new ArrayList<>();
        maxHouseCount = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int input = scan.nextInt();
                graph[row][col] = input;
                // 2. 각 집들의 좌표를 리스트로 만들어 담는다.
                if (input == 1) {
                    house.add(new Pair(row, col));
                }
            }
        }
    }

    private static void calcOperateCost() {
        // 3. k의 최대 크기는 n + 1 이다. (n*n 크기의 배열을 모두 덮기위한 마름모 크기)
        // 3-1. k 의 크기를 줄여가며 연산한다.
        for (int k = n + 1; k > 0; k--) {
            // 3-2. 운영 비용을 구한다. (k*k + (k-1)*(k-1))
            int cost = k * k + (k - 1) * (k - 1);
            // 3-3. 이중 for-loop 를 통해 그래프의 한 좌표을 기준점잡는다.
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    // 3-4. 집을 탐색한다.
                    int count = findHouse(k, row, col);
                    // 3-7. 최종 count 를 한 후 총 지불비용을 구한다. (m * count)
                    if (m * count - cost >= 0) {
                        // 3-8. 총 지불비용 - 운영비용이 >= 0 이면 answer 를 최대값으로 갱신한다.
                        maxHouseCount = Math.max(maxHouseCount, count);
                    }
                }
            }
        }
    }

    private static int findHouse(int limitDist, int startX, int startY) {
        int count = 0;
        for (int idx = 0; idx < house.size(); idx++) {
            Pair h = house.get(idx);
            // 3-5. 기준점과 집 사이의 거리를 구한다.
            int dist = getDist(startX, startY, h.x, h.y);
            // 3-6. 거리가 k 보다 작거나 같다면 같은 범위에 있는 집이기 때문에 count
            if (dist < limitDist) {
                count++;
            }
        }
        return count;
    }

    private static int getDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
