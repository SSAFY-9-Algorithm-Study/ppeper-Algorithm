package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class p10026 {
	
	static char[][] map;
	static boolean[][] visited;
	static int[] answer;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		visited = new boolean[n][n];
		answer = new int[2];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					bfs(i, j, 0);
					answer[0]++;
				}
			}
		}
		for (int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], false);
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					bfs(i, j, 1);	
					answer[1]++;
				}
			}
		}
		System.out.println(answer[0] + " " + answer[1]);
	}

	private static void bfs(int x, int y, int state) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {x, y});
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int color = map[curr[0]][curr[1]];
			for (int i = 0; i < 4; i++) {
				int cx = curr[0] + dx[i];
				int cy = curr[1] + dy[i];
				if (checkRange(cx, cy) && !visited[cx][cy]) {
					if (state == 1) {
						if ((color == 'R' || color == 'G') 
								&& (map[cx][cy] == 'R' || map[cx][cy] == 'G')) {
							visited[cx][cy] = true;
							queue.offer(new int[] {cx, cy});
						} else {
							if (color == map[cx][cy]) {
								visited[cx][cy] = true;
								queue.offer(new int[] {cx, cy});
							}
						}
					} else {
						if (color == map[cx][cy]) {
							visited[cx][cy] = true;
							queue.offer(new int[] {cx, cy});
						}
					}
				}
			}
		}
	}
	
	private static boolean checkRange(int x, int y) {
		return (0 <= x && x < map.length && 0 <= y && y < map.length);
	}
}
