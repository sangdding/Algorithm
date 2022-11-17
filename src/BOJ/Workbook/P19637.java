package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P19637 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<Power> input = new ArrayList<>();
        input.add(new Power("INIT", -1));
        for (int i = 1; i <= n; i++) {
            String[] parse = br.readLine().split(" ");
            String title = parse[0];
            int maxPower = Integer.parseInt(parse[1]);
            if (input.get(input.size() - 1).maxPower == maxPower) continue;
            input.add(new Power(parse[0], Integer.parseInt(parse[1])));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int power = Integer.parseInt(br.readLine());
            int s = 1;
            int e = input.size() - 1;
            while (s <= e) {
                int mid = (s + e) / 2;
                Power curr = input.get(mid);
                if (curr.maxPower < power) {
                    s = mid + 1;
                } else {
                    e = mid - 1;
                }
            }
            sb.append(input.get(s).title + "\n");
        }
        System.out.print(sb);
    }

    public static class Power {
        String title;
        int maxPower;

        public Power(String title, int maxPower) {
            this.title = title;
            this.maxPower = maxPower;
        }
    }
}
