package Programmers.Kakao_Recruitment_2018;

import java.util.PriorityQueue;

public class ShuttleBus {

    public static void main(String[] args) {
        System.out.println(solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"}));
    }

    public static String solution(int n, int t, int m, String[] timetable) {

        int answer = 0;
        int arriveTime = 9 * 60;

        int[] shuttle = new int[n];

        PriorityQueue<Integer> crews = new PriorityQueue<>();
        for (String time : timetable) {
            stringToTime(time);
            crews.offer(stringToTime(time));
        }

        for (int i = 0; i < n; i++) {
            while (!crews.isEmpty()) {
                int crew = crews.poll();
                if (crew <= arriveTime && shuttle[i] < m) {
                    shuttle[i]++;
                    answer = crew - 1;
                } else {
                    crews.offer(crew);
                    break;
                }
            }
            arriveTime += t;
        }

        if (shuttle[n - 1] < m) {
            answer = arriveTime - t;
        }

        return timeToString(answer);
    }

    static int stringToTime(String s) {
        String[] split = s.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);
        return hour * 60 + minute;
    }

    static String timeToString(int val) {
        String hStr = String.format("%02d", val / 60);
        String mStr = String.format("%02d", val % 60);
        return hStr +":"+mStr;
    }
}
