package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p14500 {
	
	static int[][] map;
	static boolean[][] visited;
	// 상하좌우
	static int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int n, m, answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m]; 
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// dfs 탐색
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				visited[i][j] = true;
				dfs(i, j, 0, map[i][j]);
				purple(i, j);
				visited[i][j] = false;
			}
		}
		System.out.println(answer);
	}

	// 상하좌우 중 하나를 제거 or 3개라면 모양 완성
	private static void purple(int x, int y) {
		int sum = map[x][y];
		int count = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + move[i][0], ny = y + move[i][1];
			if (nx < 0 || n <= nx || ny < 0 || m <= ny) continue;
			count++;
			sum += map[nx][ny];
		}
		if (count == 3) {
			answer = Math.max(answer, sum);
		} else if (count == 4) {
			for (int i = 0; i < 4; i++) {
				int nx = x + move[i][0], ny = y + move[i][1];
				// 방향중 하나를 뺀다
				answer = Math.max(answer, sum - map[nx][ny]);
			}
		}
	}

	private static void dfs(int x, int y, int count, int sum) {
		if (count == 3) {
			answer = Math.max(answer, sum);
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nx = x + move[i][0], ny = y + move[i][1];
			if (nx < 0 || n <= nx || ny < 0 || m <= ny) continue;
			if (visited[nx][ny]) continue;
			
			visited[nx][ny] = true;
			dfs(nx, ny, count + 1, sum + map[nx][ny]);
			visited[nx][ny] = false;
		}
	}
}
