package Programmers.Kakao_Recruitment_2018;

public class SecretMap {

    public static void main(String[] args) {
        String[] result = solution(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28});
        for (String s : result) {
            System.out.println(s);
        }
    }

    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            int result = arr1[i] | arr2[i];
            String temp = Integer.toBinaryString(result);
            sb.append(" ".repeat(Math.max(0, n - temp.length())));
            for (int j = 0; j < temp.length(); j++) {
                if (temp.charAt(j) == '1') {
                    sb.append("#");
                } else {
                    sb.append(" ");
                }
            }
            answer[i] = sb.toString();
        }
        return answer;
    }
}
