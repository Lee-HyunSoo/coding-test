package codetree;

import java.util.*;

/**
 * 술래잡기
 *
 * 1. 문제 정리
 * 	1-1. n * n 격자, 술래는 처음에 정 중앙에 서있다.
 * 	1-2. m 명의 도망자, 도망자는 처음 지정된 곳에 서있고 중앙에서 시작하지 않는다.
 * 	1-3. 도망자는 좌-우 / 상-하 로만 움직인다.
 * 		1-3-1. 좌-우 로 움직이는 살마은 항상 오른쪽을 보고 시작하고, 상-하로 움직이는 사람은 항상 아래쪽을 보고 시작한다.
 * 	1-4. h 개의 나무가 있다.
 * 		1-4-1. 나무는 도망자와 초기에 겹쳐질 수 있다.
 * 	1-5. m 명의 도망자가 먼저 움직이고, 술래가 움직이고 ... 총 k 번 반복한다.
 * 		1-5-1. 도망자가 움직일 때 현재 술래와의 거리가 3 이하인 도망자만 움직인다.
 * 		1-5-2. 거리는 맨해튼 거리로 잰다.
 * 	1-6. 거리가 3 이하인 도망자는 다음 규칙에 따라 움직인다.
 * 		1-6-1. 바라보는 쪽으로 한칸 움직였을 때 격자 내부라면
 * 			1-6-1-1. 움직이려는 칸에 술래가 있으면 움직이지 않는다.
 * 			1-6-1-2. 술래가 있지 않다면 해당 칸으로 이동한다. 나무가 있어도 ok
 * 		1-6-2. 격자를 벗어나는 경우
 * 			1-6-2-1. 방향을 반대로 틀어준다.
 * 			1-6-2-2. 이 후 앞의 한칸에 술래가 없다면 한칸 앞으로 이동한다.
 *	1-7. 술래는 달팽이 모양으로 움직인다.
 *		1-7-1. 끝에 도달하게 되면 다시 거꾸로 중심으로 이동한다.
 *		1-7-2. 바라보는 방향으로 이동 후 방향을 바꿔야하는 지점이면 바로 방향을 바꾼다.
 *		1-7-3. 양끝점인 (1,1) (중앙,중앙) 이면 바로 방향을 틀어줘야한다.
 *		1-7-4. 술래는 시야 내의 도망자를 잡는다. 시야는 3칸이다.
 *		1-7-5. 이 때 나무와 도망자가 같이 놓여있다면 그 칸의 도망자는 잡히지 않는다.
 *	1-8. 술래는 현재 턴이 t 턴이라고 했을 때, t * 현재 턴에서 잡힌 도망자 수 만큼 점수를 얻는다.
 *  1-9. n : 격자길이(반드시 홀수), m : 도망자 입력수, h : 나무입력 수, k : 턴 수
 *
 * 2. 달팽이 배열 구현
 * 	2-1. 중앙 -> (1,1) 로 가는 순서대로 배열에 저장한다.
 * 	2-2. 이 때 (1, 1, 2, 2, 2) + 1 순으로 add 한다.
 * 	2-3. (1,1) -> 중앙으로 가는 순서대로 배열에 저장한다.
 * 	2-4. 이 때 4 + (4, 4, 3, 3, 2, 2, 1) 순으로 add 한다.
 *
 * 3. 3칸씩 탐색한다.
 */
public class 술래잡기 {

    static class Tagger {
        int x, y, dir;

