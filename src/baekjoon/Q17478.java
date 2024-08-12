package baekjoon;

import java.util.Scanner;

/**
 * 재귀함수가 뭔가요?
 *
 * 문자열을 더하는 연산이 많이 일어남으로 StringBuilder 사용
 * 재귀의 횟수가 n 이 되면, 마무리 문장으로 전환
 * 재귀 호출이 종료되고 돌아오는 시점에 답변하였지 문장 추기
 */
public class Q17478 {

    static int n;
    static StringBuilder sentence;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();

        sentence = new StringBuilder();
        sentence.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");

        chatbot(0);
        System.out.println(sentence.toString());
    }

    private static void chatbot(int level) {
        StringBuilder underBar = new StringBuilder();
        for (int idx = 0; idx < level; idx++) {
            underBar.append("____");
        }

        if (level == n) {
            sentence.append(underBar).append("\"재귀함수가 뭔가요?\"\n");
            sentence.append(underBar).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
            sentence.append(underBar).append("라고 답변하였지.\n");
        } else {
            sentence.append(underBar).append("\"재귀함수가 뭔가요?\"\n");
            sentence.append(underBar).append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
            sentence.append(underBar).append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
            sentence.append(underBar).append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
            chatbot(level + 1);
            sentence.append(underBar).append("라고 답변하였지.\n");
        }
    }
}
