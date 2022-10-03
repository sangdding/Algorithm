package BOJ.Graph.BFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P16234 {

    static int N, L, R;
    static int[][] field;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Node> list;


    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        field = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(move());
    }

    public static int move() {
        int result = 0;
        while (true) {
            boolean isMove = false;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        int sum = bfs(i, j);
                        if (list.size() > 1) {
                            changePopulation(sum);
                            isMove = true;
                        }
                    }
                }
            }
            if (!isMove) return result;
            result++;
        }
    }

    public static int bfs(int x, int y) {
        Queue<Node> queue = new LinkedList<>();
        list = new ArrayList<>();

        queue.offer(new Node(x, y));
        list.add(new Node(x, y));
        visited[x][y] = true;
        int sum = field[x][y];
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                    int diff = Math.abs(field[current.x][current.y] - field[nx][ny]);
                    if (L <= diff && diff <= R) {
                        queue.offer(new Node(nx, ny));
                        list.add(new Node(nx, ny));
                        sum += field[nx][ny];
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return sum;
    }

    public static void changePopulation(int sum) {
        int avg = sum / list.size();
        for (Node n : list) {
            field[n.x][n.y] = avg;
        }
    }

    public static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
