package DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P12015 {

    static int N;
    static int[] A;
    static List<Integer> temp;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        temp = new ArrayList<>();
        temp.add(A[0]);

        for (int i = 1; i < N; i++) {
            if (A[i] > temp.get(temp.size() - 1)) {
                temp.add(A[i]);
            } else {
                findLow(A[i]);
            }
        }

        System.out.println(temp.size());
    }

    static void findLow(int key) {
        int left = 0;
        int right = temp.size() - 1;
        int mid = (left + right) / 2;
        if (left == right) {
            temp.set(mid, key);
        }
        while (left != right) {
            if (temp.get(mid) == key) break;
            if (temp.get(mid) < key && temp.get(mid + 1) >= key) {
                temp.set(mid + 1, key);
                break;
            }
            if (temp.get(mid + 1) <= key) {
                left = mid;
            } else if (temp.get(mid) > key){
                right = mid;
            }
            mid = (left + right) / 2;
            if (left == right) {
                temp.set(mid, key);
            }
        }
    }
}