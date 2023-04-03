package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p20057 {
	
	static int[][] map;
	static int n, answer, maxMove = 2;
	static int[][] move = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
	static int[][] scatterX = {
			{-1, 1, -1, 1, -2, 2, -1, 1, 0},
			{-1, -1, 0, 0, 0, 0, 1, 1, 2},
			{-1, 1, -1, 1, -2, 2, -1, 1, 0},
			{1, 1, 0, 0, 0, 0, -1, -1, -2}
	};
	static int[][] scatterY = {
			{1, 1, 0, 0, 0, 0, -1, -1, -2},
			{-1, 1, 1, -1, 2, -2, -1, 1, 0},
			{-1, -1, 0, 0, 0, 0, 1, 1, 2},
			{-1, 1, -1, 1, -2, 2, 1, -1, 0}
	};
	static double[] amount = {0.01, 0.01, 0.07, 0.07, 0.02, 0.02, 0.1, 0.1, 0.05};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int input = Integer.parseInt(st.nextToken());
				map[i][j] = input;
			}
		}
		int x = n / 2, y = n / 2;
		// 왼쪽 시작, 2번씩 이동 같은방향으로 크기 만큼 이동
		int dir = 0, moveCnt = 0, check = 0;
		// 해당 방향으로 이동할 거리
		int forward = 1;
		while (true) {
			if (x == 0 && y == 0) break;
			int nx = x + move[dir][0], ny = y + move[dir][1];
			// 모래 이동하기
			map[nx][ny] += map[x][y];
			map[x][y] = 0;
			moveCnt++;
			scatterSand(nx, ny, dir);
			// 이동 완료 -> 회전
			if (moveCnt == forward) {
				moveCnt = 0;
				check++;
				dir = (dir + 1) % 4;
			}
			// 2번 이동이 끝남 -> 가야할 크기를 1증가
			if (check == maxMove) {
				forward++;
				check = 0;
			}
			x = nx;
			y = ny;
		}
		System.out.println(answer);
	}

	// 방향대로 모래 뿌리기
	private static void scatterSand(int nx, int ny, int dir) {
		int sandAmount = map[nx][ny];
		// 알파에 뿌려줄 남은 모래
		int remain = sandAmount;
		// 알파 위치
		int x = nx + move[dir][0], y = ny + move[dir][1];
		for (int i = 0; i <= 8; i++) {
			int cx = nx + scatterX[dir][i], cy = ny + scatterY[dir][i];
			int sand = (int) (sandAmount * amount[i]);
			checkMap(cx, cy, sand);
			remain -= sand;
		}
		// 알파 자리 넣어주기
		checkMap(x, y, remain);
		// y자리 이동 완료
		map[nx][ny] = 0;
	}
	
	static void checkMap(int x, int y, int sand) {
		// 격자 밖으로 나간 모래들
		if (x < 0 || n <= x || y < 0 || n <= y) {
			answer += sand;
		} else {
			map[x][y] += sand;
		}
	}
}
