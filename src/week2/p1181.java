package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class p1181 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		ArrayList<String> words = new ArrayList();
		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			if (!words.contains(input)) {
				words.add(input);
			}
		}
		Collections.sort(words, new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() == o2.length()) {
					return o1.compareTo(o2);
				}
				return o1.length() - o2.length();
			};
		});
		for (String word : words) {
			System.out.println(word);
		}
//		String[] words = new String[n];
//		for (int i = 0; i < n; i++) {
//			words[i] = br.readLine();
//		}
//		Arrays.stream(words)
//			.distinct()
//			.sorted(Comparator.comparing(String::length)
//					.thenComparing(Comparator.naturalOrder()))
//			.forEach(System.out::println);
	}
}
