package Programmers.Kakao_Recruitment_2018;

import java.util.ArrayList;
import java.util.List;

public class ThanksgivingTraffic {

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"}));
    }

    static int solution(String[] lines) {
        int answer = 0;
        List<Log> log = new ArrayList<>();
        for (String s : lines) {
            double[] line = stringToTime(s);
            log.add(new Log(line[0], line[1]));
        }
        for (int i = 0; i < log.size(); i++) {
            int count = 1;
            for (int j = i + 1; j < log.size(); j++) {
                double start = log.get(j).end - log.get(j).time + 0.001;
                if (log.get(i).end + 1 > start) {
                    count++;
                }
            }
            answer = Math.max(count, answer);
        }
        return answer;
    }

    static double[] stringToTime(String s) {
        double[] result = new double[2];
        String[] split = s.split(" ");
        String[] date = split[1].split(":");
        result[0] = Double.parseDouble(date[0]) * 3600 + Double.parseDouble(date[1]) * 60 + Double.parseDouble(date[2]);
        split[2] = split[2].split("s")[0];
        result[1] = Double.parseDouble(split[2]);
        return result;
    }

    static class Log {
        double end;
        double time;

        Log(double end, double time) {
            this.end = end;
            this.time = time;
        }
    }

}
