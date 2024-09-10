package programmers;

/*
이모티콘 플러스 서비스 가입자를 최대한 늘리는 것 / 이모티콘 판매액을 최대한 늘리는 것

n 명에게 m 개의 이모티콘 판매, 이모티콘의 할인율은 10~40% 중 하나로 설정, 할인율은 같을수도 다를수도

사용자들의 기준
- 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘을 모두 구매
- 이모티콘 구매비용의 합이 일정 가격 이상이 되면, 구매를 취소하고 이모티콘 플러스에 가입

users 배열
[0] : 자신이 할인율 기준
[1] : 해당 가격 초과 시 구매비용에서 다 빼고 이모티콘 플러스 가입

emoticons 배열
각 이모티콘의 가격

할인 배열 -> 이모티콘 수만큼 중복순열
[10, 20, 30, 40]
*/
public class Lv2_이모티콘할인행사 {

    int[] policy = {10, 20, 30, 40};
    int[][] users;
    int[] emoticons, discount, answer;

    public int[] solution(int[][] users, int[] emoticons) {
        // init
        this.users = users;
        this.emoticons = emoticons;
        discount = new int[emoticons.length];
        answer = new int[2];

        // method
        setDiscount(0, emoticons.length);
        return answer;
    }

    private void setDiscount(int level, int limit) {
        if (level == limit) {
            // System.out.println(Arrays.toString(discount));
            int[] emos = purchaseEmo();
            // 저장된 이모티콘플러스 가입자보다 크다면 갱신
            if (answer[0] < emos[0]) {
                answer[0] = emos[0];
                answer[1] = emos[1];
            }
            // 저장된 이모티콘플러스 가입자와 같은데 판매액이 더 크다면 갱신
            else if (answer[0] == emos[0] && answer[1] < emos[1]) {
                answer[1] = emos[1];
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            discount[level] = policy[i];
            setDiscount(level + 1, limit);
        }
    }

    private int[] purchaseEmo() {
        int[] emos = new int[2]; // [0]: 이모티콘 플러스 가입수 [1]: 누적된 금액

        for (int[] user : users) {
            int myDiscount = user[0];
            int myLimit = user[1];

            int totalPrice = 0;
            for (int idx = 0; idx < discount.length; idx++) {
                // 이모티콘 할인율이 내가 상정한 할인율보다 높다면 할인가로 구매
                if (discount[idx] >= myDiscount) {
                    totalPrice += (emoticons[idx] * (100 - discount[idx]) / 100);
                }
                // totalPrice 가 내가 상정한 제한금액 이상이면 전부 구매 취소, 이모티콘플러스 가입, break
                if (totalPrice >= myLimit) {
                    totalPrice = 0;
                    emos[0]++;
                    break;
                }
            }
            // totalPrice != 0 이면 누적 이모티콘 구매 금액에 추가
            if (totalPrice != 0) {
                emos[1] += totalPrice;
            }
        }
        return emos;
    }
}