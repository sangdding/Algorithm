package Programmers.Kakao_Recruitment_2018;

import java.util.ArrayList;

public class DartGame {

    public static void main(String[] args) {
        String dartResult = "1D2S#10S";
        System.out.println(solution(dartResult));
    }

    public static int solution(String dartResult) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        ArrayList<Dart> input = new ArrayList<>();
        for (int i = 0; i < dartResult.length(); i++) {
            char item = dartResult.charAt(i);
            if (item >= '0' && item <= '9') {
                sb.append(item);
            } else if (item == 'S' || item == 'D' || item == 'T') {
                char area = item;
                char option = ' ';
                if (i != dartResult.length() - 1 && (dartResult.charAt(i + 1) == '#' || dartResult.charAt(i + 1) == '*')) {
                    option = dartResult.charAt(i + 1);
                    i++;
                }
                input.add(new Dart(area, option, Integer.parseInt(sb.toString())));
                sb = new StringBuilder();
            }
        }
        for (int i = 0; i < input.size(); i++) {
            Dart curr = input.get(i);
            if (curr.area == 'D') {
                curr.score = (int) Math.pow(curr.score, 2);
            } else if (curr.area == 'T') {
                curr.score = (int) Math.pow(curr.score, 3);
            }

            if (curr.option == '*') {
                curr.score *= 2;
                if (i != 0) {
                    input.get(i - 1).score *= 2;
                }
            } else if (curr.option == '#'){
                curr.score *= -1;
            }
        }
        for (int i = 0; i < input.size(); i++) {
            answer += input.get(i).score;
        }
        return answer;
    }

    static class Dart {

        char area;
        char option;
        int score;

        Dart(char area, char option, int score) {
            this.area = area;
            this.option = option;
            this.score = score;
        }
    }
}
