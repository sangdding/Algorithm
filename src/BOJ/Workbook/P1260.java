package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1260 {

    static int n, m, v;
    static ArrayList<ArrayList<Integer>> input;
    static boolean[] visited;
    static StringBuilder dfsRes = new StringBuilder();
    static StringBuilder bfsRes = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        input = new ArrayList<>();
        visited = new boolean[n + 1];
        for (int i = 0; i < n + 1; i++) {
            input.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            input.get(start).add(end);
            input.get(end).add(start);
        }
        for (int i = 1; i < n + 1; i++) {
            Collections.sort(input.get(i));
        }
        dfs(v);
        bfs(v);
        System.out.println(dfsRes.toString());
        System.out.println(bfsRes.toString());
    }

    public static void bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[n + 1];
        queue.add(node);
        visited[node] = true;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            bfsRes.append(curr + " ");
            for (int i = 0; i < input.get(curr).size(); i++) {
                if (visited[input.get(curr).get(i)]) continue;
                queue.add(input.get(curr).get(i));
                visited[input.get(curr).get(i)] = true;
            }
        }
    }

    public static void dfs(int node) {
        visited[node] = true;
        dfsRes.append(node + " ");
        ArrayList<Integer> curr = input.get(node);
        for (int i = 0; i < curr.size(); i++) {
            if (visited[curr.get(i)]) {
                continue;
            }
            dfs(curr.get(i));
        }
    }
}
