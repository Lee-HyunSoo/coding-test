package swea.swcompetency;

import java.util.*;

/**
 * 차량 정비소
 *
 * 1. 문제 정리
 * 	1-1. 차량 정비소에서는 두단계를 거쳐서 정비한다.
 * 		1-1-1. 접수 창구에서 고장을 접수
 * 		1-1-2. 정비 창구에서 차량을 정비
 *	1-2. 차량 정비가 끝난 고객은 설문조사를 한다.
 *	1-3. 담당자의 처리시간은 고객과 고장의 종류에 상관없이 같다.
 *		1-3-1. 접수창구 i 에서 고객 한명 처리에 ai
 *		1-3-2. 정비창구 j 에서 고객 한명 정비하는데 bj
 *	1-4. 차량 정비소를 방문한 고객은 k 명
 *	1-5. 고객은 도착하는대로 1번부터 고객번호 부여
 *	1-6. 고객번호 k 의 고객이 차량 정비소에 도착하는 시간은 tk
 *	1-7. 빈 접수 창구가 있는 경우 접수, 없으면 대기
 *	1-8. 빈 정비창구가 있는 경우 정비, 없으면 대기
 *	1-9. 접수창구의 우선순위
 *		1-9-1. 여러 고객이 대기중인 경우 고객번호가 낮은 순 접수
 *		1-9-2. 빈 창구가 여러 곳인 경우 창구번호가 작은 순으로
 *	1-10. 정비창구의 우선순위
 *		1-10-1. 먼저 기다리는 고객이 먼저
 *		1-10-2. 두명 이상의 고객이 동시에 접수 완료했다면 이용했던 접수 창구번호가 작은 순
 *		1-10-3. 빈 창구가 여러곳인 경우 정비 창구번호가 작은 곳으로
 * 	1-11. 차량정비소입장 -> 접수창구 -> 정비창구로 이동하는 시간은 0
 *
 * 2. 접수, 정비 창구 클래스
 * 	2-1. 창구 번호
 * 	2-2. 처리 시간
 * 	2-3. 현재 사용중인지 체크 여부
 *
 * 3. 고객 클래스
 * 	3-1. 고객 번호
 * 	3-2. 차량 정비소에 도착하는 시간
 * 	3-3. 사용한 접수 창구 번호
 * 	3-4. 접수창구 완료 시간 (시작시간 + 처리시간)
 *	3-5. 사용한 정비 창구 번호
 *	3-5. 정비 창구 완료 시간 (시작시간 + 처리시간)
 *	3-6. 정렬 (도착 시간순, 같다면 고객 번호 순)
 *
 * 4. 고객 테이블 탐색
 * 	4-1. 현재 시간 == 도착시간 이고 접수종료시간이 -1인 고객을 접수 대기 큐에 넣음
 * 	4-2. 현재시간 == 접수종료시간인 고객은 정비 대기큐에 넣음
 * 	4-3. 접수창구 사용 x 로 변경
 * 	4-4. 현재시간 == 정비종료시간인 고객이 있다면 정비창구 사용 x 로 변경
 * 	4-5. endService 활성화
 *
 * 5. 정비 대기큐 탐색
 * 	5-1. 정비대기큐가 null 이 아니라면
 * 	5-2. 정비창구 탐색
 * 	5-3. 사용할 수 있는 창구가 있다면 정비창구 사용중으로 변경, 고객테이블의 정비창구, 정비완료시간 update, continue
 * 	5-4. 없다면 q 에서 뽑은걸 다시 집어 넣고 return
 *
 * 6. 접수 대기큐 탐색
 * 	6-1. 접수대기큐가 null 이 아니라면
 * 	6-2. 접수창구 탐색
 * 	6-3. 사용할 수 있는 창구가 있다면 접수창구 사용중으로 변경, 고객테이블의 접수창구, 접수종료시간 update, continue
 * 	6-4. 없다면 q에서 뽑은걸 다시 집어 넣고 return
 *
 * 7. 종료 된 고객테이블 탐색
 * 	7-1. 모든 고객의 endService가 활성화 되있으면 return
 *
 * 8. 고객들이 사용했던 접수창구, 정비창구 확인
 * 	8-1. 지갑 잃은사람과 같은 경우 id 추가
 * 	8-2. 결과가 0 이면 -1 return
 */
public class S2477 {

    static class Recept {
        int id;
        int receptTime;
        boolean isUse;

        Recept(int id, int receptTime) {
            this.id = id;
            this.receptTime = receptTime;
        }
    }

    static class Repair {
        int id;
        int repairTime;
        boolean isUse;

        Repair(int id, int repairTime) {
            this.id = id;
            this.repairTime = repairTime;
        }
    }

    static class Customer {
        int id;
        int arriveTime;
        int useReceptId, useRepairId;
        boolean endService; // 전체 끝남 알림

        int finishReceptTime; // 접수 완료 시간
        int finishRepairTime; // 정비 시작 시간

        Customer(int id, int arriveTime) {
            this.id = id;
            this.arriveTime = arriveTime;
            finishReceptTime = -1;
            finishRepairTime = -1;
            endService = false;
        }
    }

    static Scanner scan = new Scanner(System.in);
    static int n, m, k; // 접수 창구의 개수, 정비 창구의 개수, 차량 정비소를 방문한 고객수
    static int a, b; // 지갑을 두고간 고객이 이용한 접수창구, 정비창구
    static Recept[] recepts; // 접수 창구
    static Repair[] repairs; // 정비 창구
    static Customer[] customers; // 고객
    static Queue<Customer> waitRecept; // 접수 대기큐
    static Queue<Customer> waitRepair; // 정비 대기큐

