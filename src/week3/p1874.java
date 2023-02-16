package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class p1874 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<Integer>();
		// 랜덤 수열
		int number = 1;
		boolean flag = true;
		for (int i = 0; i < n; i++) {
			int target = Integer.parseInt(br.readLine());
			// 이미 들어 간 수
			if (target < number) {
				if (stack.peek() == target) {
					stack.pop();
					sb.append("-\n");
				} else {
					flag = false;
				}
			} else {				
				while (number <= target) {
					sb.append("+\n");
					stack.push(number++);
				}
				sb.append("-\n");
				stack.pop();
			}
		}
		if (flag) {
			System.out.println(sb);
		} else {
			System.out.println("NO");
		}
		br.close();
	}
}
