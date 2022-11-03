package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14719 {

    static int h, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        int[] input = new int[w];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        int ans = 0;
        for (int i = 1; i < w - 1; i++) {
            int current = input[i];
            int leftMax = current;
            int rightMax = current;
            for (int j = i - 1; j >= 0; j--) {
                if (input[j] > current) {
                    leftMax = Math.max(leftMax, input[j]);
                }
            }
            for (int j = i + 1; j < w; j++) {
                if (input[j] > current) {
                    rightMax = Math.max(rightMax, input[j]);
                }
            }
            if (Math.min(leftMax, rightMax) > current) {
                ans += (Math.min(leftMax, rightMax) - input[i]);
            }
        }
        System.out.println(ans);
    }

}
