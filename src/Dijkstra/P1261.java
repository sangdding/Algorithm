package Dijkstra;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1261 {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Node> queue;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        queue.add(new Node(0, 0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (curr.y == N - 1 && curr.x == M - 1) {
                System.out.println(curr.wall);
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < M && ny < N && !visited[ny][nx]) {
                    if (map[ny][nx] == 0) {
                        queue.add(new Node(ny, nx, curr.wall));
                    } else if (map[ny][nx] == 1) {
                        queue.add(new Node(ny, nx, curr.wall + 1));
                    }
                    visited[ny][nx] = true;
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int wall;

        Node(int y, int x, int wall) {
            this.y = y;
            this.x = x;
            this.wall = wall;
        }

        @Override
        public int compareTo(Node o) {
            return this.wall - o.wall;
        }
    }
}