        public Tagger(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static class Runaway {
        int x, y, dir;
        boolean isDelete;

        public Runaway(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static Scanner scan = new Scanner(System.in);
    static int n, m, h, k;
    static int[][] tree;

    static List<Tagger> taggers1;
    static List<Tagger> taggers2;
    static List<Runaway> runaways;
    static int taggerX, taggerY, taggerDir;
    static boolean isCenter;
    static int answer;

    // 상 우 하 좌
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        init();
        moveTagger1();
        moveTagger2();

        int idx = 0;
        Tagger tagger = taggers1.get(idx++); // 술래 초기위치
        for (int turn = 1; turn <= k; turn++) {
            moveRunaway(tagger.x, tagger.y);
            if (isCenter) {
                tagger = taggers1.get(idx++);
                if (idx == taggers1.size()) {
                    // 끝에 도달 시 그 다음턴에 술래의 위치가 바뀌므로 1로 초기화
                    // 0으로 초기화하면 이전의 마지막턴의 위치 == 다음턴의 첫 위치가 되버린다.
                    idx = 1;
                    isCenter = false;
                }
            } else {
                tagger = taggers2.get(idx++);
                if (idx == taggers2.size()) {
                    // 끝에 도달 시 그 다음턴에 술래의 위치가 바뀌므로 1로 초기화
                    // 0으로 초기화하면 이전의 마지막턴의 위치 == 다음턴의 첫 위치가 되버린다.
                    idx = 1;
                    isCenter = true;
                }
            }
            search(tagger, turn);
        }
        System.out.println(answer);
    }

    private static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        h = scan.nextInt();
        k = scan.nextInt();
        tree = new int[n + 1][n + 1];
        taggers1 = new ArrayList<>();
        taggers2 = new ArrayList<>();
        runaways = new ArrayList<>();

        for (int idx = 0; idx < m; idx++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            int d = scan.nextInt();
            runaways.add(new Runaway(x, y, d));
        }

        for (int idx = 0; idx < h; idx++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            tree[x][y] = 1;
        }
        isCenter = true;
        answer = 0;
    }

    // 2-1. 중앙 -> (1,1) 로 가는 순서대로 배열에 저장한다.
    private static void moveTagger1() {
        int currDir = 0;
        taggerX = (n + 1) / 2;
        taggerY = (n + 1) / 2;
        // 2-2. 이 때 (1, 1, 2, 2, 2) + 1 순으로 add 한다.
        for (int rotate = 1; rotate < n; rotate++) {
            // 1, 1, 2, 2 부분
            for (int i = 0; i < rotate; i++) {
                taggers1.add(new Tagger(taggerX, taggerY, currDir));
                taggerX += dx[currDir];
                taggerY += dy[currDir];
            }
            currDir = (currDir + 1) % 4;
            for (int i = 0; i < rotate; i++) {
                taggers1.add(new Tagger(taggerX, taggerY, currDir));
                taggerX += dx[currDir];
                taggerY += dy[currDir];
            }
            currDir = (currDir + 1) % 4;
        }
        for (int i = 1; i < n; i++) {
            taggers1.add(new Tagger(taggerX, taggerY, currDir));
            taggerX += dx[currDir];
            taggerY += dy[currDir];
        }
        // + 1 부분
        taggers1.add(new Tagger(taggerX, taggerY, 3));
    }

    // 2-3. (1,1) -> 중앙으로 가는 순서대로 배열에 저장한다.
    private static void moveTagger2() {
        int currDir = 2;
        taggerX = 1;
        taggerY = 1;
        // 2-4. 이 때 4 + (4, 4, 3, 3, 2, 2, 1, 1) + 1 순으로 add 한다.
        // 4 + 부분
        for (int i = 1; i <= n - 1; i++) {
            taggers2.add(new Tagger(taggerX, taggerY, currDir));
            taggerX += dx[currDir];
            taggerY += dy[currDir];
        }
        currDir -= 1;
        if (currDir < 0) {
            currDir = 3;
        }
        // (4, 4, 3, 3, 2, 2, 1, 1) 부분
        for (int rotate = n - 1; rotate >= 1; rotate--) {
            for (int i = 0; i < rotate; i++) {
                taggers2.add(new Tagger(taggerX, taggerY, currDir));
                taggerX += dx[currDir];
                taggerY += dy[currDir];
            }
            currDir -= 1;
            if (currDir < 0) {
                currDir = 3;
            }
            for (int i = 0; i < rotate; i++) {
                taggers2.add(new Tagger(taggerX, taggerY, currDir));
                taggerX += dx[currDir];
                taggerY += dy[currDir];
            }
            currDir -= 1;
            if (currDir < 0) {
                currDir = 3;
            }
        }
        // + 1 부분
        taggers2.add(new Tagger(taggerX, taggerY, 0));
    }

    private static void moveRunaway(int x, int y) {
        for (Runaway r : runaways) {
            // 1-5-1. 도망자가 움직일 때 현재 술래와의 거리가 3 이하인 도망자만 움직인다.
            // 1-5-2. 거리는 맨해튼 거리로 잰다.
            int dist = Math.abs(x - r.x) + Math.abs(y - r.y);
            if (dist <= 3) {
                int nx = r.x + dx[r.dir];
                int ny = r.y + dy[r.dir];

                // 1-6-1. 바라보는 쪽으로 한칸 움직였을 때 격자 내부라면
                if (0 < nx && nx <= n && 0 < ny && ny <= n) {
                    // 1-6-1-1. 움직이려는 칸에 술래가 있으면 움직이지 않는다.
                    // 1-6-1-2. 술래가 있지 않다면 해당 칸으로 이동한다. 나무가 있어도 ok
                    if (x != nx || y != ny) {
                        r.x = nx;
                        r.y = ny;
                    }
                }
                // 1-6-2. 격자를 벗어나는 경우
                else {
                    // 1-6-2-1. 방향을 반대로 틀어준다.
                    if (r.dir == 1) {
                        r.dir = 3;
                    } else if (r.dir == 3) {
                        r.dir = 1;
                    } else if (r.dir == 0) {
                        r.dir = 2;
                    } else if (r.dir == 2) {
                        r.dir = 0;
                    }
                    // 1-6-2-2. 이 후 앞의 한칸에 술래가 없다면 한칸 앞으로 이동한다.
                    nx = r.x + dx[r.dir];
                    ny = r.y + dy[r.dir];
                    if (x != nx || y != ny) {
                        r.x = nx;
                        r.y = ny;
                    }
                }
            }
        }
    }

    // 3. 3칸씩 탐색한다.
    private static void search(Tagger tagger, int turn) {
        int nx = tagger.x;
        int ny = tagger.y;

        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (1 <= nx && nx <= n && 1 <= ny && ny <= n) {
                for (Runaway r : runaways) {
                    if (!r.isDelete && r.x == nx && r.y == ny) {
                        if (tree[nx][ny] != 1) {
                            count++;
                            r.isDelete = true;
                        }
                    }
                }
            }
            nx += dx[tagger.dir];
            ny += dy[tagger.dir];
        }
        answer += (turn * count);
    }
}
