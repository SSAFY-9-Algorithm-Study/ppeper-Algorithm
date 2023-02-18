package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class p10815 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());		
		Set<Integer> set = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.boxed()
				.collect(Collectors.toSet());
		int m = Integer.parseInt(br.readLine());
		int[] list = new int[m];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		for (int value : list) {
			if (set.contains(value)) {
				sb.append(1 + " ");
			} else {
				sb.append(0 + " ");
			}
		}
		System.out.println(sb);
		br.close();
	}
}
