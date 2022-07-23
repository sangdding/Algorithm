package DFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P9633 {

    static int N;
    static int answer;
    static List<Integer> queen;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/DFS/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        queen = new ArrayList<>();
        DFS(0);
        System.out.println(answer);
    }
    static void DFS(int row) {
        // 2. 목적지인가?
        if (row == N) {
            answer++;
        } else {
            // 3. 연결된 곳 순회
            for (int i = 0; i < N; i++) {
                // 1. 체크인
                queen.add(i);
                // 4. 갈 수 있는가?
                if (isValid(row)) {
                    // 5. 간다
                    DFS(row + 1);
                }
                // 6. 체크아웃
                queen.remove(queen.size() - 1);
            }
        }
    }

    static boolean isValid(int row) {
        for (int i = 0; i < row; i++) {
            int diff = Math.abs(queen.get(i) - queen.get(row));
            if (diff == 0 || diff == row - i) return false;
        }
        return true;
    }
}
