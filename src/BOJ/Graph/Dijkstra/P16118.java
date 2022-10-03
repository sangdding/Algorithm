package BOJ.Graph.Dijkstra;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class P16118 {

    static int N, M;
    static List<List<Node>> input;
    static long[] fox;
    static long[][] wolf;

    public static void main(String[] args) throws IOException {

        InputReader in = new InputReader(System.in);
        int N = in.readInt();
        int M = in.readInt();

        input = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            input.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int a = in.readInt();
            int b = in.readInt();
            int d = in.readInt();
            input.get(a).add(new Node(b, d * 2L));
            input.get(b).add(new Node(a, d * 2L));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        fox = new long[N + 1];
        wolf = new long[2][N + 1];
        Arrays.fill(fox, Long.MAX_VALUE);
        Arrays.fill(wolf[0], Long.MAX_VALUE);
        Arrays.fill(wolf[1], Long.MAX_VALUE);


        dijkstraFox();
        dijkstraWolf();

        int count = 0;

        for (int i = 2; i <= N; i++) {
            if (fox[i] < Math.min(wolf[0][i], wolf[1][i])) {
                count++;
            }
        }

        System.out.println(count);
    }

    static void dijkstraWolf() {
        Queue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(1, 0, 0));
        wolf[0][1] = 0;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            int dest = curr.dest;
            long length = curr.length;

            if (wolf[curr.state][dest] < length) {
                continue;
            }

            for (Node node : input.get(dest)) {
                int state = 1 - curr.state;
                long cost = wolf[curr.state][dest] + ((curr.state == 0) ? node.length / 2 : node.length * 2);
                if (wolf[state][node.dest] > cost) {
                    wolf[state][node.dest] = cost;
                    queue.offer(new Node(node.dest, cost, state));
                }
            }
        }
    }

    static void dijkstraFox() {
        Queue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(1, 0));
        fox[1] = 0;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (fox[curr.dest] < curr.length) {
                continue;
            }

            for (Node node : input.get(curr.dest)) {
                long cost = fox[curr.dest] + node.length;
                if (fox[node.dest] > cost) {
                    fox[node.dest] = cost;
                    queue.offer(new Node(node.dest, cost));
                }
            }
        }
    }

    private static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }

    static class Node implements Comparable<Node> {
        int dest;
        long length;
        int state;

        Node(int dest, long length) {
            this.dest = dest;
            this.length = length;
        }

        Node(int dest, long length, int state) {
            this.dest = dest;
            this.length = length;
            this.state = state;
        }

        @Override
        public int compareTo(Node o) {
            if (this.length < o.length) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
