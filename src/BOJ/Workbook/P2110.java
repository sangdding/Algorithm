package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2110 {

    static int n, c;
    static int[] home;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        home = new int[n];
        for (int i = 0; i < n; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(home);

        // 목적 : 공유기의 gap을 최대로 하는 지점 => gap의 최대 => 어쨌든 gap으로 탐색 해야함 => 시작은 중간값으로
        int left = 1;
        int right = home[n - 1] - home[0];
        int ans = -1;
        while (left <= right) {
            int count = 1;
            int mid = (left + right) / 2;
            int beforeHome = 0;
            // gap이 가능한지 확인한다.
            for (int i = 1; i < n; i++) {
                if (home[i] - home[beforeHome] >= mid) {
                    count++;
                    beforeHome = i;
                }
            }
            if (count >= c) {
                left = mid + 1;
                ans = mid;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(ans);
    }

}
