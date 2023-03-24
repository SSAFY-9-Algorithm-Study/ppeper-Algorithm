package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class p11866 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("<");
		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Queue<Integer> queue = new LinkedList();
		for (int i = 1; i <= input[0]; i++) {
			queue.offer(i);
		}
		while (!queue.isEmpty()) {
			// input[1] - 1번까지 뒤로 보내기
			for (int i = 0; i < input[1] - 1; i++) {
				queue.offer(queue.poll());
			}
			if (queue.size() == 1) {
				sb.append(queue.poll() + ">");
			} else {
				sb.append(queue.poll() + ", ");
			}
		}
		System.out.println(sb);
		br.close();
	}
}
