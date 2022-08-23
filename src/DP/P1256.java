package DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1256 {

    static int N, M, K;
    static int[][] dp;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][M + 1];
        sb = new StringBuilder();

        if (check(N, M) < K) {
            System.out.println(-1);
        } else {
            make(N, M, K);
            System.out.println(sb.toString());
        }

    }

    public static int check(int a, int z) {
        if (a == 0 || z == 0) {
            return 1;
        }
        if (dp[a][z] != 0) {
            return dp[a][z];
        }
        return dp[a][z] = Math.min(check(a - 1, z) + check(a, z - 1), 1000000001);
    }

    public static void make(int a, int z, int k) {
        if (a == 0) {
            for (int i = 0; i < z; i++) {
                sb.append("z");
            }
            return;
        }
        if (z == 0) {
            for (int i = 0; i < a; i++) {
                sb.append("a");
            }
            return ;
        }
        int check = check(a - 1, z);
        if (k > check) {
            sb.append("z");
            make(a, z - 1, k - check);
        } else {
            sb.append("a");
            make(a - 1, z, k);
        }
    }
}
