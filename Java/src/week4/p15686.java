package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 치킨배달
 * 1. 맨허튼 거리로 구현
 * 2. 도시의 치킨거리 -> 모든집의 치킨거리의 합
 * 3. M개를 제외한 치킨집은 폐업
 * 4. 도시의 치킨 거리가 가장 작게 -> static answer값 업데이트
 */
public class p15686 {
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	static ArrayList<Point> chicken;
	static ArrayList<Point> house;
	static boolean[] visited;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		chicken = new ArrayList<>();
		house = new ArrayList<>();
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			// 치킨과 집의 좌표들 저장
			for (int j = 0; j < n; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input == 1) {
					house.add(new Point(i, j));
				}
				if (input == 2) {
					chicken.add(new Point(i, j));
				}
			}
		}
		visited = new boolean[chicken.size()];
		pickChicken(0, m);
		System.out.println(answer);
	}

	private static void pickChicken(int start, int m) {
		// 치킨집을 다 골랐다.
		if (m == 0) {
			checkDistance();
			return;
		}
		for (int i = start; i < chicken.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				pickChicken(i + 1, m - 1);
				visited[i] = false;
			}
		}
	}

	private static void checkDistance() {
		int sum = 0;
		for (Point from : house) {
			int min = Integer.MAX_VALUE;
			// 한집에서 치킨집까지중에 가장 짧은 거리
			for (int i = 0; i < visited.length; i++) {
				if (visited[i]) {
					min = Math.min(min, distance(from, chicken.get(i)));
				}
			}
			sum += min;
		}
		answer = Math.min(answer, sum);
	}
	
	private static int distance(Point from, Point to) {
		return Math.abs(from.x - to.x) + Math.abs(from.y - to.y);
	}
}