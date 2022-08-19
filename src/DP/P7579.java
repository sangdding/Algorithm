package DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P7579 {

    static int N, M;
    static int[] m, c;
    static int ans = 20000;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        m = new int[N + 1];
        c = new int[N + 1];

        StringTokenizer memory = new StringTokenizer(br.readLine());
        StringTokenizer cost = new StringTokenizer(br.readLine());

        int col = 0; // 입력받은 앱 비용의 총 합.
        for (int i = 1; i <= N; i++) {
            m[i] = Integer.parseInt(memory.nextToken());
            c[i] = Integer.parseInt(cost.nextToken());
            col += c[i];
        }

        long[][] dp = new long[N + 1][col + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= col; j++) {
                if (j - c[i] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - c[i]] + m[i]);
                    /*
                    dp[i-1][j] : i - 1개의 앱을 사용해서 j만큼의 비용을 소모하는 앱 리스트 중 최대 메모리 값.
                    dp[i-1][j] = dp[i][j] 같다면? 이게 뭘 의미하지?
                    dp[i-1][j-c[i]] : i - 1개의 앱을 사용해서 현재 선택하려는 앱의 비용을 뺀 값을 소모하는 앱 리스트 중 최대 메모리 값.
                     */
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
                if (dp[i][j] >= M) {
                    ans = Math.min(ans, j);
                }
            }
        }
        System.out.println(ans);
    }
 }