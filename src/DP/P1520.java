package DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1520 {

    static int N, M;
    static int[][] map;
    static int[][] dp;
    static int[][] pos = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0] = 1;
        dfs(0, 0);
        System.out.println(dp[M - 1][N - 1]);
    }

    static void dfs(int y, int x) {
        for (int i = 0; i < 4; i++) {
            int nextY = y + pos[i][0];
            int nextX = x + pos[i][1];
            if (nextY >= 0 && nextY < M && nextX >= 0 && nextX < N) {
                if (map[nextY][nextX] < map[y][x]) {
                    if (dp[nextY][nextX] == 0) {
                        dp[nextY][nextX]++;
                        dfs(nextY, nextX);
                    }
                }
            }
        }
    }
}
