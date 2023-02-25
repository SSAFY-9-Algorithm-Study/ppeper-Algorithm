package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2589 {
	
	static class Point {
		int x, y, move;
		public Point(int x, int y, int move) {
			this.x = x;
			this.y = y;
			this.move = move;
		}
	}
	
	static String[][] map;
	static boolean[][] visited;
	static int distance = 0;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new String[n][m];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().split("");
		}
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].equals("L")) {
					visited = new boolean[n][m];
					Queue<Point> queue = new LinkedList<>();
					queue.offer(new Point(i, j, 0));
					visited[i][j] = true;
					while (!queue.isEmpty()) {
						Point p = queue.poll();
						distance = Math.max(distance, p.move);
						for (int k = 0; k < 4; k++) {
							int cx = p.x + dx[k];
							int cy = p.y + dy[k];
							if (checkRange(cx, cy) && !visited[cx][cy] && map[cx][cy].equals("L")) {
								visited[cx][cy] = true;
								queue.offer(new Point(cx, cy, p.move + 1));
							}
						}
					}
				}
			}
		}
		System.out.println(distance);
	}
	
	private static boolean checkRange(int i, int j) {
		return (0 <= i && i < map.length && 0 <= j && j < map[0].length);
	}
}
