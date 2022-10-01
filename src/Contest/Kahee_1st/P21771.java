package Contest.Kahee_1st;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P21771 {

    static int R, C;
    static String[] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int Rg = Integer.parseInt(st.nextToken());
        int Cg = Integer.parseInt(st.nextToken());
        int Rp = Integer.parseInt(st.nextToken());
        int Cp = Integer.parseInt(st.nextToken());

        map = new String[R];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 배게를 처음으로 만났다면
                if (map[i].charAt(j) == 'P') {
                    System.out.println(check(i, j, Rp, Cp));
                    System.exit(0);
                }
            }
        }
    }

    static int check(int y, int x, int row, int col) {
        if (y + row > R || x + col > C) {
            return 1;
        }
        for (int i = y; i < row; i++) {
            for (int j = x; j < col; j++) {
                if (map[i].charAt(j) == 'P') {
                    continue;
                }
                return 1;
            }
        }
        return 0;
    }
}
