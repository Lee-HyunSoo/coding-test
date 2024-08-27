package baekjoon;

import java.util.*;

/**
 * 암호 만들기
 *
 * 1. 문제 정리
 *  1-1. 암호는 최소 한개의 모음(a e i o u), 최소 두개의 자음으로 구성
 *  1-2. 암호는 정렬되어 있어야 한다.
 *
 * 2. 조합 사용한다.
 *  2-1. 입력 배열을 조합을 통해 뽑는다.
 *  2-2. 뽑은 배열에 자음이 1개 이상 있는지, 모음자음이 2개 이상있는지 확인한다.
 *  2-3. 있다면 set 에 저장한다.
 *
 * 3. 정렬
 *  3-1. set 에서 하나씩 꺼내 char 배열로 바꾼 후 정렬한다.
 *  3-2. 정렬 후 리스트에 넣는다.
 *  3-3. 리스트를 다시 정렬한다.
 */
public class M1759 {

    static int l, c;
    static char[] words;
    static char[] combi;
    static Set<String> set;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        l = scan.nextInt();
        c = scan.nextInt();
        scan.nextLine();
        String[] split = scan.nextLine().split(" ");
        words = new char[c];
        for (int idx = 0; idx < c; idx++) {
            words[idx] = split[idx].charAt(0);
        }
        combi = new char[l];
        set = new HashSet<>();
        // 2-1. 입력 배열을 조합을 통해 뽑는다.
        combination(0, 0);

        List<String> answer = new ArrayList<>();
        for (String s : set) {
            // 3-1. set 에서 하나씩 꺼내 char 배열로 바꾼 후 정렬한다.
            char[] ch = s.toCharArray();
            Arrays.sort(ch);
            // 3-2. 정렬 후 리스트에 넣는다.
            answer.add(String.valueOf(ch));
        }
        // 3-3. 리스트를 다시 정렬한다.
        answer.sort(null);
        for (String s : answer) {
            System.out.println(s);
        }
    }

    private static void combination(int level, int idx) {
        if (level == l) {
            // 2-2. 뽑은 배열에 자음이 1개 이상 있는지, 모음자음이 2개 이상있는지 확인한다.
            int consonant = 0, gather = 0;
            for (int i = 0; i < l; i++) {
                if (combi[i] == 'a' || combi[i] == 'e' || combi[i] == 'i' || combi[i] == 'o' || combi[i] == 'u') {
                    gather++;
                } else {
                    consonant++;
                }
            }
            // 2-3. 있다면 set 에 저장한다.
            if (gather >= 1 && consonant >= 2) {
                set.add(String.valueOf(combi));
            }
            return;
        }

        for (int i = idx; i < c; i++) {
            combi[level] = words[i];
            combination(level + 1, i + 1);
        }
    }
}
