package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class P20055 {

    static int n, k, on, off, cnt;
    static int[] belt;
    static List<Integer> robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        belt = new int[2 * n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < 2 * n + 1; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }
        robot = new LinkedList<>();
        on = 1;
        off = n;
        cnt = 0;
        System.out.println(operateBelt());
    }

    // 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
    static void rotate() {
        if (on == 1) {
            on = 2 * n;
            off--;
        } else if (off == 1) {
            on--;
            off = 2 * n;
        } else {
            on--;
            off--;
        }
    }

    // 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다.
    // 만약 이동할 수 없다면 가만히 있는다.
    static void move() {
        if (robot.size() > 0 && robot.get(0) == off) {
            robot.remove(0);
        }
        for (int i = 0; i < robot.size(); i++) {
            int currPos = robot.get(i);
            int next = currPos == 2 * n ? 1 : currPos + 1;
            // 바로 앞 칸에 로봇이 존재하지 않으면 이동한다.
            if (i == 0) {
                if (belt[next] > 0) {
                    robot.set(i, next);
                    belt[next]--;
                    check(next);
                }
            } else {
                if (belt[next] > 0 && (robot.get(i - 1) != next)) {
                    robot.set(i, next);
                    belt[next]--;
                    check(next);
                }
            }
        }
        // 맨 앞의 로봇이 내릴 수 있다면 내린다.
        if (robot.size() > 0 && robot.get(0) == off) {
            robot.remove(0);
        }
    }

    // 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
    static void on() {
        if (belt[on] > 0) {
            robot.add(on);
            belt[on]--;
            check(on);
        }
    }

    // 4. 내구도가 0인 칸의 개수가 k개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
    static int operateBelt() {
        int res = 0;
        while (cnt < k) {
            rotate();
            move();
            on();
            res++;
        }
        return res;
    }

    static void check(int index) {
        if (belt[index] == 0) {
            cnt++;
        }
    }
}