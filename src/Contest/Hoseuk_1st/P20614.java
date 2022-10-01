package Contest.Hoseuk_1st;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class P20614 {

    static int max, min;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        getOdd(br.readLine(), 0);
        System.out.println(min + " " + max);
    }

    public static void getOdd(String num, int cnt) {
        for (int i = 0; i < num.length(); i++) {
            int number = num.charAt(i) - 48;
            if (number % 2 == 1) {
                cnt++;
            }
        }
        if (num.length() == 1) {
            max = Math.max(max, cnt);
            min = Math.min(min, cnt);
            return ;
        } else if (num.length() == 2) {
            int number = Integer.parseInt(num.substring(0, 1)) + Integer.parseInt(num.substring(1, 2));
            getOdd(Integer.toString(number), cnt);
        } else {
            for (int i = 1; i < num.length() - 1; i++) {
                for (int j = i + 1; j < num.length();  j++) {
                    int number = Integer.parseInt(num.substring(0, i)) + Integer.parseInt(num.substring(i, j)) + Integer.parseInt(num.substring(j));
                    getOdd(Integer.toString(number), cnt);
                }
            }
        }
    }

}
