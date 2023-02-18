package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

class Number implements Comparable<Number> {
	int pos, count;

	public Number(int pos, int count) {
		super();
		this.pos = pos;
		this.count = count;
	}

	@Override
	public int compareTo(Number number) {
		if (this.count == number.count) {
			// 순서대로 정렬
			return this.pos - number.pos;
		}
		return number.count - this.count;
	}
		
}
public class p2910 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		ArrayList<Integer> list = new ArrayList<Integer>();
		HashMap<Integer, Number> map = new HashMap<Integer, Number>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int key = Integer.parseInt(st.nextToken());
			list.add(key);
			if (map.containsKey(key)) {
				Number curr = map.get(key);
				curr.count++;
			} else {
				map.put(key, new Number(i, 1));
			}
		}
		List<Map.Entry<Integer, Number>> entry = map.entrySet().stream()
			.sorted(Map.Entry.comparingByValue())
			.collect(Collectors.toList());
		for (Entry<Integer, Number> e : entry) {
			int key = e.getKey();
			int count = e.getValue().count;
			// count 만큼 출력
			for (int i = 0; i < count; i++) {
				sb.append(key + " ");
			}
		}
		System.out.println(sb);
		br.close();
	}
}
