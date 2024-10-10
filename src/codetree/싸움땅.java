package codetree;

import java.util.*;

/**
 * 싸움땅
 *
 * 1. 문제정리
 *  1-1. n * n 크기의 격자
 *  1-2. 첫 번째 플레이어부터 순차적으로 본인이 향하고 있는 방향대로 한 칸만큼 이동
 *  1-3. 만약 해당 방향으로 나갈 때 격자를 벗어나는 경우에는 정반대 방향으로 방향을 바꾸어서 1만큼 이동
 *  1-4. 만약 이동한 방향에 플레이어가 없다면 해당 칸에 총이 있는지 확인
 *      1-4-1. 총이 있는 경우, 해당 플레이어는 총을 획득
 *  1-5. 플레이어가 이미 총을 가지고 있는 경우에는 놓여있는 총들과 플레이어가 가지고 있는 총 가운데 공격력이 더 쎈 총을 획득
 *      1-5-1. 나머지 총들은 해당 격자에 둔다.
 *  1-6. 만약 이동한 방향에 플레이어가 있는 경우에는 두 플레이어가 싸운다.
 *      1-6-1. 해당 플레이어의 초기 능력치와 가지고 있는 총의 공격력의 합을 비교하여 더 큰 플레이어가 이기게 된다.
 *      1-6-2. 만일 이 수치가 같은 경우에는 플레이어의 초기 능력치가 높은 플레이어가 승리하게 된다.
 *      1-6-3. 이긴 플레이어는 각 플레이어의 초기 능력치와 가지고 있는 총의 공격력의 합의 차이만큼을 포인트로 획득
 *  1-7. 진 플레이어는 본인이 가지고 있는 총을 해당 격자에 내려놓고, 해당 플레이어가 원래 가지고 있던 방향대로 한 칸 이동
 *      1-7-1. 만약 이동하려는 칸에 다른 플레이어가 있거나 격자 범위 밖인 경우에는 오른쪽으로 90도씩 회전하여 빈 칸이 보이는 순간 이동
 *      1-7-2. 만약 해당 칸에 총이 있다면, 해당 플레이어는 가장 공격력이 높은 총을 획득하고 나머지 총들은 해당 격자에 내려 놓는다.
 *  1-8. 이긴 플레이어는 승리한 칸에 떨어져 있는 총들과 원래 들고 있던 총 중 가장 공격력이 높은 총을 획득하고, 나머지 총들은 해당 격자에 내려 놓는다.
 *
 *  2. 플레이어, 총 배열
 */
public class 싸움땅 {

    static class Player {
        int x, y, d, s, gun, point;

        public Player(int x, int y, int d, int s) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.s = s;
        }
    }

    static Scanner scan = new Scanner(System.in);
    static int n, m, k;
    static Queue<Integer>[][] guns;
    static List<Player> players;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        init();
        for (int idx = 0; idx < k; idx++) {
            round();
        }
        for (Player p : players) {
            System.out.print(p.point + " ");
        }
    }

    private static void init() {
        n = scan.nextInt(); // 격자 크기
        m = scan.nextInt(); // 플레이어 수
        k = scan.nextInt(); // 라운드

        guns = new PriorityQueue[n + 1][n + 1];
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                guns[row][col] = new PriorityQueue<>((a, b) -> b - a);
            }
        }
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                int gun = scan.nextInt();
                if (gun != 0) {
                    guns[row][col].add(gun);
                }
            }
        }

        players = new ArrayList<>();
        for (int idx = 0; idx < m; idx++) {
            int x = scan.nextInt(); // x 좌표
            int y = scan.nextInt(); // y 좌표
            int d = scan.nextInt(); // 방향
            int s = scan.nextInt(); // 능력치
            players.add(new Player(x, y, d, s));
        }
    }

    private static void round() {
        for (int row = 0; row < players.size(); row++) {
            Player p1 = players.get(row);
            move(p1);
            // 플레이어가 있는지 확인
            Player p2 = isExist(p1, row);
            if (p2 == null) {
                getGun(p1); // 플레이어가 없다면
            } else {
                fight(p1, p2); // 플레이어가 있다면
            }
        }
    }

    private static void move(Player p) {
        int nx = p.x + dx[p.d];
        int ny = p.y + dy[p.d];

        // 격자 외부라면 방향 전환
        if (nx < 1 || nx > n || ny < 1 || ny > n) {
            int dir = (p.d + 2) % 4; // 반대방향
            nx = p.x + dx[dir];
            ny = p.y + dy[dir];
            p.d = dir;
        }

        // 이동
        p.x = nx;
        p.y = ny;
    }

    private static Player isExist(Player p1, int row) {
        for (int col = 0; col < players.size(); col++) {
            if (row == col) continue;

            Player p2 = players.get(col);
            if (p1.x == p2.x && p1.y == p2.y) {
                return p2;
            }
        }
        return null;
    }

    private static void getGun(Player p) {
        // 총이 있다면
        if (!guns[p.x][p.y].isEmpty()) {
            int gun = guns[p.x][p.y].poll();
            if (p.gun == 0) {
                // 내가 총이 없다면 획득
                p.gun = gun;
            } else {
                // 내가 총이 있다면
                if (p.gun < gun) {
                    // 더 쎈총을 줍고 갖고있는 총을 내려놓음
                    guns[p.x][p.y].offer(p.gun);
                    p.gun = gun;
                } else {
                    // 들고있는 총이 더쌔다면 다시 큐에 넣음
                    guns[p.x][p.y].offer(gun);
                }
            }
        }
    }

    private static void fight(Player p1, Player p2) {
        int p1Power = p1.s + p1.gun;
        int p2Power = p2.s + p2.gun;

        if (p1Power > p2Power) {
            p1.point += p1Power - p2Power;
            losePlayer(p2);
            winPlayer(p1);
        } else if (p2Power > p1Power) {
            p2.point += p2Power - p1Power;
            losePlayer(p1);
            winPlayer(p2);
        } else {
            if (p1.s > p2.s) {
                losePlayer(p2);
                winPlayer(p1);
            } else if (p2.s > p1.s) {
                losePlayer(p1);
                winPlayer(p2);
            }
        }
    }

    private static void losePlayer(Player lose) {
        // 가지고 있는 총을 내려 놓는다.
        guns[lose.x][lose.y].add(lose.gun);
        lose.gun = 0;

        // 원래 가지고있던 방향으로 한칸 이동한다.
        // 이동하려는 칸에 다른 플레이어가 있거나 격자범위 밖이면 방향을 오른쪽으로 90도씩 회전
        while(true) {
            int nx = lose.x + dx[lose.d];
            int ny = lose.y + dy[lose.d];

            if (1 <= nx && nx <= n && 1 <= ny && ny <= n) {
                boolean flag = false;
                for (Player p : players) {
                    if (p.x == nx && p.y == ny) {
                        flag = true;
                        break;
                    }
                }
                // flag == true : 이미 플레이어가 있다는 뜻
                if (flag) {
                    // 방향 회전
                    lose.d = (lose.d + 1) % 4;
                } else {
                    // 회전 후 다음칸이 빈칸이면 (플레이어가 없으면) 이동
                    lose.x = nx;
                    lose.y = ny;
                    // 해당 칸에 총이 있다면 가장 공격력이 높은 총 획득, 나머지는 내려놓는다.
                    if (!guns[nx][ny].isEmpty()) {
                        lose.gun = guns[nx][ny].poll();
                    }
                    return;
                }
            } else {
                // 방향 회전
                lose.d = (lose.d + 1) % 4;
            }
        }
    }

    private static void winPlayer(Player win) {
        getGun(win);
    }
}
