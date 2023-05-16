package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class 줄기세포배양 {
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int[][] map;
	static boolean[][] visited;
	static int n, m, k;
	
	static Queue<Cell> queue;
	
	static class Cell {
		int life;
		int x, y, remain;
		boolean isActivate = false, isDead = false;
		
		public Cell(int x, int y, int life) {
			this.x = x;
			this.y = y;
			this.life = life;
			remain = life * 2;
		}
		
		private void timeMove() {
			if (!isDead) {
				remain--;
				if (remain == life) {
					isActivate = true;
				}
				if (remain == 0) {
					isDead = true;
				}
			}
		}	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = atoi(br.readLine());
		for (int testCase = 1; testCase <= t; testCase++) {
			st = new StringTokenizer(br.readLine());
			n = atoi(st.nextToken());
			m = atoi(st.nextToken());
			k = atoi(st.nextToken());
			map = new int[1000][1000];
			visited = new boolean[1000][1000];
			queue = new LinkedList<Cell>();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					int value = atoi(st.nextToken());
					map[i + k][j + k] = value;
					if (0 < value) {
						visited[i + k][j + k] = true;
						queue.add(new Cell(i + k, j + k, value));
					}
				}
			}
			while (0 < k--) {
				markMap();
				bfs();
			}
			System.out.printf("#%d %d\n", testCase, queue.size());
		}
	}
	
	private static void markMap() {
		for (Cell cell : queue) {
			if (cell.isActivate) {
				check(cell);
			}
		}
	}

	private static void check(Cell cell) {
		for (int i = 0; i < 4; i++) {
			int nx = cell.x + dx[i], ny = cell.y + dy[i];
			
			if (visited[nx][ny]) continue;
			// 더 생명력 수치가 높은 줄기가 번식
			if (map[nx][ny] < cell.life) {
				map[nx][ny] = cell.life;
			}
		}	
	}

	private static void bfs() {
		int size = queue.size();
		while (0 < size--) {
			Cell cell = queue.poll();
			if (cell.isActivate) {
				for (int i = 0; i < 4; i++) {
					int nx = cell.x + dx[i], ny = cell.y + dy[i];
					if (visited[nx][ny]) continue;
					visited[nx][ny] = true;
					queue.add(new Cell(nx, ny, map[nx][ny]));
				}
			}
			cell.timeMove();
			if (!cell.isDead) {
				queue.add(cell);
			}
		}
	}

	public static int atoi(String input) {
		return Integer.parseInt(input);
	}

}
