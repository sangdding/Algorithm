package DP;

import java.io.*;
import java.util.*;

public class P1516 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> input = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            input.add(new ArrayList<>());
        }

        int[] indegree = new int[N + 1];
        int[] times = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int num = Integer.parseInt(st.nextToken());
                if (num == -1) {
                    break;
                }
                input.get(num).add(i);
                indegree[i]++;
            }
        }

        String ans = topoSort(input, indegree, times, N);

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static String topoSort(ArrayList<ArrayList<Integer>> input, int[] indegree, int[] times, int N) {
        Queue<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int[] result = new int[N + 1];

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next :
                    input.get(now)) {
                indegree[next]--;
                result[next] = Math.max(result[next], result[now] + times[now]);
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            sb.append((result[i] + times[i]) + "\n");
        }

        return sb.toString();
    }

}