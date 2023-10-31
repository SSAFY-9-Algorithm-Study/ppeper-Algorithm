package week30;

import java.util.Comparator;
import java.util.PriorityQueue;

class 야근_지수 {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int work : works) {
            pq.add(work);
        }
        while (0 < n && !pq.isEmpty()) {
            int workingRemain = pq.poll() - 1;
            if (0 < workingRemain) pq.offer(workingRemain);
            n--;
        }
        while (!pq.isEmpty()) {
            int value = pq.poll();
            answer += (long) value * value;
        }
        return answer;
    }
}
