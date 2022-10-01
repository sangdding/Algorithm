package Contest.Kahee_1st;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P21772 {

    static int R, C, T;
    static int max;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        int Rg = 0, Cg = 0;

        for (int i = 0; i < R; i++) {
            String temp = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = temp.charAt(j);
                if (map[i][j] == 'G') {
                    Rg = i;
                    Cg = j;
                }
            }
        }
        max = Integer.MIN_VALUE;
        dfs(Rg, Cg, 0, 0);
        System.out.println(max);
    }

    static void dfs(int y, int x, int count, int time) {
        if (time == T) {
            max = Math.max(count, max);
            return ;
        }
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < R && nx < C && ny >= 0 && nx >= 0 && map[ny][nx] != '#') {
                if (map[ny][nx] == 'S') {
                    map[ny][nx] = '.';
                    dfs(ny, nx, count + 1, time + 1);
                    map[ny][nx] = 'S';
                } else {
                    dfs(ny, nx, count, time + 1);
                }
            }
        }
    }
}
