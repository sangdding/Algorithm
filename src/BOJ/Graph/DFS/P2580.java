package BOJ.Graph.DFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P2580 {

    static int[][] board;
    static boolean[][] visited;
    static List<int[]> blank;
    static boolean[] hasNum;
    static int count;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        visited = new boolean[9][9];
        board = new int[9][9];
        blank = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    blank.add(new int[]{i, j});
                }
            }
        }

        dfs(blank.get(count)[0], blank.get(count)[1]);
    }

    static void dfs(int y, int x) {
        // 3. 인접 방향 순회
        for (int i = 1; i <= 9; i++) {
            // 1. 체크인
            visited[y][x] = true;
            count++;
            board[y][x] = i;
            // 4. 갈 수 있는가?
            if (checkCol(x) && checkRect(y, x) && checkRow(y)) {
                // 2. 목적지인가? : blank의 모든 요소를 다 방문했는가?
                if (count == blank.size()) {
                    // 보드 출력 후 프로그램 종료
                    for (int j = 0; j < 9; j++) {
                        for (int k = 0; k < 9; k++) {
                            System.out.print(board[j][k] + " ");
                        }
                        System.out.println();
                    }
                    System.exit(0);
                }
                // 5. 간다
                dfs(blank.get(count)[0], blank.get(count)[1]);
            }
            // 6. 체크아웃
            visited[y][x] = false;
            count--;
            board[y][x] = 0;
        }
    }

    // 행 확인
    static boolean checkRow(int y) {
        hasNum = new boolean[10];
        for (int i = 0; i < 9; i++) {
            int num = board[y][i];
            if (num == 0) continue;
            if (!hasNum[num]) {
                hasNum[num] = true;
            } else {
                return false;
            }
        }
        return true;
    }

    // 열 확인
    static boolean checkCol(int x) {
        hasNum = new boolean[10];
        for (int i = 0; i < 9; i++) {
            int num = board[i][x];
            if (num == 0) continue;
            if (!hasNum[num]) {
                hasNum[num] = true;
            } else {
                return false;
            }
        }
        return true;
    }

    // 3 x 3 확인
    static boolean checkRect(int y, int x) {
        hasNum = new boolean[10];
        int yPos = y / 3 * 3;
        int xPos = x / 3 * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = board[yPos+i][xPos+j];
                if(num == 0) continue;
                if (!hasNum[num]) {
                    hasNum[num] = true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
