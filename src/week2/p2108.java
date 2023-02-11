package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class p2108 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] list = new int[n];
		// 중앙 index
		int mid = n / 2;
		// 배열의 합
		double sum = 0;
		// 최빈값
		int frequency = 0;
		HashMap<Integer, Integer> hm = new HashMap();
		for (int i = 0; i < n; i++) {
			int number = Integer.parseInt(br.readLine());
			sum += number;
			list[i] = number;
			hm.put(number, hm.getOrDefault(number, 0) + 1);
		}
		// value 기준 내림차순
		List<Integer> keySet = new ArrayList(hm.keySet());
		Collections.sort(keySet, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// 최빈값이 같으면 -> key순 오름차순
				if (hm.get(o2) == hm.get(o1)) {
					return o1 - o2;
				}
				// 기본은 value값 내림차순으로 -> 최빈값
				return hm.get(o2) - hm.get(o1);
			}
		});
		// n이 1 or 첫번째 값이 가장 크면 첫번째 값
		if (n == 1 || hm.get(keySet.get(1)) < hm.get(keySet.get(0))) {
			frequency = keySet.get(0);
		} else {
			frequency = keySet.get(1);
		}
		Arrays.sort(list);
		System.out.println(Math.round(sum / n));			
		System.out.println(list[mid]);
		System.out.println(frequency);
		System.out.println(list[n - 1] - list[0]);
		br.close();
	}
}
