package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2467 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] =Integer.parseInt(st.nextToken());
        }
        int s = 0;
        int e = input.length - 1;
        int left = -1;
        int right = -1;
        long cmp = Long.MAX_VALUE;
        while (s < e) {
            int cal = Math.abs(input[s] + input[e]);
            if (cmp > cal) {
                left = s;
                right = e;
                cmp = cal;
            }
            if (Math.abs(input[s]) < Math.abs(input[e])) {
                e--;
            } else {
                s++;
            }
        }
        System.out.println(input[left] + " " + input[right]);
    }
}
