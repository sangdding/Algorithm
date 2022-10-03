package BOJ.DP;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P10942 {

    static int N, M;
    static int[] num;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        num = new int[N];
        dp = new int[N + 1][N + 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
            dp[i][i] = 1;
            num[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            sb.append(isPalindrome(S, E)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int isPalindrome(int S, int E) {
        if (S >= E) {
            return 1;
        }
        if (dp[S][E] != -1) {
            return dp[S][E];
        }
        if (num[S - 1] == num[E - 1]) {
            return dp[S][E] = isPalindrome(S + 1, E - 1);
        }
        return 0;
    }
}
