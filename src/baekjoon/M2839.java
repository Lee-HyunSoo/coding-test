package baekjoon;

import java.util.Scanner;

/**
 * 설탕 배달
 *
 * 1. 최대 5천까지 입력이 들어오므로 5000개짜리 배열을 생성한다.
 * 2. 3, 5kg 봉지만 있으므로 1, 2, 4 봉지는 -1 로 설정한다.
 * 3. 5kg 봉지가 사용가능하면 5kg 봉지를 사용한다.
 * 4. 3kg 봉지가 사용가능하면 3kg 봉지를 사용한다.
 * 5. 둘다 사용불가능하다면 -1 로 한다.
 * 6. 원하는 위치에서 이전값 + 3kg 봉지와 이전값 + 5kg 봉지를 비교한다.
 */
public class M2839 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        // 1. 최대 5천까지 입력이 들어오므로 5000개짜리 배열을 생성한다.
        int[] arr = new int[5001];

        // 2. 3, 5kg 봉지만 있으므로 1, 2, 4 봉지는 -1 로 설정한다.
        arr[1] = -1;
        arr[2] = -1;
        arr[3] = 1;
        arr[4] = -1;
        arr[5] = 1;

        for (int i = 6; i <= n; i++) {
            if (arr[i - 3] == -1 && arr[i - 5] != -1) {
                // 3. 5kg 봉지가 사용가능하면 5kg 봉지를 사용한다.
                arr[i] = arr[i - 5] + 1;
            } else if (arr[i - 5] == -1 && arr[i - 3] != -1) {
                // 4. 3kg 봉지가 사용가능하면 3kg 봉지를 사용한다.
                arr[i] = arr[i - 3] + 1;
            } else if (arr[i - 3] == -1 && arr[i - 5] == -1) {
                // 5. 둘다 사용불가능하다면 -1 로 한다.
                arr[i] = -1;
            } else {
                // 6. 원하는 위치에서 이전값 + 3kg 봉지와 이전값 + 5kg 봉지를 비교한다.
                arr[i] = Math.min(arr[i - 3] + 1, arr[i - 5] + 1);
            }
        }
        System.out.println(arr[n]);
    }
}