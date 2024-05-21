package swea.d3;

import java.util.*;

/**
 * 일요일
 */
public class S13229 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Map<String, Integer> map = new HashMap<>();
        map.put("MON", 1);
        map.put("TUE", 2);
        map.put("WED", 3);
        map.put("THU", 4);
        map.put("FRI", 5);
        map.put("SAT", 6);
        map.put("SUN", 0);

        int t = scan.nextInt();
        scan.nextLine();
        for (int c = 1; c <= t; c++) {
            String day = scan.nextLine();
            System.out.println("#" + c + " " + (7 - map.get(day)));
        }
    }
}
