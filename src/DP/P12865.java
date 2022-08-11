package DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P12865 {

    static int N, K, capacity;
    static int[][] cache;
    static ArrayList<BackPack> input;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        input = new ArrayList<>();
        cache = new int[K + 1][N];
        for (int i = 0; i <= K; i++) {
            Arrays.fill(cache[i], -1);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            input.add(new BackPack(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        System.out.println(pack(K, 0));
    }

    static int pack(int capacity, int item) {
        if(item == N) return 0; // 더 이상 담을 물건이 없을 때
        if(cache[capacity][item] != -1) return cache[capacity][item];
        cache[capacity][item] = pack(capacity, item + 1);
        if(capacity >= input.get(item).W) {
            cache[capacity][item] = Math.max(cache[capacity][item], pack(capacity - input.get(item).W, item + 1) + input.get(item).V);
        }
        return cache[capacity][item];
    }

    static class BackPack {
        int W;
        int V;

        BackPack(int W, int V) {
            this.W = W;
            this.V = V;
        }
    }
}
