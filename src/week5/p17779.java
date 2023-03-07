package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p17779 {
	
	static int answer, sum, n, d1, d2;
	static int[][] map;
	static boolean[][] visited;
	static int[] area;
	static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		answer = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int input = Integer.parseInt(st.nextToken());
				map[i][j] = input;
				sum += input;
			}
		}
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				for (d1 = 1; d1 < n; d1++) {
					for (d2 = 1; d2 < n; d2++) {
						if (x + d1 + d2 < n && 0 <= y - d1 && y + d2 < n) {
							devide(x, y);							
						}
					}
				}
			}
		}
		System.out.println(answer);
	}

	private static void devide(int x, int y) {
		visited = new boolean[n][n];
		for (int i = 0; i <= d1; i++) {
			visited[x + i][y - i] = true;
			visited[x + d2 + i][y + d2 - i] = true;
		}
		for (int i = 0; i <= d2; i++) {
			visited[x + i][y + i] = true;
			visited[x + d1 + i][y - d1 + i] = true;
		}
		area = new int[6];
		// 1 구역
		for (int i = 0; i < x + d1; i++) {
			for (int j = 0; j <= y; j++) {
				if (visited[i][j]) break;
				area[1] += map[i][j];
			}
		}
		// 2 구역
		for (int i = 0; i <= x + d2; i++) {
			for (int j = n - 1; y < j; j--) {
				if (visited[i][j]) break;
				area[2] += map[i][j];
			}
		}
		// 3 구역
		for (int i = x + d1; i < n; i++) {
			for (int j = 0; j < y - d1 + d2; j++) {
				if (visited[i][j]) break;
				area[3] += map[i][j];
			}
		}
		// 4 구역
		for (int i = x + d2 + 1; i < n; i++) {
			for (int j = n - 1; y - d1 + d2 <= j; j--) {
				if (visited[i][j]) break;
				area[4] += map[i][j];
			}
		}
		// 5구역 -> 전체에서 나머지 빼기
		int total = sum;
		for (int i = 1; i < area.length; i++) {
			total -= area[i];
		}
		area[5] = total;
		calculateDiff();
	}

	private static void calculateDiff() {
		Arrays.sort(area);
		answer = Math.min(answer, area[5] - area[1]);
	}
}
