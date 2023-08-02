package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 업무처리 {
    static class Node {
        Queue<Integer> list = new LinkedList<>();
    }

    static int size, leaf, answer = 0;
    static Node[] work;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 높이
        int h = parseInt(st.nextToken());
        // 대기하는 업무의 개수
        int k = parseInt(st.nextToken());
        // 업무가 진행되는 날짜
        int r = parseInt(st.nextToken());
        size = (int) Math.pow(2.0, h + 1) - 1;
        leaf = (int) Math.pow(2.0, h);
        work = new Node[size + 1];
        for (int i = 0; i <= size; i++) {
            work[i] = new Node();
        }
        // 말단 직원의 업무들
        for (int i = leaf; i <= size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < k; j++) {
                work[i].list.add(parseInt(st.nextToken()));
            }
        }
        for (int i = 2; i <= r; i++) {
            for (int j = 2; j <= size; j++) {
                Queue<Integer> queue = work[j].list;
                if (!queue.isEmpty() && j % 2 != i % 2) {
                    if (j > 3) {
                        work[j / 2].list.add(queue.poll());
                    } else {
                        answer += queue.poll();
                    }
                }
            }
        }
        System.out.println(answer);
    }

    static int parseInt(String input) {
        return Integer.parseInt(input);
    }
}
