package Dijkstra;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1238 {

    static int N, M, X;
    static List<Node>[] goList;
    static List<Node>[] backList;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        goList = new ArrayList[N + 1];
        backList = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            goList[i] = new ArrayList<>();
            backList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            goList[start].add(new Node(end, cost));
            backList[end].add(new Node(start, cost));
        }

        int[] go = dijkstra(goList, X);
        int[] back = dijkstra(backList, X);
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < N + 1; i++) {
            int dis = go[i] + back[i];
            if (dis > res) {
                res = dis;
            }
        }
        System.out.println(res);
    }

    static int[] dijkstra(List<Node>[] list, int start) {
        Queue<Node> queue = new PriorityQueue<>();
        boolean[] check = new boolean[N + 1];
        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        queue.add(new Node(start, 0));
        dp[start] = 0;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            int end = curr.end;
            if (check[end]) continue;
            check[end] = true;
            for (Node next : list[end]) {
                if (dp[end] + next.cost < dp[next.end]) {
                    dp[next.end] = dp[end] + next.cost;
                    queue.add(new Node(next.end, dp[next.end]));
                }
            }
        }
        return dp;
    }

    static class Node implements Comparable<Node> {

        int end;
        int cost;

        Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
