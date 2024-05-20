package week55;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 차트 {

    static int N, answer;
    static int[] percent, pick;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());
        percent = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        pick = new int[N];
        visited = new boolean[N];
        perm(0);
        System.out.println(answer);
    }

    private static void perm(int r) {
        if (r == N) {
            checkChart();
        }
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            pick[r] = percent[i];
            perm(r + 1);
            visited[i] = false;
        }
    }

    private static void checkChart() {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += pick[i];
            int total = sum;
            for (int j = 0; j < i; j++) {
                total -= pick[j];
                if (total < 50) break;
                if (total == 50) count++;
            }
        }
        answer = Math.max(answer, count);
    }

    private static int parseInt(String input) {
        return Integer.parseInt(input);
    }
}
