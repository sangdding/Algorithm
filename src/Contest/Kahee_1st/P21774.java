package Contest.Kahee_1st;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class P21774 {

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> event = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        String[] parse;

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        String[] eventId = new String[n];
        int[] eventLv = new int[n];

        String[] queryS = new String[q];
        String[] queryE = new String[q];
        int[] queryLv = new int[q];

        int[][] arr = new int[7][n + 2 * q + 1];
        int[][] nj = new int[7][n + 2 * q + 1];
        event.add("");
        for (int i = 0; i < n; i++) {
            parse = br.readLine().split("#");
            eventId[i] = parse[0];
            eventLv[i] = Integer.parseInt(parse[1]);
            event.add(eventId[i]);
        }
        for (int i = 0; i < q; i++) {
            parse = br.readLine().split("#");
            queryS[i] = parse[0];
            event.add(parse[0]);
            queryE[i] = parse[1];
            event.add(parse[1]);
            queryLv[i] = Integer.parseInt(parse[2]);
        }
        Collections.sort(event);
        for (int i = 0; i < n; i++) {
            int lo = Collections.binarySearch(event, eventId[i]);
            arr[eventLv[i]][lo]++;
        }
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j < n + 2 * q; j++) {
                nj[i][j] = nj[i - 1][j] + nj[i][j - 1] - nj[i - 1][j - 1] + arr[i][j];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int s = Collections.binarySearch(event, queryS[i]);
            int e = Collections.binarySearch(event, queryE[i]);
            int lv = queryLv[i];

            int ans = calc(lv, s, e, nj);
            sb.append(ans + "\n");
        }
        System.out.println(sb.toString());
    }

    private static int calc(int x1, int y1, int y2, int[][] nj) {
        return nj[6][y2] - nj[x1 - 1][y2] - nj[6][y1 - 1] + nj[x1 - 1][y1 - 1];
    }
}
