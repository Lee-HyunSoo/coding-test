package ssafy;

import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.Arrays;

public class Dijkstra {

    static class Node {
        int n, price;

        public Node(int n, int price) {
            this.n = n;
            this.price = price;
        }
    }

    ArrayList[] alist = new ArrayList[5];

    void init() {
        alist[0] = new ArrayList<>(Arrays.asList(new Node(1, 10), new Node(2, 30), new Node(4, 100)));
        alist[1] = new ArrayList<>(Arrays.asList(new Node(2, 10), new Node(3, 40), new Node(4, 50)));
        alist[2] = new ArrayList<>(Arrays.asList(new Node(3, 10)));
        alist[3] = new ArrayList<>(Arrays.asList(new Node(4, 15)));
        alist[4] = new ArrayList<>();
    }

    void solution() {
        init();
    }

    public static void main(String[] args) {
        new Dijkstra().init();
    }
}
