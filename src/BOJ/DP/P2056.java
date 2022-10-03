package BOJ.DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2056 {

    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        dp = new int[N];
        int ans = 0;
        for (int i = 0; i < N; i++) {
            int max = 0;
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int adv = Integer.parseInt(st.nextToken());
            for (int j = 0; j < adv; j++) {
                int advJob = Integer.parseInt(st.nextToken());
                max = Math.max(dp[advJob - 1], max);
            }
            dp[i] = time + max;
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }

}
