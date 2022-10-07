package BOJ.Graph.DFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class P10026 {

    static int n;
    static char[][] map;
    static boolean[][] visited;
    static boolean[][] visitedRG;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new char[n][n];
        visited = new boolean[n][n];
        visitedRG = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String parse = new String(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = parse.charAt(j);
            }
        }

        int count = 0;
        int countRG = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, true);
                    count++;
                }
                if (!visitedRG[i][j]) {
                    dfs(i, j, false);
                    countRG++;
                }
            }
        }

        System.out.println(count + " " + countRG);
    }

    static void dfs(int y, int x, boolean flag) {
        if (flag) {
            visited[y][x] = true;
        } else {
            visitedRG[y][x] = true;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= n || ny >= n || nx < 0 || ny < 0) {
                continue;
            }
            if (flag) {
                if (visited[ny][nx]) {
                    continue;
                }
            } else {
                if (visitedRG[ny][nx]) {
                    continue;
                }
            }
            if (flag) {
                if (map[y][x] == map[ny][nx]) {
                    dfs(ny, nx, flag);
                }
            } else {
                if (map[y][x] == 'R' && map[ny][nx] == 'G') {
                    dfs(ny, nx, flag);
                } else if (map[y][x] == 'G' && map[ny][nx] == 'R') {
                    dfs(ny, nx, flag);
                } else if (map[y][x] == map[ny][nx]) {
                    dfs(ny, nx, flag);
                }
            }
        }
    }

}
