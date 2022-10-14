package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2531 {

    static int n, d, k, c;
    static int[] sushi;
    static int[] eat;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        sushi = new int[n];
        eat = new int[d + 1];

        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        for (int i = 0; i < k; i++) {
            if (eat[sushi[i]] == 0) count++;
            eat[sushi[i]]++;
        }
        int maxLen = count;
        for (int i = 1; i < n; i++) {
            if (maxLen <= count) {
                if (eat[c] == 0) {
                    maxLen = count + 1;
                } else {
                    maxLen = count;
                }
            }
            int end = (i + k - 1) % n;
            if (eat[sushi[end]] == 0) {
                count++;
            }
            eat[sushi[end]]++;
            eat[sushi[i-1]]--;
            if (eat[sushi[i - 1]] == 0) {
                count--;
            }
        }
        System.out.println(maxLen);
    }

}
