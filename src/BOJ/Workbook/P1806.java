package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1806 {

    public static void main(String[] args) throws IOException {
        int n, s;
        int[] list = new int[100001];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
           list[i] += Integer.parseInt(st.nextToken());
        }
        int min = Integer.MAX_VALUE;
        int l = 0;
        int r = 0;
        int sum = 0;
        while (true) {
            if (s <= sum) {
                sum -= list[l];
                min = Math.min(min, r - l);
                l++;
            } else if (r == n) {
                break;
            } else {
                sum += list[r];
                r++;
            }
        }
        System.out.println(min != Integer.MAX_VALUE ? min : 0);
    }
}
