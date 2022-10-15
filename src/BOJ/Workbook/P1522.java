package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1522 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int aLen = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'a') {
                aLen++;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < input.length(); i++) {
            int bCnt = 0;
            for (int j = 0; j < aLen; j++) {
                if (input.charAt((i + j) % input.length()) == 'b') {
                    bCnt++;
                }
            }
            min = Math.min(min, bCnt);
        }
        System.out.println(min);
    }
}
