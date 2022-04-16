package DP;

/*
문제 번호 : 1463
문제
정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
X가 3으로 나누어 떨어지면, 3으로 나눈다.
X가 2로 나누어 떨어지면, 2로 나눈다.
1을 뺀다.
정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.
입력
첫째 줄에 1보다 크거나 같고, 10^6보다 작거나 같은 정수 N이 주어진다.
출력
첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class makeOne {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] memory = new int[1000001];
        int n = parseInt(br.readLine());
        for (int i = 2; i <= n; i++) {
            // 현재의 수에서 1을 빼는 경우
            memory[i] = memory[i - 1] + 1;
            // 현재의 수가 2로 나누어 떨어지는 경우
            if(i % 2 == 0) {
                memory[i] = Math.min(memory[i / 2] + 1, memory[i]);
            }
            // 현재의 수가 3으로 나누어 떨어지는 경우
            if(i % 3 == 0) {
                memory[i] = Math.min(memory[i / 3] + 1, memory[i]);
            }
        }
        System.out.print(memory[n]);
    }
}
