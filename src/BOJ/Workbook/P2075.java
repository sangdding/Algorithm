package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P2075 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            int temp = Integer.parseInt(st.nextToken());
            pq.offer(temp);
        }

        for(int i=1; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                int temp = Integer.parseInt(st.nextToken());

                if(pq.peek() < temp) {  // 추가
                    pq.poll();
                    pq.offer(temp);
                }
            }
        }
        System.out.println(pq.poll());
    }
}
