package BOJ.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2098 {

    static int N;
    static int[][] W;
    static int[][] dp;
    static int INF = 20000000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        W = new int[N][N];
        dp = new int[N][1 << N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], INF);
        }

        System.out.println(TSP(0, 1));
    }

    static int TSP(int id, int visited) {
        if (visited == (1 << N) - 1) {
            return dp[id][visited] = W[id][0] != 0 ? W[id][0] : INF;
        }
        if (dp[id][visited] != INF) return dp[id][visited];

        for (int i = 0; i < N; i++) {
            int next = visited | (1 << i);
            if (W[id][i] == 0 || (visited & (1 << i)) != 0) {
                continue;
            }
            dp[id][visited] = Math.min(dp[id][visited], TSP(i, next) + W[id][i]);
        }
        return dp[id][visited];
    }
}