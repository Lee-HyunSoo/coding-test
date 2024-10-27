package baekjoon;

import java.util.*;

/**
 * 홀짝 칵테일
 *
 * 1. 문제 정리
 * 	1-1. 음료들은 정수로 표현되는 고유 번호를 가지고 있다.
 * 	1-2. 이 음료들을 섞어 만든 칵테일을 만든다.
 * 	1-3. 칵테일에 들어가는 음료들의 고유 번호의 곱에 해당하는 맛을 가진다.
 *	1-4. 맛이 홀수인 칵테일이 맛이 짝수인 칵테일보다는 무조건 맛있다고 느낀다.
 *		1-4-1. 똑같이 홀수이거나 짝수인 맛을 가진 칵테일끼리는 맛이 더 큰 칵테일을 더 맛있다고 느낀다.
 *	1-5. 음료 셋의 고유 번호가 주어졌을 때 이 음료들을 조합해 만들 수 있는 칵테일 중 가장 맛있다고 느끼는 칵테일의 맛을 알려주자.
 *	1-6. 칵테일을 만들 때는, 반드시 모든 음료를 사용할 필요는 없지만, 적어도 하나의 음료는 사용해야 한다.
 *	1-7. 하나의 음료만 사용하는 경우 칵테일의 맛은 사용한 음료의 고유 번호와 같다.
 *	1-8. 어지는 세 음료는 서로 다른 고유 번호를 가지고 있다.
 *
 * 2. 입력 된 a, b, c 가 모두 짝수면 a * b * c 를 return 한다.
 * 3. 하나라도 홀수가 있다면 홀수인 수만 뽑는다.
 * 4. 뽑은 홀수들을 다 곱한 값을 return 한다.
 */
public class M21312 {

    static Scanner scan = new Scanner(System.in);
    static int a, b, c;

    public static void main(String[] args) {
        init();
        System.out.println(calc());
    }

    private static void init() {
        a = scan.nextInt();
        b = scan.nextInt();
        c = scan.nextInt();
    }

    private static int calc() {
        // 2. 입력 된 a, b, c 가 모두 짝수면 a * b * c 를 return 한다.
        if (a % 2 == 0 && b % 2 == 0 && c % 2 == 0) {
            return a * b * c;
        } else {
            // 4. 뽑은 홀수들을 다 곱한 값을 return 한다.
            return checkOdd(a) * checkOdd(b) * checkOdd(c);
        }
    }

    private static int checkOdd(int n) {
        // 3. 하나라도 홀수가 있다면 홀수인 수만 뽑는다.
        return (n % 2 != 0) ? n : 1;
    }
}
