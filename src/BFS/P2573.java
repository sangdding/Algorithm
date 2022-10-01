package BFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2573 {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<Node> queue;
    static Queue<Node> one;
    static int continent = 1;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int year = 0;
        while (countContinent() == 1) {
            year++;
        }
        if (continent == 0) {
            System.out.println(0);
        } else {
            System.out.println(year);
        }
    }

    static int countContinent() {
        queue = new LinkedList<>();
        one = new LinkedList<>();
        visited = new boolean[N][M];
        continent = 0;
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    queue.add(new Node(i, j));
                    visited[i][j] = true;
                    continent++;
                    flag = true;
                    while (!queue.isEmpty()) {
                        Node curr = queue.poll();
                        int count = 0;
                        for (int k = 0; k < 4; k++) {
                            int nx = curr.x + dx[k];
                            int ny = curr.y + dy[k];
                            if (ny >= 0 && nx >= 0 && ny < N && nx < M && map[ny][nx] != 0 && !visited[ny][nx]) {
                                queue.add(new Node(ny, nx));
                                visited[ny][nx] = true;
                            } else if (map[ny][nx] == 0) {
                                count++;
                            }
                        }
                        if (map[curr.y][curr.x] == 1 && count > 0) {
                            one.add(curr);
                        } else if (map[curr.y][curr.x] > 1) {
                            if (map[curr.y][curr.x] <= count) {
                                one.add(new Node(curr.y, curr.x));
                            } else {
                                map[curr.y][curr.x] -= count;
                            }
                        }
                    }
                }
            }
        }
        while (!one.isEmpty()) {
            Node curr = one.poll();
            map[curr.y][curr.x] = 0;
        }
        return continent;
    }

    static class Node {
        int x;
        int y;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
