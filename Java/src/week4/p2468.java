package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  물에 잠기는 영역은 배열의 최소~최대값 - 1까지만 확인해보면 된다
 *  bfs 탐색을 할때 -> 주어진 물의 양보다 클때 확인하며 돈다
 *
 */
public class p2468 {
	
	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int[][] map;
	static boolean[][] visited;
	static int safeZone = 1;
	static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int input = Integer.parseInt(st.nextToken());
				min = Math.min(input, min);
				max = Math.max(input, max);
				map[i][j] = input;
			}
		}
		// 물이 잠기는 영역을 확인
		for (int i = min; i < max; i++) {
			visited = new boolean[n][n];
			int count = 0;
			for (int a = 0; a < n; a++) {
				for (int b = 0; b < n; b++) {
					// 방문 X, 물에 안잠긴 지역
					if (!visited[a][b] && i < map[a][b]) {
						bfs(a, b, i);
						count++;
					}
				}
			}
			safeZone = Math.max(safeZone, count);
		}
		System.out.println(safeZone);
	}

	private static void bfs(int i, int j, int range) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(i, j));
		visited[i][j] = true;
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			for (int k = 0; k < dx.length; k++) {
				int cx = p.x + dx[k];
				int cy = p.y + dy[k];
				// 범위 안에 들어올때
				if (checkRange(cx, cy)) {
					if (!visited[cx][cy] && range < map[cx][cy]) {
						visited[cx][cy] = true;
						queue.offer(new Point(cx, cy));
					}
				}
			}
		}
	}
	
	private static boolean checkRange(int i, int j) {
		return (0 <= i && i < map.length && 0 <= j && j < map.length);
	}
}