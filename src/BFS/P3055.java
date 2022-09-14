package BFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P3055 {

    static int R, C;
    static char[][] map;
    static int[][] water;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<Object> queueHed;
    static Queue<Object> queueWat;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        water = new int[R][C];
        visited = new boolean[R][C];
        queueHed = new LinkedList<>();
        queueWat = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            String temp = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = temp.charAt(j);
                if (map[i][j] == 'S') {
                    queueHed.add(new Object(j, i, 0));
                }
                if (map[i][j] == '*') {
                    queueWat.add(new Object(j, i, 0));
                    water[i][j] = 0;
                }
            }
            Arrays.fill(water[i], Integer.MAX_VALUE);
        }

        while (!queueWat.isEmpty()) {
            Object curr = queueWat.poll();
            int x = curr.x;
            int y = curr.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (ny >= 0 && nx >= 0 && ny < R && nx < C) {
                    if (map[ny][nx] == '.' && water[ny][nx] == Integer.MAX_VALUE) {
                        water[ny][nx] = curr.time + 1;
                        queueWat.add(new Object(nx, ny, curr.time + 1));
                    }
                }
            }
        }

        boolean flag = false;

        while (!queueHed.isEmpty() && !flag) {
            Object curr = queueHed.poll();
            int x = curr.x;
            int y = curr.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (ny >= 0 && nx >= 0 && ny < R && nx < C) {
                    if (map[ny][nx] == '.' && water[ny][nx] > curr.time + 1 && !visited[ny][nx]) {
                        queueHed.add(new Object(nx, ny, curr.time + 1));
                        visited[ny][nx] = true;
                    } else if (map[ny][nx] == 'D') {
                        System.out.println(curr.time + 1);
                        flag = true;
                        break;
                    }
                }
            }
        }
        if (!flag) {
            System.out.println("KAKTUS");
        }
    }

    static class Object {
        int x;
        int y;
        int time;

        Object(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
