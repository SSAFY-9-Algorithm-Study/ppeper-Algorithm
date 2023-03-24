package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class p12605 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		Stack<String> words = new Stack();
		for (int i = 0; i < n; i++) {
			sb.append("Case #").append(i + 1).append(": ");
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				words.add(st.nextToken());
			}
			while (!words.isEmpty()) {
				sb.append(words.pop() + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}
