package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p17822 {
	
	static int n, m, t;
	static int[][] map;
	static int[][] list;
	static int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		map = new int[n + 1][m];
		list = new int[t][3];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 회전
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			list[i][0] = Integer.parseInt(st.nextToken());
			list[i][1] = Integer.parseInt(st.nextToken());
			list[i][2] = Integer.parseInt(st.nextToken());
		}
		// 원판 회전
		for (int i = 0; i < list.length; i++) {
			int[] rotate = list[i];
			int x = rotate[0];
			int d = rotate[1];
			int k = rotate[2];
			int m = 1;
			while (true) {
				int idx = x * m;
				if (map.length <= idx) {
					break;
				}
				rotateCircle(map[idx], d, k);
				m++;
			}
			// 인접 숫자 제거
			boolean check = remove();
			// 인접한 수가 없는 경우
			if (!check) {
				calculate();
			}
		}
		int sum = Arrays.stream(map).flatMapToInt(Arrays::stream).sum();
		System.out.println(sum);
	}

	private static void calculate() {
		double sum = 0;
		int count = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 0) {
					sum += map[i][j];
					count++;
				}
			}
		}
		double average = sum / count;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) continue;
				if (map[i][j] < average) {
					map[i][j] += 1;
				} else if (average < map[i][j]){
					map[i][j] -= 1;
				}
			}
		}
	}

	private static boolean remove() {
		boolean check = false;
		boolean[][] visited = new boolean[n + 1][m];
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j] && map[i][j] != 0) {
					Queue<int[]> queue = new LinkedList<>();
					queue.offer(new int[] {i, j});
					while (!queue.isEmpty()) {
						int[] curr = queue.poll();
						int value = map[curr[0]][curr[1]];
						for (int k = 0; k < 4; k++) {
							int nx = curr[0] + move[k][0], ny = curr[1] + move[k][1];
							if (nx < 1 || n < nx || ny < 0 || m <= ny) continue;
							if (visited[nx][ny]) continue;
							if (map[nx][ny] == value) {
								check = true;
								queue.offer(new int[] {nx, ny});
								visited[nx][ny] = true;
							}
							// 맨 끝일때
							if (curr[1] == m - 1 && !visited[curr[0]][0]) {
								if (value == map[curr[0]][0]) {
									check = true;
									queue.offer(new int[] {curr[0], 0});
									visited[curr[0]][0] = true;
								}
							}
							// 시작점일 때
							if (curr[1] == 0 && !visited[curr[0]][m - 1]) {
								if (value == map[curr[0]][m-1]) {
									check = true;
									queue.offer(new int[] {curr[0], m-1});
									visited[curr[0]][m - 1] = true;
								}
							}
						}
					}
				}
			}
		}
		// 방문 -> 인접한 같은 숫자들 -> 0처리
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < m; j++) {
				if (visited[i][j]) {
					map[i][j] = 0;
				}
			}
		}
		return check;
	}

	private static void rotateCircle(int[] list, int d, int k) {
		int rotateCount = k % m;
		if (d == 0) {
			while (0 < rotateCount--) {
				int temp = list[m - 1];
				for (int i = m - 1; 0 < i; i--) {
					list[i] = list[i - 1];
				}
				list[0] = temp;
			}
		} else {
			while (0 < rotateCount--) {
				int temp = list[0];
				for (int i = 0; i < m - 1; i++) {
					list[i] = list[i + 1];
				}
				list[m - 1] = temp;
			}
		}
	}

}
