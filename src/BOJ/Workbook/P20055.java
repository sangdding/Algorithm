package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class P20055 {

    static int n, k;
    static int[] belt;
    static List<Integer> robot;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        belt = new int[2 * n + 1];
        robot = new LinkedList<>();
        check = new boolean[2 * n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 2 * n; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0;
        int on = 1;
        int off = n;
        int start = 1;
        robot.add(1);
        check[1] = true;
        belt[1]--;
        while (cnt < k) {
            // 한 칸 회전하기
            if (on == 1) {
                on = 2 * n;
                off--;
            } else if (off == 1) {
                off = 2 * n;
                on--;
            } else {
                on--;
                off--;
            }
            // 로봇 이동하기
        }
    }

}
