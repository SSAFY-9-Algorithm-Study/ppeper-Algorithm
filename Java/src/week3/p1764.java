package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class p1764 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Set<String> setA = new HashSet<String>();
		Set<String> setB = new HashSet<String>();
		for (int i = 0; i < n; i++) {
			setA.add(br.readLine());
		}
		for (int i = 0; i < m; i++) {
			setB.add(br.readLine());
		}
		List<String> filter = setA.stream()
			.filter(o -> setB.contains(o))
			.sorted()
			.collect(Collectors.toList());
		System.out.println(filter.size());
		for (String value : filter) {
			System.out.println(value);
		}
		br.close();
	}
}
