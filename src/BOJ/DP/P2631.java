package BOJ.DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2631 {

    static int N;
    static int[] num;
    static int[] dp;
    static int max;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new int[N];
        dp = new int[N];
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }
        max = 0;
        for (int i = 0; i < N; i++) {
            dfs(i);
        }
        System.out.println(N - (max + 1));
    }

    static int dfs(int pos) {
        // 기저 조건
        if (pos == N - 1) {
            return 0;
        }
        if (dp[pos] != 0) {
            return dp[pos];
        }
        for (int i = pos + 1; i < N; i++) {
            if (num[pos] < num[i]) {
                dp[pos] = Math.max(dfs(i) + 1, dp[pos]);
                max = Math.max(dp[pos], max);
            }
        }
        return dp[pos];
    }
}
