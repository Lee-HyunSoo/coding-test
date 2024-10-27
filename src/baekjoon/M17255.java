package baekjoon;

import java.util.*;

/**
 * N 으로 만들기
 *
 * 1. 문제 정리
 * 	1-1. 처음에 어떤 숫자 하나를 적고 만들어진 수의 왼쪽이나 오른쪽에 숫자를 계속 붙이면 어떤 수 N이든 만들 수 있다.
 * 	1-2. 다시 말해 어떤 수 N을 만들기 위해서는, 처음에 어떤 숫자를 하나 적고 아래의 두 가지 행동을 반복한다.
 * 		1-2-1. 수의 왼쪽에 숫자를 하나 적는다.
 * 		1-2-2. 수의 오른쪽에 숫자를 하나 적는다.
 * 	1-3. 어떤 수 N을 만드는 방법의 수가 몇 가지인지 궁금해졌다. 이를 알아내는 프로그램을 작성해주자.
 * 	1-4. 숫자를 적는 과정에서 나온 수가 순서대로 모두 같다면 같은 방법이다.
 * 	1-5. 단, 숫자를 적는 과정에서 수는 0으로 시작할 수 있다.
 *
 * 2. 숫자를 문자열로 받고, 각 index 를 출발지점으로 설정한다.
 * 	2-1. 재귀 파라미터 : left, right, 현재지점까지 더한 문자, 총 경로
 *
 * 3. 기저 조건
 * 	3-1. left 가 0이고, right 가 number.length() - 1 인 경우 set 에 총 경로를 저장하고 return
 * 		3-1-1. 즉, 모든 숫자를 다 탐색했을 때 여태까지의 경로를 set 에 저장하고 return 한다.
 *
 * 4. left 가 0 보다 큰 경우
 * 	4-1. 이전 문자열의 왼쪽에 현재 문자를 더한다.
 * 	4-2. 더한 결과를 총 경로에 추가한다.
 *
 * 5. right 가 number.length() - 1 보다 작은 경우
 * 	5-1. 이전 문자열의 오른쪽에 현재 문자를 추가한다.
 * 	5-2. 더한 결과를 총 경로에 추가한다.
 */
public class M17255 {

    static Scanner scan = new Scanner(System.in);
    static String number;
    static Set<String> resultSet;

    public static void main(String[] args) {
        init();
        // 2. 숫자를 문자열로 받고, 각 index 를 출발지점으로 설정한다.
        // 2-1. 재귀 파라미터 : left, right, 현재지점까지 더한 문자, 총 경로
        for (int idx = 0; idx < number.length(); idx++) {
            trace(idx, idx, String.valueOf(number.charAt(idx)), String.valueOf(number.charAt(idx)));
        }
        System.out.println(resultSet.size());
    }

    private static void init() {
        number = scan.nextLine();
        resultSet = new HashSet<>();
    }

    private static void trace(int left, int right, String prevStr, String totalPath) {
        // 3. 기저 조건
        // 3-1. left 가 0이고, right 가 number.length() - 1 인 경우 set 에 총 경로를 저장하고 return
        // 3-1-1. 즉, 모든 숫자를 다 탐색했을 때 여태까지의 경로를 set 에 저장하고 return 한다.
        if (left == 0 && right == number.length() - 1) {
            resultSet.add(totalPath);
            return;
        }

        // 4. left 가 0 보다 큰 경우
        // 4-1. 이전 문자열의 왼쪽에 현재 문자를 더한다.
        // 4-2. 더한 결과를 총 경로에 추가한다.
        if (left > 0) {
            String currStr = number.charAt(left - 1) + prevStr;
            trace(left - 1, right, currStr, totalPath + currStr);
        }
        // 5. right 가 number.length() - 1 보다 작은 경우
        // 5-1. 이전 문자열의 오른쪽에 현재 문자를 추가한다.
        // 5-2. 더한 결과를 총 경로에 추가한다.
        if (right < number.length() - 1) {
            String currStr = prevStr + number.charAt(right + 1);
            trace(left, right + 1, currStr, totalPath + currStr);
        }
    }
}
