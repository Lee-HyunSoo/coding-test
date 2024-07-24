package swea.d3;

import java.util.*;

/**
 * Rooted Binary Tree 재구성
 * 리스트를 돌며 짝수 index 인것만 모아서 결과 리스트에 추가
 * 홀수 index 인 것을 모아 새로운 input 으로 갱신
 * 로직 반복
 */
public class S7985 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int k = scan.nextInt();

            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < Math.pow(2, k) - 1; i++) {
                list.add(scan.nextInt());
            }

            List<List<Integer>> result = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                List<Integer> odd = new ArrayList<>();
                List<Integer> even = new ArrayList<>();
                for (int j = 0; j < list.size(); j++) {
                    if (j % 2 == 0) {
                        even.add(list.get(j));
                    } else {
                        odd.add(list.get(j));
                    }
                }
                result.add(even);
                list = odd;
            }

            System.out.print("#" + tc + " ");
            for (int i = result.size() - 1; i >= 0; i--) {
                for (int j = 0; j < result.get(i).size(); j++) {
                    System.out.print(result.get(i).get(j) + " ");
                }
                System.out.println();
            }
        }
    }
}