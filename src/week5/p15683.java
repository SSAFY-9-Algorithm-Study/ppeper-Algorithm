package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class p15683 {
	
	static class CCTV {
		int number, x, y, rotateCount;

		public CCTV(int number, int x, int y, int rotateCount) {
			this.number = number;
			this.x = x;
			this.y = y;
			this.rotateCount = rotateCount;
		}
		
	}
	static int[][] move = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
	static int[][] base = {
			{0},
			{0, 2},
			{0, 1},
			{0, 1, 3},
			{0, 1, 2, 3}
	};
	static int blind = 64;
	static List<CCTV> list;
	static Set<Integer> cctvNumberList;
	static int n, m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		cctvNumberList = new HashSet<>();
		setCCTV();
		int[][] map = new int[n][m];
		list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int input = Integer.parseInt(st.nextToken());
				map[i][j] = input;
				// cctv 저장
				switch (input) {
				case 1:
					list.add(new CCTV(input - 1, i, j, 3));
					break;
				case 2:
					list.add(new CCTV(input - 1, i, j, 1));
					break;
				case 3:
					list.add(new CCTV(input - 1, i, j, 3));
					break;
				case 4:
					list.add(new CCTV(input - 1, i, j, 3));
					break;
				case 5:
					list.add(new CCTV(input - 1, i, j, 0));
					break;
				}
			}
		}
		// cctv로 감지
		checkDetectZone(map, 0);
		System.out.println(blind);
	}

	private static void setCCTV() {
		cctvNumberList.add(1);
		cctvNumberList.add(2);
		cctvNumberList.add(3);
		cctvNumberList.add(4);
		cctvNumberList.add(5);
	}

	private static void checkDetectZone(int[][] map, int depth) {
		if (depth == list.size()) {
			// 사각지대 구하기
			blind = Math.min(blind, checkMap(map));
			return;
		}
		CCTV cctv = list.get(depth);
		// cctv에 따라 회전하면서 detect
		for (int rotate = 0; rotate <= cctv.rotateCount; rotate++) {
			int[][] detectMap = detect(map, cctv, rotate);
			checkDetectZone(detectMap, depth + 1);
		}
	}
	
	private static int[][] detect(int[][] map, CCTV cctv, int rotate) {
		int[][] detectMap = copyMap(map);
		// 현재 cctv의 감시 방향
		int[] zone = base[cctv.number];
		// 모든 방향 탐지
		for (int dir : zone) {
			int x = cctv.x, y = cctv.y;
			while (true) {
				x += move[(dir + rotate) % 4][0];
				y += move[(dir + rotate) % 4][1];
				
				if (0 <= x && x < n && 0 <= y && y < m && detectMap[x][y] != 6) {
					// cctv는 패스
					if (cctvNumberList.contains(detectMap[x][y])) continue;
					// cctv가 감지
					detectMap[x][y] = -1;
				} else {
					break;
				}
			}
		}
		return detectMap;
	}

	private static int checkMap(int[][] map) {
		int count = 0;
		for (int[] array : map) {
			for (int i : array) {
				if (i == 0) {
					count++;
				}
			}
		}
		return count;
	}

	// 맵 변경
	private static int[][] copyMap(int[][] map) {
		int[][] copy = new int[n][m];
		for (int i = 0; i < n; i++) {
			copy[i] = map[i].clone();
		}
		return copy;
	}
}
