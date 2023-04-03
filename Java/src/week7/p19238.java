package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class p19238 {
	
	static class Point implements Comparable<Point> {
		int x, y, dist;
		
		public Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Point p) {
			if (this.dist == p.dist) {
				if (this.x == p.x) {
					return this.y - p.y;
				}
				return this.x - p.x;				
			}
			return this.dist - p.dist;
		}
	}
	static int n, m, remain, dist, answer;
	static int[][] map;
	static boolean[][] visited;
	static Point[] dest;
	static Point taxi;
	static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		remain = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		dest = new Point[m + 1];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input == 1) {
					map[i][j] = -1;					
				} else {
					map[i][j] = input;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken()) - 1;
		int b = Integer.parseInt(st.nextToken()) - 1;
		taxi = new Point(a, b, 0);
		// 승객들
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			map[x][y] = i;
			int dx = Integer.parseInt(st.nextToken()) - 1;
			int dy = Integer.parseInt(st.nextToken()) - 1;
			dest[i] = new Point(dx, dy, 0);
		}
		// m명의 손님
		for (int i = 0; i < m; i++) {
			Point person = getClosablePerson(taxi);
			// 탑승객을 태울 수 없음
			if (person == null) {
				System.out.println(-1);
				return;
			}
			remain -= person.dist;
			if (remain < 0) {
				System.out.println(-1);
				return;
			}
			taxi = new Point(person.x, person.y, 0);
			int no = map[person.x][person.y];
			int goToDest = startToDest(taxi, no);
			if (goToDest == -1) {
				System.out.println(-1);
				return;
			}
			remain -= goToDest;
			if (remain < 0) {
				System.out.println(-1);
				return;
			}
			remain += goToDest * 2;
			// 승객 완료
			map[taxi.x][taxi.y] = 0;
			taxi = new Point(dest[no].x, dest[no].y, 0);
		}
		System.out.println(remain);
	}

	private static int startToDest(Point taxi, int no) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(taxi);
		visited = new boolean[n][n];
		visited[taxi.x][taxi.y] = true;
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			if (p.x == dest[no].x && p.y == dest[no].y) {
				return p.dist;
			}
			for (int i = 0; i < 4; i++) {
				int nx = p.x + move[i][0], ny = p.y + move[i][1];
				if (nx < 0 || n <= nx || ny < 0 || n <= ny) continue;
				if (visited[nx][ny]) continue;
				
				if (0 <= map[nx][ny]) {
					queue.offer(new Point(nx, ny, p.dist + 1));
					visited[nx][ny] = true;
				}
			}
		}
		return -1;
	}

	private static Point getClosablePerson(Point taxi) {
		Queue<Point> queue = new LinkedList<>();
		PriorityQueue<Point> pq = new PriorityQueue<>();
		queue.offer(taxi);
		visited = new boolean[n][n];
		if (0 < map[taxi.x][taxi.y]) {
			return taxi;
		}
		visited[taxi.x][taxi.y] = true;
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			// 도착 지점
			for (int i = 0; i < 4; i++) {
				int nx = p.x + move[i][0], ny = p.y + move[i][1];
				if (nx < 0 || n <= nx || ny < 0 || n <= ny) continue;
				if (visited[nx][ny]) continue;
				
				if (0 <= map[nx][ny]) {
					if (0 < map[nx][ny]) {
						// 도착 지점
						pq.offer(new Point(nx, ny, p.dist + 1));
					}
					queue.offer(new Point(nx, ny, p.dist + 1));
					visited[nx][ny] = true;
				}
			}
		}
		if (!pq.isEmpty()) {
			return pq.poll();
		}
		return null;
	}
}
