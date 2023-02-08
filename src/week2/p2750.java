package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p2750 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = parseInt(br.readLine());
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = parseInt(br.readLine());
		}
		Arrays.sort(array);
		for (int i : array) {
			System.out.println(i);
		}
		br.close();
	}
	
	static int parseInt(String input) {
		return Integer.parseInt(input);
	}
}
