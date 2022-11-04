package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class P2164 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<Integer> queue = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toCollection(LinkedList::new));
        for (int i = 0; i < n - 1; i++) {
            queue.poll();
            queue.add(queue.poll());
        }
        System.out.println(queue.poll());
    }

}
