package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class p4358 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TreeMap<String, Double> map = new TreeMap<String, Double>();
		String tree;
		int count = 0;
		while ((tree = br.readLine()) != null) {
			map.put(tree, map.getOrDefault(tree, 0.0) + 1);
			count++;				
		}
		// 출력
		while (!map.isEmpty()) {
			Map.Entry<String, Double> entry = map.pollFirstEntry();
			System.out.println(entry.getKey() + " " + String.format("%.4f", entry.getValue() / count * 100));
		}
		br.close();
	}
}
