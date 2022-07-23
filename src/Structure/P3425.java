package Structure;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P3425 {


    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/Structure/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        while (true) {

            String input = br.readLine();

            // 종료 조건 : 입력값이 QUIT이면 입력이 끝난다.
            if (input.equals("QUIT")) {
                break;
            }

            // 프로그램 영역
            // 새로운 프로그램일 때 GoStack 생성
            GoStack machine = new GoStack();
            // 처음 입력으로 들어온 order machine에 저장
            machine.addOrder(input);
            while (!input.equals("END")) {
                String order = br.readLine();
                // 종료 조건 : 입력값이 END이면 입력이 끝난다.
                if (order.equals("END")) {
                    break;
                }
                // 입력받은 order를 machine에 저장
                machine.addOrder(order);
            }

            // 입력 영역 : 프로그램 영역 입력 끝난 후 입력받은 정수 만큼 입력받기
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                // 입력받은 값으로 machine 동작
                machine.operateMachine(Long.parseLong(br.readLine()));
            }

            // 빈 줄 처리
            System.out.println();
            br.readLine();
        }
    }
}

class GoStack {

    private Stack<Long> stack;
    private List<String> orders;
    boolean errorFlag;

    // 동작 명세
    // X를 스택의 가장 위에 저장한다.
    private void numX(long X) {
        stack.push(X);
    }
    // 스택 가장 위의 숫자를 제거한다.
    private void pop() {
        if(!stack.isEmpty()) stack.pop();
        else error();
    }
    // 첫 번째 수의 부호를 바꾼다.
    private void inv() {
        if (!stack.isEmpty()) {
            stack.push(-stack.pop());
        } else {
            error();
        }
    }
    // 첫 번째 숫자를 하나 더 스택의 가장 위에 저장한다.
    private void dup() {
        if (!stack.isEmpty()) {
            stack.push(stack.peek());
        } else {
            error();
        }
    }
    // 첫 번째 숫자와 두 번째 숫자의 위치를 서로 바꾼다.
    private void swp() {
        if (stack.size() < 2) {
            error();
        } else {
            long num1 = stack.pop();
            long num2 = stack.pop();
            stack.push(num1);
            stack.push(num2);
        }
    }
    // 첫 번째 숫자와 두 번째 숫자를 더한다.
    private void add() {
        if (stack.size() < 2) {
            error();
        } else {
            long num1 = stack.pop();
            long num2 = stack.pop();
            long result = num1 + num2;
            if (Math.abs(result) > 1e9) {
                error();
            } else {
                stack.push(num1 + num2);
            }
        }
    }
    // 첫 번째 숫자와 두 번째 숫자를 뺀다. (두 번째 - 첫 번째)
    private void sub() {
        if (stack.size() < 2) {
            error();
        } else {
            long num1 = stack.pop();
            long num2 = stack.pop();
            long result = num2 - num1;
            if (Math.abs(result) > 1e9) {
                error();
            } else {
                stack.push(num2 - num1);
            }
        }
    }
    // 첫 번째 숫자와 두 번째 숫자를 곱한다.
    private void mul() {
        if (stack.size() < 2) {
            error();
        } else {
            long num1 = stack.pop();
            long num2 = stack.pop();
            long result = num1 * num2;
            if (Math.abs(result) > 1e9) {
                error();
            } else {
                stack.push(num1 * num2);
            }
        }
    }
    // 첫 번째 숫자로 두 번째 숫자를 나눈 몫을 저장한다. 두 번째 숫자가 피제수, 첫 번째 숫자가 제수이다.
    private void div() {
        if (stack.size() < 2) {
            error();
        } else {
            long num1 = stack.pop();
            long num2 = stack.pop();
            // 0으로 나누면 에러
            if (num1 == 0) {
                error();
            } else {
                long result = Math.abs(num2) / Math.abs(num1);
                if ((num1 < 0 && num2 < 0) || (num1 > 0 && num2 > 0)) {
                    stack.push(result);
                } else {
                    stack.push(-result);
                }
            }
        }
    }
    // 첫 번째 숫자로 두 번재 숫자를 나눈 나머지를 저장한다. 두 번째 숫자가 피제수, 첫 번째 숫자가 제수이다.
    private void mod() {
        if (stack.size() < 2) {
            error();
        } else {
            long num1 = stack.pop();
            long num2 = stack.pop();
            // 0으로 나누면 에러
            if (num1 == 0) {
                error();
            } else {
                long result = Math.abs(num2) % Math.abs(num1);
                if (num2 > 0) {
                    stack.push(result);
                } else {
                    stack.push(-result);
                }
            }
        }
    }
    // 에러 처리
    private void error() {
        System.out.println("ERROR");
        this.stack.clear();
        this.errorFlag = true;
    }


    // 고스택 생성자
    public GoStack() {
        this.stack = new Stack<>();
        this.orders = new ArrayList<>();
        this.errorFlag = false;
    }

    public void addOrder(String order) {
        this.orders.add(order);
    }

    public void operateMachine(long num) {

        this.stack.push(num);
        // 에러 초기화
        this.errorFlag = false;
        // order가 빌 때 까지 실행, 만약 에러가 나면 실행 중지
        for (String order : orders) {
            if (errorFlag) break;
            switch (order.substring(0, 3)) {
                case "NUM":
                    this.numX(Long.parseLong(order.substring(4)));
                    break;
                case "POP":
                    this.pop();
                    break;
                case "INV":
                    this.inv();
                    break;
                case "DUP":
                    this.dup();
                    break;
                case "SWP":
                    this.swp();
                    break;
                case "ADD":
                    this.add();
                    break;
                case "SUB":
                    this.sub();
                    break;
                case "MUL":
                    this.mul();
                    break;
                case "DIV":
                    this.div();
                    break;
                case "MOD":
                    this.mod();
                    break;
            }
        }

        // 만약에 stack에 저장되어 있는 값이 1개라면 값을 출력, 아니라면 스택을 비우고 에러를 출력
        if (!errorFlag) {
            if (stack.size() == 1) {
                System.out.println(stack.pop());
            } else {
                this.stack.clear();
                System.out.println("ERROR");
            }
        }
    }
}