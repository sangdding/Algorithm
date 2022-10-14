package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P22233 {

    static int n, m;
    static Map<String, String> keyword;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        keyword = new HashMap<>();

        for (int i = 0; i < n; i++) {
            keyword.put(br.readLine(), "");
        }

        for (int i = 0; i < m; i++) {
            String[] parse = br.readLine().split(",");
            for (String word :
                    parse) {
                keyword.computeIfPresent(word, (ke, ve) -> null);
            }
            sb.append(keyword.size()).append("\n");
        }
        System.out.println(sb.toString());
    }
}