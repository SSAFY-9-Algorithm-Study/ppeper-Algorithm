package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 출력: 다 녹는데 걸리는 시간, 녹기 1시간 전 치즈의 개수
 * 문제의 가장자리 -> 동서남북 방향중 하나라도 0이면 가장자리다 -> 녹음
 */

public class p2636 {
	
	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int[][] map;
	static boolean[][] visited;
	static int cheeze = 0;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int time = 0;
		map = new int[n][m];
		visited = new boolean[n][m];
		boolean flag = true;
		for (int i = 0; i < n; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		cheeze = checkCheeze();
		// 처음부터 녹을 치즈가 없다면
		if (cheeze == 0) {
			flag = false;
		}
		while (flag) {
			bfs(0, 0);
			time++;
			visited = new boolean[n][m];
			int count = checkCheeze();
			// count = 0 -> 모든 치즈가 녹음
			if (count == 0) {
				flag = false;
			} else {
				cheeze = count;	
			}
		}
		System.out.println(time);
		System.out.println(cheeze);
	}

	private static void bfs(int i, int j) {
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
					if (!visited[cx][cy] && map[cx][cy] == 0) {
						visited[cx][cy] = true;
						queue.offer(new Point(cx, cy));
					}
					if (!visited[cx][cy] && map[cx][cy] == 1) {
						visited[cx][cy] = true;
						map[cx][cy] = 0;
					}
				}
			}
		}
	}
	
	private static int checkCheeze() {
		int count = 0;
		for (int[] list : map) {
			count += Arrays.stream(list).filter(o -> o == 1).count();
		}
		return count;
	}
	
	private static boolean checkRange(int i, int j) {
		return (0 <= i && i < map.length && 0 <= j && j < map[0].length);
	}
}