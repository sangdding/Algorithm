package Contest.Kahee_1st;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P21773 {

    static int T, n;


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        PriorityQueue<Process> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            pq.add(new Process(A, B, C));
        }

        int time = 1;

        while (!pq.isEmpty()) {
            if (time > T) {
                break;
            }
            Process curr = pq.poll();
            System.out.println(curr.A);
            curr.B--;
            if (curr.B != 0) {
                curr.C--;
                pq.add(new Process(curr.A, curr.B, curr.C));
            }
            time++;
        }
    }

    static class Process implements Comparable<Process> {

        int A;
        int B;
        int C;

        Process(int A, int B, int C) {
            this.A = A;
            this.B = B;
            this.C = C;
        }

        @Override
        public int compareTo(Process o) {
            if (this.C < o.C) {
                return 1;
            } else if (this.C == o.C) {
                return this.A - o.A;
            } else {
                return -1;
            }
        }
    }
}
