package BOJ.DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P14002 {

    static int N;
    static int[] A, cache, choices;
    static List<Integer> result;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        cache = new int[N + 1];
        choices = new int[N + 1];
        result = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(cache, -1);
        Arrays.fill(choices, -1);
        System.out.println(lis4(-1) - 1);
        reconstruct(-1);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }

    static void reconstruct(int start) {
        if (start != -1) result.add(A[start]);
        int next = choices[start + 1];
        if (next != -1) reconstruct(next);
    }

    static int lis4(int start) {
        if (cache[start + 1] != -1) return cache[start + 1];
        cache[start + 1] = 1;
        int bestNext = -1;
        for (int i = start + 1; i < N; ++i) {
            if (start == -1 || A[start] < A[i]) {
                int cand = lis4(i) + 1;
                if(cand > cache[start + 1]) {
                    cache[start + 1] = cand;
                    bestNext = i;
                }
            }
        }
        choices[start + 1] = bestNext;
        return cache[start + 1];
    }
}
