package BOJ.Graph.BFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2146 {

    static int N;
    static int[][] map;
    static boolean[][] check;
    static Queue<Node> queue;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        map = new int[N][N];
        check = new boolean[N][N];
        queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        getContinent();

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0) {
                    check = new boolean[N][N];
                    int res = getDistance(i, j);
                    if (res == -1) {
                        continue;
                    }
                    if (min > res) {
                        min = res;
                    }
                }
            }
        }
        System.out.println(min - 1);
    }


    static void getContinent() {
        int id = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!check[i][j] && map[i][j] != 0) {
                    map[i][j] = id;
                    check[i][j] = true;
                    queue.add(new Node(i, j));
                    while (!queue.isEmpty()) {
                        Node curr = queue.poll();
                        int x = curr.x;
                        int y = curr.y;
                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];
                            if (nx >= 0 && ny >= 0 && nx < N && ny < N && !check[ny][nx]) {
                                if (map[ny][nx] == 1) {
                                    check[ny][nx] = true;
                                    map[ny][nx] = id;
                                    queue.add(new Node(ny, nx));
                                }
                            }
                        }
                    }
                    id++;
                }
            }
        }
    }

    static int getDistance(int y, int x) {
        queue = new LinkedList<>();
        int num = map[y][x];
        check[y][x] = true;
        queue.add(new Node(y, x, 0));
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (map[curr.y][curr.x] != 0 && map[curr.y][curr.x] != num) {
                return curr.distance;
            }
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !check[ny][nx] && map[ny][nx] != num) {
                    check[ny][nx] = true;
                    queue.add(new Node(ny, nx, curr.distance + 1));
                }
            }
        }
        return -1;
    }

    static class Node {

        int x;
        int y;
        int distance;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        Node(int y, int x, int distance) {
            this.y = y;
            this.x = x;
            this.distance = distance;
        }

    }
}
