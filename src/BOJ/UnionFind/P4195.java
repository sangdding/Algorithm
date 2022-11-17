package BOJ.UnionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class P4195 {

    /*
    친구 관계가 생길 때 마다, 해당 친구 집합의 크기를 출력한다.
    친구의 이름은 문자열로 제공하기 때문에, HashMap 자료구조를 사용해 구별한다.
     */

    static HashMap<String, Integer> friends;
    static int[] parent;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int f = Integer.parseInt(br.readLine());
            friends = new HashMap<>(); // 친구 관계 저장
            parent = new int[f * 2];
            count = new int[f * 2];
            for (int j = 0; j < f * 2; j++) {
                parent[j] = j;
                count[j] = 1;
            }
            for (int j = 0; j < f; j++) {
                String[] names = br.readLine().split(" ");
                saveFriend(names);
                if (!isSameParent(names[0], names[1])) sb.append(union(names[0], names[1]) + "\n"); // 부모가 다를 경우 집합 합침
                else sb.append(count[find(friends.get(names[0]))] + "\n");
            }
        }
        System.out.print(sb);
    }

    public static void saveFriend(String[] names) {
        for (String name :
                names) {
            if (!friends.containsKey(name)) {
                friends.put(name, friends.size());
            }
        }
    }

    public static boolean isSameParent(String n1, String n2) {
        int index1 = friends.get(n1);
        int index2 = friends.get(n2);
        return find(index1) == find(index2);
    }

    public static int find(int index) {
        if (parent[index] == index) {
            return index;
        }
        return parent[index] = find(parent[index]);
    }

    public static int union(String n1, String n2) {
        int root1 = find(friends.get(n1));
        int root2 = find(friends.get(n2));
        parent[root2] = root1;
        count[root1] += count[root2];
        count[root2] = 1;
        return count[root1];
    }
}
