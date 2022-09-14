package BFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1600 {

    static int K, W, H;
    static boolean[][][] visited;
    static int[] dxHorse = {-2, -1, 1, 2, -2, -1, 1, 2};
    static int[] dyHorse = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] map;
    static Queue<Node> queue;
    static int result = -1;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        visited = new boolean[H][W][K + 1];
        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (curr.x == W - 1 && curr.y == H - 1) {
                result = curr.count;
                break;
            }
            if (curr.horse < K) {
                for (int i = 0; i < 8; i++) {
                    int nx = curr.x + dxHorse[i];
                    int ny = curr.y + dyHorse[i];
                    if (nx >= 0 && ny >= 0 && nx < W && ny < H && map[ny][nx] != 1 && !visited[ny][nx][curr.horse + 1]) {
                        queue.add(new Node(nx, ny, curr.horse + 1, curr.count + 1));
                        visited[ny][nx][curr.horse + 1] = true;
                    }
                }
            }
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < W && ny < H && map[ny][nx] != 1 && !visited[ny][nx][curr.horse]) {
                    queue.add(new Node(nx, ny, curr.horse, curr.count + 1));
                    visited[ny][nx][curr.horse] = true;
                }
            }
        }
        System.out.println(result);
    }

    static class Node {
        int x;
        int y;
        int horse;
        int count;

        Node(int x, int y, int horse, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.horse = horse;
        }
    }
}
