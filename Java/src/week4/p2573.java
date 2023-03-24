package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2573 {
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int year = 0;
		map = new int[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		while (true) {
			int land = checkLand();
			if (1 < land) {
				break;
			}
			if (land == 0) {
				year = 0;
				break;
			}
			melting();
			year++;
			visited = new boolean[n][m];
		}
		System.out.println(year);
	}

	// 두개 이상으로 나누어 졌을때
	private static int checkLand() {
		Queue<Point> queue = new LinkedList<>();
		int land = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (!visited[i][j] && map[i][j] != 0) {
					land++;
					queue.offer(new Point(i, j));
					visited[i][j] = true;
					while (!queue.isEmpty()) {
						Point p = queue.poll();
						for (int k = 0; k < dx.length; k++) {
							int cx = p.x + dx[k];
							int cy = p.y + dy[k];
							if (!visited[cx][cy] && map[cx][cy] != 0) {
								visited[cx][cy] = true;
								queue.offer(new Point(cx, cy));
							}
						}
					}
				}
			}
		}
		return land;
	}

	private static void melting() {
		visited = new boolean[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (!visited[i][j] && map[i][j] != 0) {
					visited[i][j] = true;
					for (int k = 0; k < dx.length; k++) {
						int cx = i + dx[k];
						int cy = j + dy[k];
						if (!visited[cx][cy] && map[cx][cy] == 0) {
							if (1 <= map[i][j]) {
								map[i][j]--;
							}
						}
					}
				}
			}
		}
	}

}