package Programmers.Kakao_Recruitment_2019;

import java.util.Arrays;

public class FailRatio {

    public static void main(String[] args) {
        int[] result = solution(4, new int[]{4,4,4,4,4});
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        User[] users = new User[N + 1];
        // 1번 스테이지부터 계산한다.
        for (int i = 0; i <= N; i++) {
            users[i] = new User(i);
        }
        for (int i = 1; i <= N; i++) {
            for (int stage : stages) {
                if (stage > i) {
                    users[i].total++;
                } // 만약 i번째 스테이지보다 현재 스테이지가 크면 통과한 것
                else if (stage == i) {
                    users[i].failure++;
                    users[i].total++;
                } // 현재 스테이지와 i번째 스테이지가 같다면 해당 스테이지에 머물러있는 것
            }
            if (users[i].total == 0) continue;
            users[i].getFailure();
        }
        Arrays.sort(users);
        for (int i = 0, j = 0; i <= N; i++) {
            if (users[i].level == 0) continue;
            answer[j++] = users[i].level;
        }
        return answer;
    }

    static class User implements Comparable<User> {
        int level;
        float ratio;
        int total;
        int failure;

        User (int level) {
            this.level = level;
            this.total = 0;
            this.failure = 0;
            this.ratio = 0;
        }

        public void getFailure() {
            ratio = (float) failure / (float) total;
        }

        @Override
        public int compareTo(User o) {
            if (this.ratio > o.ratio) {
                return -1;
            } else if (this.ratio == o.ratio) {
                return this.level - o.level;
            } else {
                return 1;
            }
        }
    }
}
