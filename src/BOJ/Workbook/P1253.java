package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1253 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            int s = 0;
            int e = arr.length - 1;
            int sum = 0;
            while (s < e) {
                sum = arr[s] + arr[e];
                if (sum == arr[i]) {
                    if (i == s) {
                        s++;
                    } else if (i == e) {
                        e--;
                    } else {
                        res++;
                        break;
                    }
                }
                if (sum > arr[i]) e--;
                else if (sum < arr[i]) s++;
            }
        }
        System.out.println(res);
    }
}
