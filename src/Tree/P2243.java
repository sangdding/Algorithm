package Tree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2243 {

    static int n;
    static int[] box;
    static int S;
    static int candy;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int A, B, C;

        S = 1;
        while (S < 1000000) {
            S *= 2;
        }

        box = new int[2 * S];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            // 사탕 꺼내기
            if (A == 1) {
                int index = query(1, S, 1, B);
                update(1, S, 1, index, -1);
                System.out.println(index);
            }
            // 사탕 넣기
            else if (A == 2) {
                C = Integer.parseInt(st.nextToken());
                update(1, S, 1, B, C);
            }
        }
    }

    /**
     * 사탕 상자의 개수가 변할 때 호출한다.
     * @param left 구간의 왼쪽 끝
     * @param right 구간의 오른쪽 끝
     * @param node 현재 위치한 노드
     * @param index 바꿀 사탕의 번호
     * @param diff 얼마나 개수가 변하는지
     */
    static void update(int left, int right, int node, int index, int diff) {
        if (left <= index && index <= right) {
            box[node] += diff;
            if (left != right) {
                int mid = (left + right) / 2;
                update(left, mid, node * 2, index, diff);
                update(mid + 1, right, node * 2 + 1, index, diff);
            }
        }
    }

    /**
     * 사탕 상자에서 사탕을 꺼낼 때 호출한다.
     * @param left 구간의 왼쪽 끝
     * @param right 구간의 오른쪽 끝
     * @param node 현재 위치한 노드
     * @param index 꺼낼 사탕의 순위
     */
    static int query(int left, int right, int node, int index) {
        // left == right가 동일하다면 left(순번)를 return한다.
        if (left == right) {
            return left;
        } else {
            int mid = (left + right) / 2;
            if (index <= box[node * 2]) {
                return query(left, mid, 2 * node, index);
            }
            // 만약 index가 왼쪽 자식의 값 보다 크면 index에서 왼쪽 자식의 값을 빼고 오른쪽 자식을 호출한다.
            else {
                index -= box[node * 2];
                return query(mid + 1, right, 2 * node + 1, index);
            }
        }
    }
}