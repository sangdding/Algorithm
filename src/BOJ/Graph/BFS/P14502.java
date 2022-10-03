package BOJ.Graph.BFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P14502 {

    static int N, M;
    static int[][] board;
    static int[][] copyMap;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static Queue<Virus> queue;
    static int max;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        max = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                board[i][j] = num;
            }
        }
        dfs(0);
        System.out.println(max);
    }

    static void dfs(int depth) {
        if (depth == 3) {
            bfs();
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 1;
                    dfs(depth + 1);
                    board[i][j] = 0;
                }
            }
        }
    }

    static void bfs() {

        copyMap = new int[N][M];
        queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = board[i][j];
                if (copyMap[i][j] == 2) {
                    queue.add(new Virus(j, i));
                }
            }
        }

        while (!queue.isEmpty()) {
            Virus curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
                    if (copyMap[ny][nx] == 0) {
                        copyMap[ny][nx] = 2;
                        queue.add(new Virus(nx, ny));
                    }
                }
            }
        }

        int area = M * N;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 2 || copyMap[i][j] == 1) {
                    area--;
                }
            }
        }

        max = Math.max(area, max);
    }

    static class Virus {
        int x;
        int y;

        Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
