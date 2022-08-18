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

            for (int i = 4; i <= N; i += 2) {
                cache[i] += cache[i - 2] * 3 + 2;
                for (int j = i - 4; j >= 2; j -= 2) {
                    cache[i] += cache[j] * 2;
                }
            }
            System.out.println(cache[N]);
        }
    }
}
