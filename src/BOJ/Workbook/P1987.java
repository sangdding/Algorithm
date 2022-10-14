package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1987 {

    static int r, c;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static char[][] map;
    static boolean[] check;
    static boolean[][] visited;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        check = new boolean[28];
        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            String parse = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = parse.charAt(j);
            }
        }
        dfs(0, 0, 1);
        System.out.println(max);
    }

    public static void dfs(int x, int y, int cnt) {
        visited[y][x] = true;
        check[map[y][x] - 'A'] = true;
        max = Math.max(cnt, max);
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= c || ny >= r || nx < 0 || ny < 0) continue;
            if (check[map[ny][nx] - 'A']) continue;
            dfs(nx, ny, cnt + 1);
        }
        visited[y][x] = false;
        check[map[y][x] - 'A'] = false;
    }

}
