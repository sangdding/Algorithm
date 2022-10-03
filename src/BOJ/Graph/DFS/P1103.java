package BOJ.Graph.DFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1103 {

    static String[] board;
    static boolean[][] visited;
    static int[][] pos = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    static int N, M;
    static int count;
    static int max;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N][M];
        board = new String[N];

        // 보드 입력
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine();
        }

        count = 0;
        max = 0;
        dfs(0, 0);
        System.out.println(max);
    }

    static void dfs(int y, int x) {
        // 2. 목적지인가? : 구멍이거나 보드 바깥일 때, 방문했던 곳이라면 무한루프
        if (((y < 0 || y >= N) || (x < 0 || x >= M))) {
            max = Math.max(count, max);
            return ;
        } else if (board[y].charAt(x) == 'H') {
            max = Math.max(count, max);
            return ;
        }
        if (visited[y][x]) {
            System.out.println(-1);
            System.exit(0);
        }
        // 1. 체크인
        visited[y][x] = true;
        count++;
        // 3. 인접한 곳 순회
        for (int i = 0; i < 4; i++) {
            int nextY = y + pos[i][1];
            int nextX = x + pos[i][0];
            // 4. 갈 수 있는가? : 한 칸 옮겼을 때 map 내부이면
            if (nextX < M && nextX >= 0 && nextY < N && nextY >= 0) {
                // 5. 간다
                nextX = x + pos[i][0] * Character.getNumericValue(board[y].charAt(x));
                nextY = y + pos[i][1] * Character.getNumericValue(board[y].charAt(x));
                dfs(nextY, nextX);
            }
        }
        // 6. 체크아웃
        visited[y][x] = false;
        count--;
    }
}