    public static void main(String[] args) {
        int t = scan.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            init();
            useService();
            System.out.println("#" + tc + " " + searchSameNum());
        }
    }

    private static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        k = scan.nextInt();
        a = scan.nextInt();
        b = scan.nextInt();

        recepts = new Recept[n];
        repairs = new Repair[m];
        customers = new Customer[k];

        waitRecept = new PriorityQueue<>((a, b) -> a.id - b.id);
        // 먼저 기다리는 고객이 먼저
        // 두명 이상의 고객이 동시에 접수 완료했다면 이용했던 접수 창구번호가 작은 순
        waitRepair = new PriorityQueue<>((a, b) -> {
            if (a.finishReceptTime == b.finishReceptTime) {
                return a.useReceptId - b.useReceptId;
            }
            return a.finishReceptTime - b.finishReceptTime;
        });

        for (int idx = 0; idx < n; idx++) {
            int receptTime = scan.nextInt();
            recepts[idx] = new Recept(idx + 1, receptTime);
        }

        for (int idx = 0; idx < m; idx++) {
            int repairTime = scan.nextInt();
            repairs[idx] = new Repair(idx + 1, repairTime);
        }

        for (int idx = 0; idx < k; idx++) {
            int arriveTime = scan.nextInt();
            customers[idx] = new Customer(idx + 1, arriveTime);
        }
    }

    // 고객이 사용하는 서비스 모음
    private static void useService() {
        int time = 0;
        while(true) {
            if (finishAll()) { // 전체 종료 여부 확인
                break;
            }
            searchCustomers(time); // 고객 테이블 탐색
            searchWaitRecept(time); // 접수 대기큐 탐색
            searchWaitRepair(time); // 정비 대기큐 탐색
            time++;
        }
    }

    // 4. 고객 테이블 탐색
    private static void searchCustomers(int time) {
        for (Customer c : customers) {
            if (c.endService) {
                continue;
            }
            // 4-1. 현재 시간 == 도착시간 이고 접수종료시간이 -1인 고객을 접수 대기 큐에 넣음
            if (time == c.arriveTime && c.finishReceptTime == -1) {
                waitRecept.offer(c);
            }
            // 4-2. 현재시간 == 접수종료시간인 고객은 정비 대기큐에 넣음
            if (time == c.finishReceptTime) {
                waitRepair.offer(c);
                // 4-3. 접수창구 사용 x 로 변경
                recepts[c.useReceptId - 1].isUse = false;
            }
            // 4-4. 현재시간 == 정비종료시간인 고객이 있다면 정비창구 사용 x 로 변경
            if (time == c.finishRepairTime) {
                repairs[c.useRepairId - 1].isUse = false;
                // 4-5. endService 활성화
                c.endService = true;
            }
        }
    }

    // 5. 정비 대기큐 탐색
    private static void searchWaitRepair(int time) {
        // 5-1. 정비대기큐가 null 이 아니라면
        Loop:
        while(!waitRepair.isEmpty()) {
            Customer c = waitRepair.poll();
            // 5-2. 정비창구 탐색
            for (Repair repair : repairs) {
                // 5-3. 사용할 수 있는 창구가 있다면 정비창구 사용중으로 변경, 고객테이블의 정비창구, 정비완료시간 update, continue
                if (!repair.isUse) {
                    repair.isUse = true;
                    c.useRepairId = repair.id;
                    c.finishRepairTime = time + repair.repairTime;
                    continue Loop;
                }
            }
            // 5-4. 없다면 q 에서 뽑은걸 다시 집어 넣고 return
            waitRepair.offer(c);
            return;
        }
    }

    // 6. 접수 대기큐 탐색
    private static void searchWaitRecept(int time) {
        // 6-1. 접수대기큐가 null 이 아니라면
        Loop:
        while (!waitRecept.isEmpty()) {
            Customer c = waitRecept.poll();
            // 6-2. 접수창구 탐색
            for (Recept recept : recepts) {
                // 6-3. 사용할 수 있는 창구가 있다면 접수창구 사용중으로 변경, 고객테이블의 접수창구, 접수종료시간 update, continue
                if (!recept.isUse) {
                    recept.isUse = true;
                    c.useReceptId = recept.id;
                    c.finishReceptTime = time + recept.receptTime;
                    continue Loop;
                }
            }
            // 6-4. 없다면 q에서 뽑은걸 다시 집어 넣고 return
            waitRecept.offer(c);
            return;
        }
    }

    // 7. 종료 된 고객테이블 탐색
    private static boolean finishAll() {
        for (Customer c : customers) {
            if (!c.endService) {
                return false;
            }
        }
        // 7-1. 모든 고객의 endService가 활성화 되있으면 return
        return true;
    }

    // 8. 고객들이 사용했던 접수창구, 정비창구 확인
    private static int searchSameNum() {
        int result = 0;
        for (Customer c : customers) {
            // 8-1. 지갑 잃은사람과 같은 경우 id 추가
            if (c.useReceptId == a && c.useRepairId == b) {
                result += c.id;
            }
        }
        // 8-2. 결과가 0 이면 -1 return
        return result != 0 ? result : -1;
    }
}
