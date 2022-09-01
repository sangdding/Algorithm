package Programmers.Kakao_Recruitment_2018;

public class SecretMap {

    public static void main(String[] args) {
        String[] result = solution(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28});
        for (String s : result) {
            System.out.println(s);
        }
    }

    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = {};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int result = arr1[i] | arr2[i];
            for (int j = 0; j < n; j++) {
                int flag = (int) Math.pow(2, j) & result;
            }
        }
        return answer;
    }
}
