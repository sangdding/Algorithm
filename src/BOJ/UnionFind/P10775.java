package BOJ.UnionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P10775 {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());
        parent = new int[g + 1];
        for (int i = 1; i <= g; i++) {
            parent[i] = i;
        }
        int ans = 0;
        for (int i = 0; i < p; i++) {
            int plane = Integer.parseInt(br.readLine());
            int emptyGate = find(plane);
            if (emptyGate == 0) {
                break;
            }
            ans++;
            union(emptyGate, emptyGate - 1);
        }
        System.out.println(ans);
    }

    public static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[x] = y;
        }
    }
}
