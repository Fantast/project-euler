package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;
import utils.pairs.IntPair;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

//Answer :
public class Task_39 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_39());
        Logger.close();
    }

    //    int n = 121;
//    int n = 40;
    int n = 13;
//    int n = 4;

    int tree[] = new int[n];
    boolean used[] = new boolean[n];
    boolean dist[] = new boolean[n];

    Set<Node> all = new HashSet<>();

    private PrintWriter out;

    public void solving() throws IOException {
        out = new PrintWriter(
                new BufferedWriter(
                        new FileWriter("/downloads/task39.out")));

        for (int i = 0; i < n; ++i) {
            progress(i);

            used[i] = true;
            tree[0] = i;
            if (find(1)) {
                break;
            }
            used[i] = false;
        }

        for (Node n : all) {
            out.println(n);
        }
        out.close();
    }

    public boolean find(int ind) {
        if (ind == n) {
            all.add(createNode(0));
            return true;
        }
        int parent = tree[(ind - 1) / 3];
        for (int i = 0; i < n; ++i) {
            if (!used[i]) {
                int d = abs(i - parent);
                if (!dist[d]) {
                    used[i] = true;
                    dist[d] = true;
                    tree[ind] = i;
                    find(ind + 1);
//                    if (find(ind+1)) {
//                        return true;
//                    }
                    dist[d] = false;
                    used[i] = false;
                }
            }
        }
        return false;
    }

    private Node createNode(int ind) {
        Set<Node> children = new HashSet<>();
        if (ind < n/3) {
            for (int i = 1; i <= 3; ++i) {
                children.add(createNode(3*ind + i));
            }
        }
        return new Node(tree[ind], children);
    }

    static class Node {
        final int value;
        final Set<Node> children;

        public Node(int value, Set<Node> children) {
            this.value = value;
            this.children = children;
        }

        @Override
        public boolean equals(Object o) {
            Node node = (Node) o;
            return children.equals(node.children);
        }

        @Override
        public int hashCode() {
            return value;
        }

        public String toString() {
            Set<IntPair> links = new TreeSet<>();
            List<Integer> values = new ArrayList<>();

            Queue<Node> q = new LinkedList<>();
            q.add(this);
            while (!q.isEmpty()) {
                Node n = q.poll();
                values.add(n.value);
                q.addAll(n.children);

                for (Node child : n.children) {
                    links.add(new IntPair(n.value, child.value));
                }
            }

            return values.toString();
        }
    }
}
