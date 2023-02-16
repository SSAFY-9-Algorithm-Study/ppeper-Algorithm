package week3;

/**
 * 예시 1
 * 1,2,3,4,5,6,7 
 * 맨위 버리고 다음 카드 아래로
 * 3,4,5,6,7,2 | 1
 * 5,6,7,2,4 | 1,3
 * 7,2,4,6 | 1,3,5
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class p2161 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 1; i <= n; i++) {
			queue.offer(i);
		}
		while (1 < queue.size()) {
			sb.append(queue.poll() + " ");
			queue.offer(queue.poll());
		}
		sb.append(queue.poll());
		System.out.println(sb);
		br.close();
	}
}
