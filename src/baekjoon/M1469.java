package baekjoon;

import java.util.*;

/**
 * 숌 사이의 수열
 *
 * 1. 문제 정리
 * 	1-1. N개의 다른 숫자로 구성되어 있는 집합 X를 만들었다. 그리고, 길이가 2N인 숌 사이 수열 (S)을 만들려고 한다.
 * 	1-2. 숌 사이 수열이란 다음과 같다.
 *  	1-2-1. X에 들어있는 모든 수는 숌 사이 수열 S에 정확히 두 번 등장해야 한다.
 *  	1-2-2. X에 등장하는 수가 i라면, S에서 두 번 등장하는 i사이에는 수가 i개 등장해야 한다.
 *  1-3. 숌이 만든 집합 X가 {1,2,3}이고, 숌이 만든 숌 사이 수열이 {2 3 1 2 1 3}이라면,
 *  	1-3-1. 일단 X에 속하는 모든 수가 S에 두 번 등장하므로 1번 조건을 만족한다.
 *  	1-3-2. 2와 2사이엔 수가 두 개, 1과 1사이엔 1개, 3과 3사이엔 3개가 등장하므로 조건을 만족시킨다.
 * 	1-4. 만약 여러 개일 경우 사전 순으로 가장 빠른 것을 출력한다. 만약 없을 경우에는 -1을 출력한다.
 *
 * 2. 사전 순으로 가장 빠른 것을 출력하기 위해 입력받은 배열을 정렬한다.
 *
 * 3. 정답을 저장할 배열을 -1 로 채운다.
 * 	3-1. -1 로 채우고 값을 넣었다 뺌으로써 visit 배열의 역할도 겸한다.
 *
 * 4. 기저조건
 * 	4-1. 끝까지 숫자를 채웠다면 return
 * 	4-2. 현재 위치가 이미 채워져있다면 다음 단계로 넘어간다.
 *
 * 5. 백트래킹 조건
 * 	5-1. 이미 사용한 숫자면 continue
 * 	5-2. 배열의 범위 밖으로 나가면 continue
 *  5-3. 현재 위치, num 만큼 떨어진 위치가 둘다 -1 이 아니면 (사용중이면) continue
 *
 * 6. true 가 넘어왔다면 이미 결과가 나왔다는 뜻이기 때문에 return
 */
public class M1469 {

    static Scanner scan = new Scanner(System.in);
    static int n;
    static int[] seq, answer;
    static boolean[] visit;

    public static void main(String[] args) {

        init();
        if (backtrack(0)) {
            for (int idx = 0; idx < n * 2; idx++) {
                System.out.print(answer[idx] + " ");
            }
        } else {
            System.out.println(-1);
        }
    }

    private static void init() {
        n = scan.nextInt();
        seq = new int[n];
        visit = new boolean[n];
        answer = new int[n * 2];

        for (int idx = 0; idx < n; idx++) {
            seq[idx] = scan.nextInt();
        }
        // 2. 사전 순으로 가장 빠른 것을 출력하기 위해 입력받은 배열을 정렬한다.
        Arrays.sort(seq);
        // 3. 정답을 저장할 배열을 -1 로 채운다.
        // 3-1. -1 로 채우고 값을 넣었다 뺌으로써 visit 배열의 역할도 겸한다.
        Arrays.fill(answer, -1);
    }

    private static boolean backtrack(int level) {
        // 4. 기저조건
        // 4-1. 끝까지 숫자를 채웠다면 return
        if (level == n * 2) {
            return true;
        }
        // 4-2. 현재 위치가 이미 채워져있다면 다음 단계로 넘어간다.
        if (answer[level] != -1) {
            return backtrack(level + 1);
        }

        for (int idx = 0; idx < n; idx++) {
            int prev = level;
            int next = level + seq[idx] + 1;

            // 5. 백트래킹 조건
            // 5-1. 이미 사용한 숫자면 continue
            if (visit[idx]) {
                continue;
            }
            // 5-2. 배열의 범위 밖으로 나가면 continue
            if (next >= n * 2) {
                continue;
            }
            // 5-3. 현재 위치, num 만큼 떨어진 위치가 둘다 -1 이 아니면 (사용중이면) continue
            if (answer[prev] != -1 || answer[next] != -1) {
                continue;
            }

            visit[idx] = true;
            answer[prev] = seq[idx];
            answer[next] = seq[idx];

            // 6. true 가 넘어왔다면 이미 결과가 나왔다는 뜻이기 때문에 return
            if (backtrack(level + 1)) {
                return true;
            }

            visit[idx] = false;
            answer[prev] = -1;
            answer[next] = -1;
        }

        return false;
    }
}
