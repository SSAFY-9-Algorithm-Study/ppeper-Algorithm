package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class p2587 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int[] array = new int[5];
		for (int i = 0; i < 5; i++) {
			array[i] = parseInt(br.readLine());
		}
		Arrays.stream(array);
		sb.append((int)Arrays.stream(array).average().getAsDouble() + "\n");
		sb.append(array[2]);
		System.out.println(sb);
		br.close();
	}
	
	static int parseInt(String input) {
		return Integer.parseInt(input);
	}
}
