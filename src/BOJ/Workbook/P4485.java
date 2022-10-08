package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P4485 {

    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 1;
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }
            map = new int[n][n];
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.printf("Problem %d: %d%n", cnt++, bfs(n));
        }
    }

    public static int bfs(int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, map[0][0]));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            visited[curr.y][curr.x] = true;
            if (curr.y == n - 1 && curr.x == n - 1) {
                return curr.rupee;
            }
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx >= n || ny >= n || nx < 0 || ny < 0) continue;
                if (visited[ny][nx]) continue;
                pq.add(new Node(nx, ny, curr.rupee + map[ny][nx]));
            }
        }
        return -1;
    }

    public static class Node implements Comparable<Node> {
        int x;
        int y;
        int rupee;

        Node(int x, int y, int rupee) {
            this.x = x;
            this.y = y;
            this.rupee = rupee;
        }

        @Override
        public int compareTo(Node o) {
            return this.rupee - o.rupee;
        }
    }

}
