package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2178 {
	
	static class Point {
		int x, y, dist;
		
		public Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	
	static boolean[][] visited;
	static int[][] map;
	static int answer = Integer.MAX_VALUE;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		// init map
		for (int i = 0; i < map.length; i++) {
			map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}
		// bfs
		bfs(new Point(0, 0, 1));
		System.out.println(answer);
		br.close();
	}

	private static void bfs(Point p) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(p);
		visited[p.x][p.y]= true;
		while (!queue.isEmpty()) {
			Point curr = queue.poll();
			for (int i = 0; i < 4; i++) {
				int cx = curr.x + dx[i];
				int cy = curr.y + dy[i];
				if (checkRange(cx, cy) && !visited[cx][cy] && map[cx][cy] == 1) {
					// 도착 지점
					if (cx == map.length - 1 && cy == map[0].length - 1) {
						// 최단거리 업데이트
						answer = Math.min(answer, curr.dist + 1);
					} else {
						visited[cx][cy] = true;
						queue.offer(new Point(cx, cy, curr.dist + 1));
					}
				}
			}
		}
	}
	
	private static boolean checkRange(int x, int y) {
		return (0 <= x && x < map.length && 0 <= y && y < map[0].length);
	}
}
