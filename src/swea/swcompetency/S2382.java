package swea.swcompetency;

import java.util.*;
import java.io.*;

/**
 * 미생물 격리
 *
 * 1. 문제 정리
 * 	1-1. n * n 정사각형 구역, k 개의 미생물 군집
 * 	1-2. 미생물 군집의 위치, 군집 내 미생물 수, 이동방향이 주어진다.
 * 	1-3. 군집은 1시간마다 이동
 * 	1-4. 이동 후 테두리라면 절반이 죽고, 방향이 반대로 바뀐다.
 * 	1-5. 이동 후 여러 군집이 한 셀에 모이면 군집이 합쳐진다.
 * 		1-5-1. 이 때 가장 많은 미생물이 있던 군집의 방향으로 이동
 * 		1-5-2. 합쳐지는 군집의 미생물 수가 같은 경우는 고려 x
 * 	1-6. 셀의 개수 n, 격리 시간 m, 군집의 개수 k
 * 	1-7. x, y, 미생물 수, 이동방향(1, 2, 3, 4) (상 하 좌 우)
 *
 * 2. 현재 칸에 미생물군집이 몇개나 있는지 기록하는 배열을 사용한다.
 * 3. 미생물들의 정보를 기록하는 리스트를 사용한다.
 * 4. 모든 미생물을 이동시킨다.
 * 	4-1. 이동 후 남은 미생물이 0이라면, 삭제하고 개수를 줄인다.
 * 	4-2. for-each 내에서 삭제 시 동시성 문제가 터질 수 있기 때문에 일반 for문으로 돌린다.
 * 5. 겹친 미생물이 있는지 확인한다.
 * 	5-1. 겹쳤다면, 합친 후 미생물 정보를 갱신한다.
 *  5-2. for-each 내에서 삭제 시 동시성 문제가 터질 수 있기 때문에 일반 for문으로 돌린다.
 * 6. 남은 미생물의 수를 더한다.
 */
public class S2382 {

    static class Micro {
        int x, y, cnt, dir;

        Micro (int x, int y, int cnt, int dir) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.dir = dir;
        }
    }

    static int n, m, k;
    static int[][] graph;
    static List<Micro> micros;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            String[] input = br.readLine().split(" ");
            n = Integer.parseInt(input[0]);
            m = Integer.parseInt(input[1]);
            k = Integer.parseInt(input[2]);
            // 2. 현재 칸에 미생물군집이 몇개나 있는지 기록하는 배열을 사용한다.
            graph = new int[n][n];
            // 3. 미생물들의 정보를 기록하는 리스트를 사용한다.
            micros = new ArrayList<>();

            for (int idx = 0; idx < k; idx++) {
                input = br.readLine().split(" ");
                int x = Integer.parseInt(input[0]);
                int y = Integer.parseInt(input[1]);
                int cnt = Integer.parseInt(input[2]);
                int dir = Integer.parseInt(input[3]);
                graph[x][y]++;
                micros.add(new Micro(x, y, cnt, dir));
            }

            for (int time = 0; time < m; time++) {
                // 4. 모든 미생물을 이동시킨다.
                for (int idx = 0; idx < micros.size(); idx++) {
                    Micro micro = micros.get(idx);
                    move(micro);
                    // 4-1. 이동 후 남은 미생물이 0이라면, 삭제하고 개수를 줄인다.
                    // 4-2. for-each 내에서 삭제 시 동시성 문제가 터질 수 있기 때문에 일반 for문으로 돌린다.
                    if (micro.cnt == 0) {
                        micros.remove(micro);
                        graph[micro.x][micro.y]--;
                        idx--;
                    }
                }
                // 5. 겹친 미생물이 있는지 확인한다.
                for (int row = 0; row < n; row++) {
                    for (int col = 0; col < n; col++) {
                        // 5-1. 겹쳤다면, 합친 후 미생물 정보를 갱신한다.
                        if (graph[row][col] > 1) {
                            sumMicro(row, col);
                        }
                    }
                }
            }

            // 6. 남은 미생물의 수를 더한다.
            int answer = 0;
            for (Micro micro : micros) {
                answer += micro.cnt;
            }
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void sumMicro(int x, int y) {
        int sum = 0, dir = 0, max = 0;
        // 5-2. for-each 내에서 삭제 시 동시성 문제가 터질 수 있기 때문에 일반 for문으로 돌린다.
        for (int idx = 0; idx < micros.size(); idx++) {
            Micro micro = micros.get(idx);
            if (micro.x == x && micro.y == y) {
                // 더 큰 값으로 방향 갱신
                if (max < micro.cnt) {
                    max = micro.cnt;
                    dir = micro.dir;
                }
                // 미생물 수 합친 후 기존 미생물 삭제
                sum += micro.cnt;
                micros.remove(micro);
                graph[x][y]--;
                idx--;
            }
        }
        // 다 더한 후 추가
        micros.add(new Micro(x, y, sum, dir));
        graph[x][y]++;
    }

    private static void move(Micro micro) {
        if (micro.dir == 1) {
            graph[micro.x][micro.y]--;
            micro.x -= 1; // 좌표 변경
            graph[micro.x][micro.y]++;
            // 끝에 도달했다면
            if (micro.x == 0) {
                micro.dir = 2; // 방향 변경
                micro.cnt /= 2; // 미생물 수 변경
            }
        } else if (micro.dir == 2) {
            graph[micro.x][micro.y]--;
            micro.x += 1; // 좌표 변경
            graph[micro.x][micro.y]++;
            // 끝에 도달했다면
            if (micro.x == n - 1) {
                micro.dir = 1; // 방향 변경
                micro.cnt /= 2; // 미생물 수 변경
            }
        } else if (micro.dir == 3) {
            graph[micro.x][micro.y]--;
            micro.y -= 1; // 좌표 변경
            graph[micro.x][micro.y]++;
            // 끝에 도달했다면
            if (micro.y == 0) {
                micro.dir = 4; // 방향 변경
                micro.cnt /= 2; // 미생물 수 변경
            }
        } else if (micro.dir == 4) {
            graph[micro.x][micro.y]--;
            micro.y += 1; // 좌표 변경
            graph[micro.x][micro.y]++;
            // 끝에 도달했다면
            if (micro.y == n - 1) {
                micro.dir = 3; // 방향 변경
                micro.cnt /= 2; // 미생물 수 변경
            }
        }
    }
}


