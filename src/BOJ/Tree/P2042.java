package BOJ.Tree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2042 {

    static int N, M, K;
    static long[] input;
    static long[] tree;
    static int S;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 트리의 크기는 N보다 큰 가장 작은 2의 제곱수
        S = 1;
        while (S < N) {
            S *= 2;
        }
        tree = new long[2 * S];
        input = new long[S + 1];
        for (int i = 0; i < N; i++) {
            input[i] = Long.parseLong(br.readLine());
        }

        // tree의 node는 index 1부터 초기화한다.
        init(1, S, 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("1")) {
                int index = Integer.parseInt(st.nextToken());
                long diff = Long.parseLong(st.nextToken()) - tree[S + index - 1];
                update(1, S, 1, index, diff);
            } else {
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                System.out.println(query(1, S, 1, start, end));
            }
        }
    }

    /**
     * 트리 초기화하기
     * @param left : 구간의 시작
     * @param right : 구간의 끝
     * @param node : 업데이트 할 node index
     */
    static long init(int left, int right, int node) {
        // 만약 left == right이라면 leaf node이므로 원소의 값으로 초기화한다.
        if (left == right) {
            return tree[node] = input[node - S];
        }
        // 아니라면 왼쪽 자식 node와 오른쪽 자식 node를 초기화한 후 return 한다.
        else {
            int mid = (left + right) / 2;
            return tree[node] = init(left, mid, 2 * node) + init(mid + 1, right, 2 * node + 1);
        }
    }


    /**
     * 요소 업데이트하기
     * @param left 구간의 시작
     * @param right 구간의 끝
     * @param node 현재 위치한 node
     * @param index update할 index
     * @param diff update값과 현재 값의 차이
     */
    static void update(int left, int right, int node, int index, long diff) {
        // 만약 index를 포함하면 값 갱신하면서 왼쪽과 오른쪽 검사
        if (left <= index && right >= index) {
            int mid = (left + right) / 2;
            tree[node] += diff; // 값 갱신
            if (left == right) {
                return ;
            }
            update(left, mid, node * 2, index, diff);
            update(mid + 1, right, node * 2 + 1, index, diff);
        }
    }

    /**
     * 구간 합 구하기
     * @param left 구간 검사 시작 값
     * @param right 구간 검사 끝 값
     * @param node 현재 node의 index
     * @param start 구하려는 구간의 시작 idnex
     * @param end 구하려는 구간의 끝 index
     * @return 구간 합
     */
    static long query(int left, int right, int node, int start, int end) {
        // 만약 범위 밖이라면 무시
        if (left > end || right < start) {
            return 0;
        }
        // 만약 범위에 포함하면 구간 값 return
        else if (left >= start && right <= end) {
            return tree[node];
        }
        // 만약 범위에 걸치면 자식에게 위임
        else {
            int mid = (left + right) / 2;
            return query(left, mid, 2 * node, start, end) + query(mid + 1, right, 2 * node + 1, start, end);
        }
    }
}