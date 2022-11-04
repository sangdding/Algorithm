package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class P20006 {

    static int p, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ArrayList<Player> input = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            input.add(new Player(Integer.parseInt(st.nextToken()), st.nextToken()));
        }
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room(input.get(0).level - 10, input.get(0).level + 10, input.get(0)));
        for (int i = 1; i < input.size(); i++) {
            Player curr = input.get(i);
            boolean flag = false;
            for (Room room : rooms) {
                if (room.count == m) continue;
                if (curr.level > room.maxLv || curr.level < room.minLv) continue;
                room.enter(curr);
                flag = true;
                break;
            }
            if (!flag) {
                rooms.add(new Room(curr.level - 10, curr.level + 10, curr));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Room room :
                rooms) {
            room.players.sort(Comparator.comparing(o -> o.name));
            if (room.count == m) {
                sb.append("Started!\n");
            } else {
                sb.append("Waiting!\n");
            }
            for (Player player :
                    room.players) {
                sb.append(player.level + " " + player.name + "\n");
            }
        }
        System.out.println(sb);
    }

    public static class Player {
        int level;
        String name;
        boolean inRoom = false;

        public Player(int level, String name) {
            this.level = level;
            this.name = name;
        }
    }

    public static class Room {
        ArrayList<Player> players = new ArrayList<>();
        int minLv;
        int maxLv;
        int count;

        public Room(int minLv, int maxLv, Player player) {
            this.minLv = minLv;
            this.maxLv = maxLv;
            this.players.add(player);
            count = 1;
        }

        public void enter(Player player) {
            players.add(player);
            count++;
        }
    }

}