package swea.d3;

import java.util.*;

/**
 * 체스판 위의 룩 배치
 */
public class S15612 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            char[][] chess = new char[8][8];

            int cnt = 0;
            for (int i = 0; i < 8; i++) {
                String input = scan.nextLine();
                for (int j = 0; j < 8; j++) {
                    chess[i][j] = input.charAt(j);

                    if (input.charAt(j) == 'O') {
                        cnt++;
                    }
                }
            }

            boolean flag = false;
            if (cnt != 8) {
                flag = true;
            } else {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (chess[i][j] == 'O') {
                            for (int x = 0; x < 8; x++) {
                                if (x != i && chess[x][j] == 'O') {
                                    flag = true;
                                    break;
                                }
                            }

                            for (int y = 0; y < 8; y++) {
                                if (y != j && chess[i][y] == 'O') {
                                    flag = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (flag) {
                        break;
                    }
                }
            }
            if (flag) {
                System.out.println("#" + c + " no");
            } else {
                System.out.println("#" + c + " yes");
            }
        }
    }
}
