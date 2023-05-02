package week10;

import java.util.Arrays;
import java.util.Comparator;

class 단속카메라 {
	public int solution(int[][] routes) {
		int answer = 1;
		Arrays.sort(routes, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		// 첫번째 차량이 나가는 지점
		int camera = routes[0][1];
		for (int i = 1; i < routes.length; i++) {
			int[] next_car = routes[i];
			// 나가는 차량의 나가는 시점이 카메라 안에 들어오면 앞당겨줌
			if (next_car[1] < camera) {
				camera = next_car[1];
			}
			// 카메라보다 진입점이 더 크면 카메라가 하나 더 필요
			if (camera < next_car[0]) {
				camera = next_car[1];
				answer++;
			}
		}
		return answer;
	}
}