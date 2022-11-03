package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P2493 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] input = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        // 언제 수신하는가?
        // 자신보다 높은 탑들 중 왼쪽에서 가장 가까운 탑
        // 뒤의 탑이 자신보다 높으면 그 뒤의 탑은 무조건 자신에게 올 수 없다.
        // 큐에 탑의 높이 삽입
        // 만약 다음 탑의 높이가 자신보다 크다면
        // 큐에서 제거
        // 아니라면 큐에 다음 탑의 높이 삽입
        ArrayList<Integer> queue = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(0).append(" ");
        for (int i = 0; i < n - 1; i++) {
            queue.add(i);
            for (int j = 0; j < queue.size(); j++) {
                int index = queue.get(j);
                if (index < i && input[index] >= input[i]) {
                    sb.append(index).append(" ");
                } else if (index > i && input[index] <= input[i]){
                    sb.append(0).append(" ");
                }
            }
            if (input[i] < input[i + 1]) {
                queue.remove(queue.size()-1);
            }
        }
        System.out.println(sb);
    }

}
