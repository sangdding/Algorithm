package Samsung;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P13458 {

    static int N, B, C;
    static int[] A;
    static long sum;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        sum = N;
        for (int i = 0; i < N; i++) {
            A[i] -= B;
            if (A[i] <= 0) {
                continue;
            }
            if (A[i] % C == 0) {
                sum += A[i] / C;
            } else {
                sum += A[i] / C + 1;
            }
        }
        System.out.println(sum);
    }
}
