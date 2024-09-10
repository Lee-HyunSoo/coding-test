package programmers;

import java.util.*;

public class Lv2_우박수열정적분 {

    List<Integer> nums;
    List<Double> areas;
    int n;
    double total;

    public double[] solution(int k, int[][] ranges) {
        getNum(k);
        getArea();
        n = nums.size() - 1;

        double[] answer = new double[ranges.length];
        int index = 0;
        for (int[] pos : ranges) {
            // [0, 0] 이면 total
            if (pos[0] == 0 && pos[1] == 0) {
                answer[index++] = total;
            } else {
                int start = pos[0];
                int end = n + pos[1];
                // 시작점이 종료점보다 크면 -1.0
                if (start > end) {
                    answer[index++] = -1.0;
                } else {
                    // 아니라면 구간합
                    double sum = 0;
                    for (int idx = start; idx < end; idx++) {
                        sum += areas.get(idx);
                    }
                    answer[index++] = sum;
                }
            }
        }
        // 1 -2 : 1~5
        // 3 -3 : 3~4

        return answer;
    }

    private void getArea() {
        areas = new ArrayList<>();
        total = 0;
        for (int idx = 1; idx < nums.size(); idx++) {
            int higher = Math.max(nums.get(idx - 1), nums.get(idx));
            int lower = Math.min(nums.get(idx - 1), nums.get(idx));

            double area = higher - ((double) (higher - lower) / 2);
            areas.add(area);
            total += area;
        }
    }

    private void getNum(int k) {
        nums = new ArrayList<>();
        while(k > 1) {
            nums.add(k);
            if (k % 2 == 0) {
                k /= 2;
            } else {
                k *= 3;
                k += 1;
            }
        }
        nums.add(1);
    }
}
