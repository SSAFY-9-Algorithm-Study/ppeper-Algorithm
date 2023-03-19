package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class p2206 {
	
	static class Point {
		int x, y, dist, brick;

		public Point(int x, int y, int dist, int brick) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.brick = brick;
		}
		
	}
	static char[][] map;
	static boolean[][][] visited;
	static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int n, m;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		visited = new boolean[n][m][2];
		search(0, 0);
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}

	private static void search(int i, int j) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(i, j, 1, 0));
		visited[i][j][0] = true;
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			if (p.x == n - 1 && p.y == m - 1) {
				answer = Math.min(answer, p.dist);
				break;
			}
			for (int k = 0; k < 4; k++) {
				int nx = p.x + move[k][0], ny = p.y + move[k][1];
				if (nx < 0 || n <= nx || ny < 0 || m <= ny) continue;
				if (map[nx][ny] == '1') {
					// 한번만 부셔야함
					if (!visited[nx][ny][p.brick] && p.brick < 1) {
						visited[nx][ny][p.brick + 1] = true;
						queue.offer(new Point(nx, ny, p.dist + 1, p.brick + 1));
					}
				} else {
					if (!visited[nx][ny][p.brick]) {
						visited[nx][ny][p.brick] = true;
						queue.offer(new Point(nx, ny, p.dist + 1, p.brick));
					}
				}
			}
		}
	}
}
