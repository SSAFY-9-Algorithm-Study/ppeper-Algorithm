package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class p1427 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Arrays.stream(br.readLine().split("")) // String[]
			.sorted(Collections.reverseOrder())
			.forEach(System.out::print);
		br.close();
	}
}
