package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class P20920 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> wordBook = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            if (input.length() < m) {
                continue;
            }
            if (wordBook.containsKey(input)) {
                int count = wordBook.get(input);
                wordBook.replace(input, count + 1);
            } else {
                wordBook.put(input, 1);
            }
        }
        List<String> result = new ArrayList<>(wordBook.keySet());
        result.sort((o1, o2) -> {
            int c1 = wordBook.get(o1);
            int c2 = wordBook.get(o2);
            if (c1 == c2) {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                } else {
                    return o2.length() - o1.length();
                }
            } else {
                return c2 - c1;
            }
        });
        for (String s : result) {
            System.out.println(s);
        }
    }

}
