package BOJ.Graph.BFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1194 {

    static int N, M;
    static String[] map;
    static Queue<Node> queue;
    static boolean[][][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        queue = new LinkedList<>();
        visited = new boolean[64][N][M];
        map = new String[N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine();
            for (int j = 0; j < M; j++) {
                if (map[i].charAt(j) == '0') {
                    queue.add(new Node(i, j, 0, 0));
                    visited[0][i][j] = true;
                }
            }
        }

        int result = -1;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (map[curr.y].charAt(curr.x) == '1') {
                result = curr.count;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < M && ny < N && !visited[curr.key][ny][nx]) {
                    if (map[ny].charAt(nx) == '.' || map[ny].charAt(nx) == '0' || map[ny].charAt(nx) == '1') {
                        queue.add(new Node(ny, nx, curr.count + 1, curr.key));
                        visited[curr.key][ny][nx] = true;
                    } else if (map[ny].charAt(nx) >= 'a' && map[ny].charAt(nx) <= 'f') {
                        int code = (int) map[ny].charAt(nx) - 97;
                        code = (int) Math.pow(2, code);
                        if ((curr.key & code) == 0) {
                            code += curr.key;
                        } else {
                            code = curr.key;
                        }
                        visited[code][ny][nx] = true;
                        queue.add(new Node(ny, nx, curr.count + 1, code));
                    } else if (map[ny].charAt(nx) >= 'A' && map[ny].charAt(nx) <= 'F') {
                        int code = (int) map[ny].charAt(nx) - 65;
                        code = (int) Math.pow(2, code);
                        if ((curr.key & code) == 0) {
                            continue;
                        }
                        visited[curr.key][ny][nx] = true;
                        queue.add(new Node(ny, nx, curr.count + 1, curr.key));
                    }
                }
            }
        }
        System.out.println(result);
    }

    static class Node {
        int x;
        int y;
        int count;
        int key;

        Node(int y, int x, int count, int key) {
            this.y = y;
            this.x = x;
            this.count = count;
            this.key = key;
        }
    }
}
