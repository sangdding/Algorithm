package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P1446 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Road> input = new ArrayList<>();
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int[] dp = new int[100001];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            input.add(new Road(start, end, length));
        }
        Arrays.fill(dp, 10001);
        dp[0] = 0;
        for (int i = 1; i < 10001; i++) {
            for (Road curr : input) {
                if (curr.end == i) {
                    if (dp[curr.start] + curr.length < dp[i]) {
                        dp[i] = dp[curr.start] + curr.length;
                    }
                }
            }
            if (dp[i] == 10001 || dp[i] > dp[i - 1] + 1) {
                dp[i] = dp[i - 1] + 1;
            }
        }
        System.out.println(dp[d]);
    }

    public static class Road {
        int start;
        int end;
        int length;

        public Road(int start, int end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }
    }
}
