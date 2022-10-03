package BOJ.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2096 {

    static int N;
    static int[][] input;
    static int[] maxDp;
    static int[] minDp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        input = new int[N][3];
        maxDp = new int[3];
        minDp = new int[3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
                if (i == 0) {
                    maxDp[j] = input[i][j];
                    minDp[j] = input[i][j];
                }
            }
            if(i != 0) {
                int temp_0 = maxDp[0];
                int temp_2 = maxDp[2];
                maxDp[0] = Math.max(maxDp[0], maxDp[1]) + input[i][0];
                maxDp[2] = Math.max(maxDp[1], maxDp[2]) + input[i][2];
                maxDp[1] = Math.max(Math.max(temp_0, maxDp[1]), temp_2) + input[i][1];
                temp_0 = minDp[0];
                temp_2 = minDp[2];
                minDp[2] = Math.min(minDp[1], minDp[2]) + input[i][2];
                minDp[0] = Math.min(minDp[0], minDp[1]) + input[i][0];
                minDp[1] = Math.min(Math.min(temp_0, minDp[1]), temp_2) + input[i][1];
            }
        }
        System.out.print(Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]) + " " + Math.min(Math.min(minDp[0], minDp[1]), minDp[2]));
    }
}
