package DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2133 {

    static int N;
    static int[] cache;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cache = new int[N + 1];
        if (N % 2 != 0) {
            System.out.println(0);
        } else {
            cache[0] = 1;
            cache[1] = 1;
            cache[2] = 3;
            System.out.println(dp(N));
        }
    }

    static int dp(int pos) {
        if (pos <= 2) {
            return cache[pos];
        }
        if (cache[pos] != 0) {
            return cache[pos];
        }
        return cache[pos] = dp(pos - 4) * 2 + dp(pos - 2) * 3 + 2;
    }
}
