package Samsung;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class P3190 {

    static int N, K, L, ans;
    static int[][] map;
    static int[][] dir;
    static int[][] DIRECTION = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static Snake snake = new Snake();

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        dir = new int[N + 1][N + 1];
        map[1][1] = 2;
        dir[1][1] = 1;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1; // 사과 위치 지도에 반영
        }
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            String C = st.nextToken();
            if (ans == 0) {
                move(X, C);
            }
        }
        if (ans == 0) {
            move(10000, "End");
        }
        System.out.println(ans);
    }

    static void move(int time, String direction) {
        int[] nextHead = new int[2];
        time -= snake.time;
        for (int i = 1; i <= time; i++) {
            nextHead[0] = snake.head[0] + DIRECTION[snake.direction][0];
            nextHead[1] = snake.head[1] + DIRECTION[snake.direction][1];
            snake.time++;
            // 뱀이 벽이나 자기 자신에 부딪힐 경우
            if (!check(nextHead[0], nextHead[1]) || map[nextHead[0]][nextHead[1]] == 2) {
                ans = snake.time;
                break;
            }
            // 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
            snake.head = nextHead.clone();
            // 만약 사과가 없다면 꼬리는 한 칸 따라온다. (꼬리의 방향은 어떻게 결정할 것인가?)
            if (map[snake.head[0]][snake.head[1]] != 1) {
                map[snake.tail[0]][snake.tail[1]] = 0;
                int dx = DIRECTION[dir[snake.tail[0]][snake.tail[1]]][1];
                int dy = DIRECTION[dir[snake.tail[0]][snake.tail[1]]][0];
                snake.tail[0] += dy;
                snake.tail[1] += dx;
            }
            map[snake.head[0]][snake.head[1]] = 2;
            dir[snake.head[0]][snake.head[1]] = snake.direction;
        }
        // 방향에 따라 머리의 방향을 수정한다.
        if (direction.equals("D")) {
            if (snake.direction == 3) {
                snake.direction = 0;
            } else {
                snake.direction++;
            }
        } else if (direction.equals("L")){
            if (snake.direction == 0) {
                snake.direction = 3;
            } else {
                snake.direction--;
            }
        }
        dir[snake.head[0]][snake.head[1]] = snake.direction;
    }

    static boolean check(int y, int x) {
        return y > 0 && y <= N && x > 0 && x <= N;
    }

    static class Snake {

        int[] head;
        int[] tail;
        int direction;
        int time;

        Snake() {
            this.head = new int[]{1, 1};
            this.tail = new int[]{1, 1};
            this.direction = 1;
            this.time = 0;
        }
    }
}
