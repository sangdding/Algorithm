package BOJ.DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P11053 {

    static int N;
    static int[] cache, S;
    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        cache = new int[N + 1];
        S = new int[N];
        Arrays.fill(cache, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        int maxLen = 0;
        for (int begin = 0; begin < N; ++begin) {
            maxLen = Math.max(maxLen, lis2(begin));
        }
        System.out.println(maxLen);
        Arrays.fill(cache, -1);
        System.out.println(lis3(-1) - 1);
    }

    static int lis2(int start) {
        if (cache[start] != -1) {
            return cache[start];
        }
        cache[start] = 1;
        for (int next = start + 1; next < N; ++next) {
            if (S[start] < S[next]) {
                cache[start] = Math.max(cache[start], lis2(next) + 1);
            }
        }
        return cache[start];
    }

    static int lis3(int start) {
        if (cache[start + 1] != -1) {
            return cache[start + 1];
        }
        cache[start + 1] = 1;
        for (int next = start + 1; next < N; ++next) {
            if (start == -1 || S[start] < S[next]) {
                cache[start + 1] = Math.max(cache[start + 1], lis3(next) + 1);
            }
        }
        return cache[start + 1];
    }
}