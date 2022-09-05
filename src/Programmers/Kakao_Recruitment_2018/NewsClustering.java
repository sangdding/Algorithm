package Programmers.Kakao_Recruitment_2018;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class NewsClustering {

    public static void main(String[] args) {
        System.out.println(solution("handshake", "shake hands"));
    }

    public static int solution(String str1, String str2) {
        int answer = 0;
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        int interSection = 0;
        int union = 0;
        Queue<String> newStr1 = new LinkedList<>();
        Queue<String> newStr2 = new LinkedList<>();
        makeStr(str1, newStr1);
        makeStr(str2, newStr2);
        int newStr1Length = newStr1.size();
        int newStr2Length = newStr2.size();
        if (newStr1.size() > newStr2.size()) {
            interSection = findInterSection(newStr2, newStr1);
        } else if (newStr1.size() == 0 && newStr2.size() == 0) {
            return 1;
        } else {
            interSection = findInterSection(newStr1, newStr2);
        }
        union = newStr1Length + newStr2Length - interSection;
        answer = (int)((float) interSection / (float) union * 65536);
        return answer;
    }

    private static int findInterSection(Queue<String> str1, Queue<String> str2) {
        int interSection = 0;
        for (String string1 : str1) {
            for (String string2 : str2) {
                if (string1.equals(string2)) {
                    interSection++;
                    str2.remove(string2);
                    break;
                }
            }
        }
        return interSection;
    }

    private static void makeStr(String str, Queue<String> newStr) {
        for (int i = 0; i < str.length() - 1; i++) {
            StringBuilder sb = new StringBuilder();
            if (str.charAt(i) < 'a' || str.charAt(i) > 'z' || str.charAt(i + 1) < 'a' || str.charAt(i + 1) > 'z') {
                continue;
            }
            newStr.add(sb.append(str.charAt(i)).append(str.charAt(i + 1)).toString());
        }
    }
}
