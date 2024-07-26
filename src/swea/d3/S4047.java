package swea.d3;

import java.util.*;

/**
 * 영준이의 카드 카운팅
 *
 * a, j, q, k - 1, 11, 12, 13
 * 1 ~ 13 까지 각 4장씩
 * 카드의 무늬 : S D H C
 * 이미 가지고 있는 카드 -> 무늬 별로 몇 장의 카드가 부족한가?
 * 겹치는 카드가 있다면 ERROR
 *  -- Map<Char, Set<Integer>> 으로 value 에서 겹침 여부 확인
 *  -- 겹치지 않았다면, 13 - set.size()
 */
public class S4047 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int tc = 1; tc <= t; tc++) {
            char[] cards = scan.nextLine().toCharArray();

            Map<Character, Set<Integer>> cardMap = new HashMap<>();
            for (char ch : new char[] {'S', 'D', 'H', 'C'}) {
                cardMap.put(ch, new HashSet<>());
            }

            boolean flag = false;
            for (int i = 0; i < cards.length; i+=3) {
                char card = cards[i];
                String str = String.valueOf(cards[i + 1]) + String.valueOf(cards[i + 2]);
                int num = Integer.parseInt(str);

                Set<Integer> value = cardMap.get(card);
                if (value.contains(num)) { // 카드가 겹쳤다면
                    flag = true;
                    break;
                } else {
                    value.add(num);
                }
            }

            if (flag) {
                System.out.println("#" + tc + " ERROR");
            } else {
                int s = 13 - cardMap.get('S').size();
                int d = 13 - cardMap.get('D').size();
                int h = 13 - cardMap.get('H').size();
                int c = 13 - cardMap.get('C').size();
                System.out.println("#" + tc + " " + s + " " + d + " " + h + " " + c);
            }
        }
    }
}