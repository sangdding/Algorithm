package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P13549 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] visited = new int[100001];
        Arrays.fill(visited, Integer.MAX_VALUE);
        System.out.println(bfs(n, k, visited));
        System.out.println();
    }

    public static int bfs(int n, int k, int[] value) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        queue.add(n);
        value[n] = 0;
        while (!queue.isEmpty()) {
            int pos = queue.poll();
            visited[pos] = true;
            if (pos == k) {
                return value[k];
            }
            int[] next = {pos * 2, pos + 1, pos - 1};
            for (int i = 0; i < 3; i++) {
                if (next[i] < 0 || next[i] > 100000 || visited[next[i]]) {
                    continue;
                }
                if (i == 0) {
                    value[next[i]] = Math.min(value[pos], value[next[i]]);
                } else {
                    value[next[i]] = Math.min(value[pos] + 1, value[next[i]]);
                }
                queue.add(next[i]);
            }
        }
        return -1;
    }

}
