package BOJ.Tree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P2517 {

    static int N;
    static int[] tree;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            players.add(new Player(i, Integer.parseInt(br.readLine())));
        }

        players.sort(Comparator.comparingInt(a -> a.speed));
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            player.speed = i + 1;
        }
        players.sort(Comparator.comparingInt(a -> a.id));

        StringBuilder sb = new StringBuilder();
        tree = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int speed = players.get(i - 1).speed;
            sb.append(i - sum(speed - 1)).append("\n");
            update(speed);
        }
        System.out.print(sb);
    }

    static int sum(int idx) {
        int result = 0;
        while (idx > 0) {
            result += tree[idx];
            idx -= (idx & -idx);
        }
        return result;
    }

    static void update(int idx) {
        while (idx <= N) {
            tree[idx] += 1;
            idx += (idx & -idx);
        }
    }

    static class Player {

        int id;
        int speed;

        Player(int id, int speed) {
            this.id = id;
            this.speed = speed;
        }
    }
}