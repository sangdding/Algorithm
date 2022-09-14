package BFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P16236 {

    static int N;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static Queue<Shark> queue;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    queue.add(new Shark(j, i, 2, 0));
                }
            }
        }

        while (!queue.isEmpty()) {
            Shark curr = queue.poll();
            int x = curr.x;
            int y = curr.y;
            for (int i = 0; i < 4; i++) {

            }
        }

    }

    static class Shark {
        int x;
        int y;
        int size;
        int count;
        int time;

        Shark(int x, int y, int size, int count) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.count = count;
        }
    }

}
