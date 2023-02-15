package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class p1822 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Set<String> setA = new TreeSet<String>(Arrays.asList(br.readLine().split(" ")));
		Set<String> setB = new TreeSet<String>(Arrays.asList(br.readLine().split(" ")));
		setA.removeAll(setB);
		System.out.println(setA.size());
		setA.stream()
			.mapToInt(Integer::parseInt)
			.sorted()
			.forEach(o -> System.out.print(o + " "));
		br.close();
	}
}
