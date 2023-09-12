package week24;

import java.util.PriorityQueue;

class 신입사원_교육 {
    public int solution(int[] ability, int number) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int ab: ability) {
            pq.add(ab);
        }
        for (int i = 0; i < number; i++) {
            int sum = pq.poll() + pq.poll();
            pq.add(sum);
            pq.add(sum);
        }
        while (!pq.isEmpty()) {
            answer += pq.poll();
        }
        return answer;
    }
}
