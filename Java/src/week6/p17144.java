package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p17144 {
	
	static int[][] map;
	static int r, c, t;
	static int top, bottom;
	static int[][] move = {{1, 0}, {-1, 0}, {0 , 1}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (top == 0 && input == -1) {
					top = i;
					bottom = i + 1;
				}
				map[i][j] = input;
			}
		}
		while (0 < t--) {
			diffusion();
			cleaner();
		}
		System.out.println(calculate() + 2);
	}

	// 미세먼지 확산 map
	private static void diffusion() {
		int[][] spreadMap = new int[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == -1) {
					spreadMap[i][j] = -1;
				} else {
					spreadMap[i][j] += map[i][j];
					for (int k = 0; k < 4; k++) {
						int nx = i + move[k][0], ny = j + move[k][1];
						if (nx < 0 || r <= nx || ny < 0 || c <= ny) continue;
						if (map[nx][ny] == -1) continue;
						spreadMap[nx][ny] += map[i][j] / 5;
						spreadMap[i][j] -= map[i][j] / 5;
					}
				}
			}
		}
		map = spreadMap;
	}

	private static void cleaner() {
		// 공기청정기 -> Top
		// 아래쪽
		for (int i = top - 1; 0 < i; i--) {
			map[i][0] = map[i - 1][0];
		}
		// 왼쪽
		for (int i = 0; i < c - 1; i++) {
			map[0][i] = map[0][i + 1];
		}
		// 위쪽
		for (int i = 0; i < top; i++) {
			map[i][c - 1] = map[i + 1][c - 1];
		}
		// 오른쪽
		for (int i = c - 1; 1 < i; i--) {
			map[top][i] = map[top][i - 1];
		}
		map[top][1] = 0;
		
		// 공기청정기 -> Bottom
		// 위쪽
		for (int i = bottom + 1; i < r - 1; i++) {
			map[i][0] = map[i + 1][0];
		}
		// 왼쪽
		for (int i = 0; i < c - 1; i++) {
			map[r - 1][i] = map[r - 1][i + 1];
		}
		// 아래쪽
		for (int i = r - 1; bottom < i; i--) {
			map[i][c - 1] = map[i - 1][c - 1];
		}
		// 오른쪽
		for (int i = c - 1; 1 < i; i--) {
			map[bottom][i] = map[bottom][i - 1];
		}
		map[bottom][1] = 0;
	}
	
	private static int calculate() {
		return Arrays.stream(map)
				.flatMapToInt(Arrays::stream)
				.sum();
	}
}
