package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P3687 {

    /*
    {1:2, 2:5, 3:5, 4:4, 5:5, 6:6, 7:3, 8:7, 9:6, 0:6}
    성냥개비 1개일때 : 없음
    성냥개비 2개일때 : 1, 1
    성냥개비 3개일 때 : 7, 7
    성냥개비 4개일 때 : 11, 4
    성냥개비 5개일 때 : 71, 2
    성냥개비 6개일 때 : 71, 6
    성냥개비 7개일 때 : 71, 8
    성냥개비 8개일 때 : 91, 10
    성냥개비 9개일 때 : 97, 18
    성냥개비 10개일 때 : 911, 22
    11 : 26 (5 + 6)
    12 : 28 (5 + 7)
    13 : 86 (6 + 7)
    14 : 88 (7 + 7)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        String[] dp = new String[101];
        dp[2] = "1";
        dp[3] = "7";
        StringBuilder sb;
        for (int i = 4; i <= 100; i++) {
            sb = new StringBuilder();
            dp[i] = sb.append(dp[i - 2]).append(1).toString();
        }
        Long[] minDp = new Long[101];
        Arrays.fill(minDp, Long.MAX_VALUE);
        Long[] init = {0L, 1L, 7L, 4L, 2L, 0L, 8L};
        minDp[0] = 0L;
        minDp[1] = 0L;
        minDp[2] = 1L;
        minDp[3] = 7L;
        minDp[4] = 4L;
        minDp[5] = 2L;
        minDp[6] = 6L;
        minDp[7] = 8L;
        minDp[8] = 10L;
        for (int i = 9; i <= 100; i++) {
            for (int j = 1; j <= 6; j++) {
                minDp[i] = Math.min(minDp[i], minDp[i - j - 1] * 10 + init[j]);
            }
        }
        sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int num = Integer.parseInt(br.readLine());
            sb.append(minDp[num] + " " + dp[num] + "\n");
        }
        System.out.print(sb);
    }
}
