package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P9655 {

    /*
    이기는 사람
    마지막 돌을 가져가는 경우의 수
    돌이 하나 남게 만들면 된다.

    돌이 하나일 때 : 상근
    돌이 두개일 때 : 창영
    돌이 세개일 때 : 상근
    돌이 네개일 때 : 창영
    돌이 다섯개일 때 : 돌 4개 돌 2개 reverse
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[1001];
        dp[1] = -1;
        dp[2] = 1;
        dp[3] = -1;
        dp[4] = 1;
        if (n > 4) {
            for (int i = 5; i <= n; i++) {
                dp[i] = dp[i - 3] * -1;
            }
        }
        System.out.println(dp[n] == 1 ? "CY" : "SK");
    }
}
