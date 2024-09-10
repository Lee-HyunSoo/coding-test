package programmers;

import java.util.*;

/*
네방향 중 한방향을 골라 dfs
이 때 한방향을 끝까지 dfs
벽을 만나거나 장애물을 만나면 그 이전값을 q 에 넣고 return
q에서 뽑았을 때 해당 좌표가 g 이면 결과
*/
public class Lv2_리코쳇로봇 {

    static class Pair {
        int y, x, dist;
        Pair (int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }

    char[][] graph;
    boolean[][] visit;
    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    Pair start;
    int answer;

    public int solution(String[] board) {
        init(board);
        bfs();

        return answer != Integer.MAX_VALUE ? answer : -1;
    }

    private void bfs() {
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(start);
        visit[start.y][start.x] = true;

        while(!q.isEmpty()) {
            int len = q.size();
            while(len-- > 0) {
                Pair p = q.poll();
                // 뽑은 좌표가 도착지점이면 answer 갱신 후 return
                if (graph[p.y][p.x] == 'G') {
                    answer = p.dist;
                    return;
                }

                // 4방향 탐색
                for (int d = 0; d < 4; d++) {
                    int ny = p.y + dy[d];
                    int nx = p.x + dx[d];

                    // 더한 지점부터 갈 수 있는데까지 이동
                    while (true) {
                        // 이동한 지점이 보드 밖이면 break
                        if (ny < 0 || ny >= graph.length || nx < 0 || nx >= graph[0].length) {
                            break;
                        }
                        // 이동한 지점이 장애물이면 break
                        if (graph[ny][nx] == 'D') {
                            break;
                        }
                        // 이동
                        ny += dy[d];
                        nx += dx[d];
                    }
                    // 보드 밖 or 장애물일 때 break 했으니 좌표 한칸 뒤로 이동 (도착지점)
                    ny -= dy[d];
                    nx -= dx[d];
                    // 도착지점이 이미 방문한 곳이면 continue
                    if (visit[ny][nx]) {
                        continue;
                    }
                    // 아니라면 방문 체크, queue 에 좌표와 이전 거리 + 1
                    visit[ny][nx] = true;
                    q.offer(new Pair(ny, nx, p.dist + 1));
                }
            }
        }
    }

    private void init(String[] board) {
        graph = new char[board.length][board[0].length()];
        visit = new boolean[board.length][board[0].length()];
        for (int row = 0; row < graph.length; row++) {
            for (int col = 0; col < graph[0].length; col++) {
                graph[row][col] = board[row].charAt(col);

                if (graph[row][col] == 'R') {
                    start = new Pair(row, col, 0);
                }
            }
        }
        answer = Integer.MAX_VALUE;
    }
}