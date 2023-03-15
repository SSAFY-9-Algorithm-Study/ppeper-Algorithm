package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p23288 {

	static class Dice {
		int x, y, dir;

		public Dice(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

	}

	static int[][] map;
	static boolean[][] visited;
	// 동남서북
	static int[][] move = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	// 아래가 index = 4
	static int[] square = { 1, 2, 4, 3, 5, 6 };
	static int n, m, answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		moveDice(new Dice(0, 0, 0), k);
		System.out.println(answer);
	}

	private static void moveDice(Dice dice, int k) {
		if (k == 0) {
			return;
		}
		// 1. 주사위 이동
		int nx = dice.x + move[dice.dir][0], ny = dice.y + move[dice.dir][1];
		// 이동 불가 -> 반대 방향으로 이동
		if (nx < 0 || n <= nx || ny < 0 || m <= ny) {
			nx = dice.x + move[(dice.dir + 2) % 4][0];
			ny = dice.y + move[(dice.dir + 2) % 4][1];
			dice.dir = (dice.dir + 2) % 4;
		}
		calculateScore(nx, ny, map[nx][ny]);
		changeDice(dice.dir);
		int dir = checkDir(map[nx][ny], dice.dir);
		moveDice(new Dice(nx, ny, dir), k - 1);
	}

	private static int checkDir(int value, int dir) {
		int newDir = dir;
		// A > B -> 시계방향 회전
		if (value < square[5]) {
			newDir = (newDir + 1) % 4;
		} else if (square[5] < value) {
			newDir = (newDir + 3) % 4;
		}
		return newDir;
	}

	private static void changeDice(int dir) {
//		int[] squa1re = { 1, 2, 4, 3, 5, 6 };
//		int[] squ23are = { 4, 2, 6, 1, 5, 3 };
//		int[] squa3r2e = { 3, 2, 1, 6, 5, 4 };
//		int[] squa3re = { 2, 6, 4, 3, 1, 5 };
//		int[] squa2re = { 5, 1, 4, 3, 6, 2 };
		// 동서남북
		switch (dir) {
		case 0:
			square = new int[]{square[2], square[1], square[5], square[0], square[4], square[3]};
			break;
		case 1:
			square = new int[]{square[1], square[5], square[2], square[3], square[0], square[4]};
			break;
		case 2:
			square = new int[]{square[3], square[1], square[0], square[5], square[4], square[2]};
			break;
		case 3:
			square = new int[]{square[4], square[0], square[2], square[3], square[5], square[1]};
			break;
		}
	}

	private static void calculateScore(int x, int y, int score) {
		visited = new boolean[n][m];
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { x, y});
		visited[x][y] = true;
		int count = 0;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			count++;
			for (int i = 0; i < 4; i++) {
				int nx = curr[0] + move[i][0], ny = curr[1] + move[i][1];
				if (nx < 0 || n <= nx || ny < 0 || m <= ny)	continue;
				if (visited[nx][ny]) continue;
				
				if (score == map[nx][ny]) {
					queue.offer(new int[] { nx, ny});
					visited[nx][ny] = true;
				}
			}
		}
		answer += score * count;
	}
}
