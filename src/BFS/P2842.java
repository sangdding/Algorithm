package BFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P2842 {

    static int N, K, bottom, up, sx, sy;
    static String[] map;
    static int[][] height;
    static int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static boolean[][] visited;
    static Queue<Node> queue;
    static List<Integer> heightList;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new String[N];
        height = new int[N][N];
        visited = new boolean[N][N];
        StringTokenizer st;
        K = 0;
        queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine();
            for (int j = 0; j < N; j++) {
                if (map[i].charAt(j) == 'P') {
                    queue.add(new Node(i, j));
                    visited[i][j] = true;
                } else if (map[i].charAt(j) == 'K') {
                    K++;
                }
            }
        }
        int max = -1, min = Integer.MAX_VALUE;
        heightList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                height[i][j] = Integer.parseInt(st.nextToken());
                heightList.add(height[i][j]);
                if (map[i].charAt(j) == 'P' || map[i].charAt(j) == 'K') {
                    max = Math.max(max, height[i][j]);
                    min = Math.min(min, height[i][j]);
                }
            }
        }

        Collections.sort(heightList);
        bottom = 0;
        up = heightList.indexOf(max);
        int minIdx = heightList.indexOf(min);
        int maxIdx = heightList.size();
        int res = Integer.MAX_VALUE;
        while (bottom <= minIdx && bottom <= up && up < maxIdx) {
            int l = heightList.get(bottom);
            int r = heightList.get(up);
            if (bfs() == 0) {
                res = Math.min(res, (r - l));
                bottom++;
            } else {
                up++;
            }
        }
        System.out.println(res);
    }

    static int bfs() {
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (K == 0) return K;
            for (int i = 0; i < 8; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[ny][nx] && height[ny][nx] >= bottom && height[ny][nx] <= up) {
                    visited[ny][nx] = true;
                    if (map[ny].charAt(nx) == 'K') {
                        K--;
                    }
                    queue.add(new Node(ny, nx));
                }
            }
        }
        return K;
    }

    static class Node {
        int x;
        int y;

        Node (int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
