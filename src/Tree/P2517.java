package Tree;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2517 {

    static int N;
    static int S;
    static final long MAX = 10000;
    static int[] tree;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        S = 1;
        while (S < MAX) {
            S *= 2;
        }

        tree = new int[2 * S + 1];

        for (int i = 0; i < N; i++) {
            int ability = Integer.parseInt(br.readLine());
            update(1, S, 1, ability, 1);
            System.out.println(query(1, S, 1, ability + 1, S) + 1);
        }
    }

    static int query(int left, int right, int node, int start, int end) {
        if (left >= start && right <= end) {
            return tree[node];
        } else if (left >= start || right <= end) {
            int mid = (left + right) / 2;
            return query(left, mid, 2 * node, start, end) + query(mid * 2 + 1, right, 2 * node + 1, start, end);
        } else {
            return 0;
        }
    }

    static void update(int left, int right, int node, int index, int diff) {
        if (left == right) {
            tree[node] += diff;
        } else if (left <= index && right >= index) {
            int mid = (left + right) / 2;
            update(left, mid, 2 * node, index, diff);
            update(mid + 1, right, 2 * node + 1, index, diff);
        }
    }
}
