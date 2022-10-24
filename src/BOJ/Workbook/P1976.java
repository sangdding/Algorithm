package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P1976 {

    /*
    도시의 개수 : n <= 200
    여행 계획에 속한 도시의 수 : m <= 1000
    i번째 줄의 j번째 수 : i번과 j번 도시의 연결 정보 (1: 연결, 0: 비연결)
    양방향 연결

    문제에서 구하고자 하는 것
    여행 계획에 속한 도시를 순서대로 방문할 수 있는가?
    중복방문가능

    알고리즘
    a 도시에서 b 도시로 가야 한다면, a에서 b로 갈 수 있는 방법을 찾아야 한다.
    각 도시마다 다른 도시에 대한 연결 정보를 가지고 있으면 된다.
    목적지로 가면서 방문했던 도시는 방문 가능하다고 할 수 있다.
     */

    static int n, m;
    static int[][] map;
    static int[] plan;
    static boolean[] visited;
    static List<Integer> route; // 방문했던 도시를 담을 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[n][n];
        plan = new int[m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (i == j) map[i][j] = 1;
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            // 방문을 했다면 탐색 할 필요가 없다.
            if (visited[i]) continue;
            route = new ArrayList<>();
            dfs(i);
            // 방문해서 얻은 경로상의 도시들은 다 서로 연결되어 있다고 할 수 있다.
            if (route.size() < 2) {
                // 경로가 자신 하나라면 map을 갱신할 필요가 없다.
                continue;
            }
            // 연결 정보 갱신
            for (int j = 0; j < route.size(); j++) {
                for (int k = 1; k < route.size(); k++) {
                    map[route.get(j) - 1][route.get(k) - 1] = 1;
                    map[route.get(k) - 1][route.get(j) - 1] = 1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (i == m - 1) {
                System.out.println("YES");
                break;
            }
            if (map[plan[i] - 1][plan[i + 1] - 1] == 0) {
                System.out.println("NO");
                break;
            }
        }
    }

    public static void dfs(int city) {
        // 도시에 방문 했으므로 방문 체크
        visited[city] = true;
        // 경로에 추가
        route.add(city);
        // 도시와 연결된 도시들 탐색
        for (int i = 1; i <= n; i++) {
            // 방문 했던 도시라면 탐색할 필요 없다.
            if (visited[i]) continue;
            // 방문하지 않았고 도시와 연결되어 있다면 탐색한다.
            if (!visited[i] && map[city - 1][i - 1] == 1) {
                dfs(i);
            }
        }
    }
}
