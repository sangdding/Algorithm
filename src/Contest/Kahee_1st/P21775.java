package Contest.Kahee_1st;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P21775 {

    static int n, t;
    static int[] users;
    static Queue<Card> cardList;
    static Card[] userCard;
    static List<List<Integer>> userSpace;
    static List<Integer> commonSpace;


    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        users = new int[t + 1];
        userCard = new Card[n + 1];
        userSpace = new ArrayList<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= t; i++) {
            users[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= n; i++) {
            userSpace.add(new ArrayList<>());
        }

        cardList = new LinkedList<>();
        for (int i = 0; i < t; i++) {
            String[] parse = br.readLine().split(" ");
            if (parse.length == 2) {
                cardList.add(new Card(Integer.parseInt(parse[0]), parse[1]));
            } else {
                cardList.add(new Card(Integer.parseInt(parse[0]), parse[1], Integer.parseInt(parse[2])));
            }
        }

        int cnt = 0;
        commonSpace = new ArrayList<>();
        for (int i = 1; i <= t; i++) {
            int user = users[i];
            if (userCard[user] == null) {
                Card curr = cardList.poll();
                System.out.println(curr.id);
                if (curr.op.equals("next")) {
                    continue;
                }
                if (curr.op.equals("acquire")) {
                    if (!commonSpace.contains(curr.target)) {
                        userSpace.get(user).add(curr.target);
                        commonSpace.add(curr.target);
                    } else {
                        userCard[user] = curr;
                    }
                } else {
                    int indexUser = userSpace.get(user).indexOf(curr.target);
                    int indexCommon = commonSpace.indexOf(curr.target);
                    userSpace.get(user).remove(indexUser);
                    commonSpace.remove(indexCommon);
                }
            } else {
                Card curr = userCard[user];
                userCard[user] = null;
                System.out.println(curr.id);
                if (curr.op.equals("next")) {
                    continue;
                }
                if (curr.op.equals("acquire")) {
                    if (!commonSpace.contains(curr.target)) {
                        userSpace.get(user).add(curr.target);
                        commonSpace.add(curr.target);
                    } else {
                        userCard[user] = curr;
                    }
                } else {
                    int indexUser = userSpace.get(user).indexOf(curr.target);
                    int indexCommon = commonSpace.indexOf(curr.target);
                    userSpace.get(user).remove(indexUser);
                    commonSpace.remove(indexCommon);
                }
            }
        }
    }

    public static class Card {
        int id;
        String op;
        int target;

        Card(int id, String op) {
            this.id = id;
            this.op = op;
        }

        Card(int id, String op, int target) {
            this.id = id;
            this.op = op;
            this.target = target;
        }
    }

}
