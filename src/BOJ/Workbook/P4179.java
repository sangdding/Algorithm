package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P4179 {

    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        boolean[][] visited = new boolean[r][c];
        Queue<Pos> queue = new LinkedList<>();
        Queue<Pos> fire = new LinkedList<>();
        map = new char[r][c];
        int[][] fireMap = new int[r][c];
        int startY = 0;
        int startX = 0;
        for (int i = 0; i < r; i++) {
            String parse = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = parse.charAt(j);
                if (map[i][j] == 'J') {
                    startY = i;
                    startX = j;
                    queue.add(new Pos(i, j, 0));

                } else if (map[i][j] == 'F') {
                    fire.add(new Pos(i, j, 0));
                }
            }
        }
        while (!fire.isEmpty()) {
            Pos curr = fire.poll();
            fireMap[curr.y][curr.x] = curr.time;
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx >= c || nx < 0 || ny >= r || ny < 0) continue;
                if (visited[ny][nx]) continue;
                if (map[ny][nx] != '#') {
                    visited[ny][nx] = true;
                    fire.add(new Pos(ny, nx, curr.time + 1));
                }
            }
        }
        visited = new boolean[r][c];
        boolean flag = false;
        visited[startY][startX] = true;
        while (!queue.isEmpty()) {
            Pos curr = queue.poll();
            if (curr.x == 0 || curr.x == c - 1 || curr.y == 0 || curr.y == r - 1) {
                System.out.println(curr.time + 1);
                flag = true;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx >= c || nx < 0 || ny >= r || ny < 0) continue;
                if (visited[ny][nx]) continue;
                if (map[ny][nx] == '#' || fireMap[ny][nx] <= curr.time + 1) continue;
                visited[ny][nx] = true;
                queue.add(new Pos(ny, nx, curr.time + 1));
            }
        }
        if (!flag) {
            System.out.println("IMPOSSIBLE");
        }
    }

    public static class Pos {
        int x;
        int y;
        int time;

        Pos(int y, int x, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
