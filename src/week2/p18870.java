package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class p18870 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int[] list = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		HashMap<Integer, Integer> hm = new HashMap();
		int[] sortedList = Arrays.stream(list)
				.distinct()
				.sorted()
				.toArray();
		int count = 0;
		for (int i : sortedList) {
			hm.put(i, count++);
		}
		for (int j : list) {
			sb.append(hm.get(j) + " ");
		}
		System.out.println(sb);
		br.close();
	}
}
