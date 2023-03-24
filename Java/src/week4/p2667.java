package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 단지의 수를 출력
 * 단지안의 집의수를 오름차순으로 출력
 * 1. 단지를 넣어줄 list 필요
 * 2. bfs를 통하여 주변 1을 탐색하여 단지를 구함
 * 3. 지나간 길은 visited배열에 추가하여 재방문 X
 *
 */
public class p2667 {
	
	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static ArrayList<Integer> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(visited[i], false);
		}
		list = new ArrayList<Integer>();
		for (int i = 0; i < map.length; i++) {
			map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}
		// bfs 탐색 시작
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					bfs(i, j);
				}
			}
		}
		System.out.println(list.size());
		list.stream()
			.sorted()
			.forEach(System.out::println);
	}

	private static void bfs(int i, int j) {
		int count = 0;
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(i, j));
		visited[i][j] = true;
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			count++;
			for (int k = 0; k < dx.length; k++) {
				int cx = p.x + dx[k];
				int cy = p.y + dy[k];
				// 범위 안에 들어올때
				if (checkRange(cx, cy)) {
					if (!visited[cx][cy] && map[cx][cy] == 1) {
						visited[cx][cy] = true;
						queue.offer(new Point(cx, cy));
					}
				}
			}
		}
		list.add(count);
	}
	
	private static boolean checkRange(int i, int j) {
		return (0 <= i && i < map.length && 0 <= j && j < map.length);
	}
}
