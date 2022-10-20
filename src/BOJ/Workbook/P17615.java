package BOJ.Workbook;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P17615 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String colors = br.readLine();
        int result = N;
        int rCnt = 0;
        int bCnt = 0;

        for(int i=0; i<N; i++) {
            if(colors.charAt(i)=='R')
                rCnt ++;
            else
                bCnt ++;
        }

        int cnt = 0;
        // 4가지 경우
        // 1. R만 움직여서 RRRBBB(R을 다 왼쪽으로)
        for(int i=0; i<N; i++) {
            if(colors.charAt(i)=='R')
                cnt++;
            else
                break;
        }
        result = Math.min(result, rCnt-cnt);

        // 2. R만 움직여서 BBBRRR
        cnt = 0;
        for(int i=N-1; i>=0; i--) {
            if(colors.charAt(i)=='R')
                cnt++;
            else
                break;
        }

        result = Math.min(result, rCnt-cnt);

        // 3. B만 움직여서 BBBRRR
        cnt = 0;
        for(int i=0; i<N; i++) {
            if(colors.charAt(i)=='B')
                cnt++;
            else
                break;
        }
        result = Math.min(result, bCnt-cnt);

        // 4. B만 움직여서RRRBBB
        cnt = 0;
        for(int i=N-1; i>=0; i--) {
            if(colors.charAt(i)=='B')
                cnt++;
            else
                break;
        }

        result = Math.min(result, bCnt-cnt);

        System.out.println(result);

    }

}