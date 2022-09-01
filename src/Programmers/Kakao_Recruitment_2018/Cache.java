package Programmers.Kakao_Recruitment_2018;

import java.util.PriorityQueue;

public class Cache {

    public static void main(String[] args) {
        System.out.println(solution(0, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
    }

    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;
        int time = 0;
        PriorityQueue<City> cache = new PriorityQueue<>();
        for (String city : cities) {
            time++;
            city = city.toLowerCase();
            boolean flag = false;
            for (City curr:
                 cache) {
                if (curr.name.equals(city)) {
                    cache.remove(curr);
                    cache.add(new City(city, time));
                    flag = true;
                    answer += 1;
                    break;
                }
            }
            if (!flag) {
                cache.add(new City(city, time));
                if (cache.size() > cacheSize) {
                    cache.poll();
                }
                answer += 5;
            }
        }
        return answer;
    }

    static class City implements Comparable<City>{

        String name;
        int value;

        City(String name, int value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public int compareTo(City o) {
            return this.value - o.value;
        }
    }
}
