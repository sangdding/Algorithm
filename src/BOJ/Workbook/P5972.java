package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P5972 {

    static int n, m;
    static boolean[] visited;
    static int[] dist;
    static List<ArrayList<Road>> input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        input = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            input.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cow = Integer.parseInt(st.nextToken());
            input.get(start).add(new Road(end, cow));
            input.get(end).add(new Road(start, cow));
        }
        visited = new boolean[n + 1];
        dist = new int[n + 1];
        Arrays.fill(dist, 50000001);
        dijkstra();
        System.out.println(dist[n]);
    }

    public static void dijkstra() {
        PriorityQueue<Road> pq = new PriorityQueue<>();
        dist[1] = 0;
        pq.offer(new Road(1, 0));
        while (!pq.isEmpty()) {
            Road curr = pq.poll();
            if (!visited[curr.end]) visited[curr.end] = true;
            else continue;
            for (int i = 0; i < input.get(curr.end).size(); i++) {
                Road next = input.get(curr.end).get(i);
                if (dist[next.end] > dist[curr.end] + next.cow) {
                    dist[next.end] = dist[curr.end] + next.cow;
                    pq.add(new Road(next.end, dist[next.end]));
                }
            }
        }
    }

    public static class Road implements Comparable<Road> {
        int end;
        int cow;

        @Override
        public int compareTo(Road o) {
            return this.cow - o.cow;
        }

        public Road(int end, int cow) {
            this.end = end;
            this.cow = cow;;
        }
    }
}
