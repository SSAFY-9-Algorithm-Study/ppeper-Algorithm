package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class p21610 {
	
	static class Cloud {
		int x, y;

		public Cloud(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	static LinkedList<Cloud> cloud;
	static int[][] map;
	static boolean[][] state;
	static int n;
	// ←, ↖, ↑, ↗, →, ↘, ↓, ↙
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1}; 
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1}; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		state = new boolean[n][n];
		cloud = new LinkedList<>();
		initCloud(n - 1);
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 마법 초기화
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int move = Integer.parseInt(st.nextToken());
			// 구름 이동
			moveCloud(dir - 1, move);
			rain();
			createCloud();
		}
		System.out.println(calculateWater());
	}

	private static void moveCloud(int dir, int move) {
		for (int i = 0; i < cloud.size(); i++) {
			Cloud c = cloud.poll();
			int nx = c.x + dx[dir] * (move % n), ny = c.y + dy[dir] * (move % n);
	        if (n <= nx) nx -= n;
            if (n <= ny) ny -= n;
            if (nx < 0) nx += n;
            if (ny < 0) ny += n;
            state[nx][ny] = true;
            cloud.addLast(new Cloud(nx, ny));
		}
	}
	
	private static void rain() {
		// 비내리기
		for (Cloud c : cloud) {
			map[c.x][c.y] += 1; 
		}
		// 대각선 방향 확인
		while (!cloud.isEmpty()) {
			Cloud c = cloud.poll();
			int count = 0;
			// 대각선
			for (int i = 1; i < 8; i += 2) {
				int nx = c.x + dx[i], ny = c.y + dy[i];
				if (nx < 0 || n <= nx || ny < 0 || n <= ny) continue;
				if (map[nx][ny] == 0) continue;
				count++;
			}
			// 물추가
			map[c.x][c.y] += count;
		}
	}

	private static void createCloud() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 구름이 있었던 칸은 제외
				if (state[i][j]) {
					state[i][j] = false;
				} else if (2 <= map[i][j]) {
					map[i][j] -= 2;
					cloud.add(new Cloud(i, j));
				}
			}
		}
	}


	private static int calculateWater() {
		return Arrays.stream(map)
				.flatMapToInt(Arrays::stream)
				.sum();
	}
	
	private static void initCloud(int n) {
		cloud.add(new Cloud(n, 0));
		cloud.add(new Cloud(n, 1));
		cloud.add(new Cloud(n - 1, 0));
		cloud.add(new Cloud(n - 1, 1));		
	}
}
