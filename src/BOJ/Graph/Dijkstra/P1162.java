package BOJ.Graph.Dijkstra;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1162 {

    static int N, M, K;
    static boolean[][] visited;
    static List<List<Rode>> input;
    static PriorityQueue<Rode> pq;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        input = new ArrayList<>();
        visited = new boolean[K + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            input.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int city = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            input.get(city).add(new Rode(end, time, 0));
            input.get(end).add(new Rode(city, time, 0));
        }

        System.out.println(dijkstra());
    }

    static long dijkstra() {
        long[][] dist = new long[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }
        dist[1][0] = 0;
        pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.time));
        pq.add(new Rode(1, 0, 0));

        while (!pq.isEmpty()) {
            Rode curr = pq.poll();
            if (curr.time > dist[curr.end][curr.num]) {
                continue;
            }
            for (Rode next : input.get(curr.end)) {
                if (curr.num < K && dist[next.end][curr.num + 1] > dist[curr.end][curr.num]) {
                    dist[next.end][curr.num + 1] = dist[curr.end][curr.num];
                    pq.add(new Rode(next.end, dist[next.end][curr.num + 1], curr.num + 1));
                }

                if (dist[next.end][curr.num] > dist[curr.end][curr.num] + next.time) {
                    dist[next.end][curr.num] = dist[curr.end][curr.num] + next.time;
                    pq.add(new Rode(next.end, dist[next.end][curr.num], curr.num));
                }
            }
        }
        return Arrays.stream(dist[N]).min().getAsLong();
    }

    static class Rode {
        int end;
        long time;
        int num;

        Rode(int end, long time, int num) {
            this.end = end;
            this.time = time;
            this.num = num;
        }
    }
}
