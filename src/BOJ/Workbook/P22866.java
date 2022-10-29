package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class P22866 {

    static int n;
    static int[] building;
    static int[][] res;
    static boolean[] visited;

    static Stack<Integer> possible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        building = new int[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            building[i] = Integer.parseInt(st.nextToken());
        }
        res = new int[2][n];
        int lastMax = n - 1;
        for (int i = 0; i < lastMax; i++) {
            possible = new Stack<>();
            int count = 0;
            if (!visited[i]) {
                // 방문하지 않았다면 그 전 빌딩이 무조건 가장 가까우면서 번호가 작은 빌딩이다.
                int max = i; // 현재 빌딩의 높이
                for (int j = i; j < n; j++) {
                    if (building[j] > building[max]) {
                        possible.push(j);
                        visited[j] = true;
                        max = j;
                    }
                }
                lastMax=max;
                res[0][i] += possible.size();
                for (int j = 0; j < possible.size(); j++) {
                    System.out.print(possible.get(j) + " ");
                }
                System.out.println();
                for (int j = 0; j < possible.size(); j++) {
                    int index = possible.pop();
                    res[0][index] += j;
                }
            }
        }
        for (int i = n-1; i > lastMax; i--) {
            possible = new Stack<>();
            int count = 0;
            if (!visited[i]) {
                // 방문하지 않았다면 그 전 빌딩이 무조건 가장 가까우면서 번호가 작은 빌딩이다.
                int max = i; // 현재 빌딩의 높이
                for (int j = i; j >= lastMax; j--) {
                    if (building[j] > building[max]) {
                        possible.push(j);
                        visited[j] = true;
                        max = j;
                    }
                }
                res[0][i] += possible.size();
                for (int j = 0; j < possible.size(); j++) {
                    int index = possible.pop();
                    res[0][index] += j;
                }
            }
        }
        System.out.println();

    }

}
