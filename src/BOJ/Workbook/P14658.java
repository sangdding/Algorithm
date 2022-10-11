package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P14658 {

    static int n, m, l, k;
    static List<Pos> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int res = Integer.MIN_VALUE;
        for (Pos star1 :
                list) {
            for (Pos star2 :
                    list) {
                res = Math.max(res, check(star1.x, star2.y));
            }
        }
        System.out.println(k - res);
    }

    public static int check(int a, int b) {
        int res = 0;
        for (Pos star :
                list) {
            if (a <= star.x && star.x <= a + l && b <= star.y && star.y <= b + l) res++;
        }
        return res;
    }

    public static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
