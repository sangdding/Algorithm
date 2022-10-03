package BOJ.Graph.DFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P22352 {

    static int n, m, count;
    static int[][] prevMap, afterMap;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        prevMap = new int[n][m];
        afterMap = new int[n][m];
        visited = new boolean[n][m];

        // 백신을 놓기 전 촬영 결과
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                prevMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 백신을 놓은 후 촬영 결과
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                afterMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean flag = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (prevMap[i][j] != afterMap[i][j]) {
                    dfs(i, j, prevMap[i][j], afterMap[i][j]);
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (prevMap[i][j] != afterMap[i][j]) {
                    System.out.println("NO");
                    System.exit(0);
                }
            }
        }

        System.out.println("YES");
    }

    static void dfs(int y, int x, int preVal, int afterVal) {
        visited[y][x] = true;
        prevMap[y][x] = afterVal;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 범위를 벗어나거나 방문한 적이 있다면
            if (nx >= m || ny >= n || nx < 0 || ny < 0 || visited[ny][nx]) continue;
            // data value가 다르다면
            if (prevMap[ny][nx] != preVal) continue;
            dfs(ny, nx, preVal, afterVal);
        }
    }

}
