package DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1937 {

    static int n;
    static int max;
    static int[][] map, dp;
    static int[][] move = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][n];
        max = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(i, j));
            }
        }

        System.out.println(max);
    }

    static int dfs(int y, int x) {
        if (dp[y][x] != 0) {
            return dp[y][x];
        }

        dp[y][x] = 1;

        for (int i = 0; i < 4; i++) {
            if (y + move[i][0] < n && y + move[i][0] >= 0 && x + move[i][1] < n && x + move[i][1] >= 0) {
                int nextY = y + move[i][0];
                int nextX = x + move[i][1];
                if (map[y][x] < map[nextY][nextX]) {
                    dp[y][x] = Math.max(dp[y][x], dfs(nextY, nextX) + 1);
                    dfs(nextY, nextX);
                }
            }
        }

        return dp[y][x];
    }
}
