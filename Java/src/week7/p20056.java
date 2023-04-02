package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class p20056 {
	
	static class FireBall {
		int x, y, m, s, d;

		public FireBall(int x, int y, int m, int s, int d) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	
	static int[][] move = {
		{-1, 0},
		{-1, 1},
		{0, 1},
		{1, 1},
		{1, 0},
		{1, -1},
		{0, -1},
		{-1, -1},
	};
	
	static int n;
	static LinkedList<FireBall>[][] map;
	static List<FireBall> fireBall;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		map = new LinkedList[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = new LinkedList<>();
			}
		}
		fireBall = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int mm = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fireBall.add(new FireBall(x - 1, y - 1, mm, s, d));
		}
		// k번 이동
		while (0 < k--) {
			moveFireBall();
			// 겹치는 파이어볼 나누어지기
			checkFireBall();
		}
		System.out.println(calculateFireBall());
	}

	private static void checkFireBall() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (1 < map[i][j].size()) {
					splitFireBall(i, j);
				} else {
					map[i][j].clear();
				}
			}
		}
	}

	private static int calculateFireBall() {
		int weight = 0;
		for (FireBall fb : fireBall) {
			weight += fb.m;
		}
		return weight;
	}

	private static void splitFireBall(int i, int j) {
		LinkedList<FireBall> fireBallList = map[i][j];
		int size = fireBallList.size();
		boolean odd = true;
		boolean even = true;
		int sumM = 0;
		int sumS = 0;
		while (!fireBallList.isEmpty()) {
			FireBall curr = fireBallList.poll();
			sumM += curr.m;
			sumS += curr.s;
			if (curr.d % 2 == 0) {
				odd = false;
			} else {
				even = false;
			}
			fireBall.remove(curr);
		}
		if (sumM / 5 == 0) {
			return;
		} else {
			// 짝수 홀수로 나누기
			if (odd || even) {
				for (int dir = 0; dir < 8; dir+=2) {
					fireBall.add(new FireBall(i, j, sumM / 5, sumS / size, dir));
				}
			} else {
				for (int dir = 1; dir < 8; dir+=2) {
					fireBall.add(new FireBall(i, j, sumM / 5, sumS / size, dir));
				}
			}
		}
	}

	private static void moveFireBall() {
		for (FireBall fb : fireBall) {
			fb.x = (fb.x + n + move[fb.d][0] * (fb.s % n)) % n;
			fb.y = (fb.y + n + move[fb.d][1] * (fb.s % n)) % n;
			map[fb.x][fb.y].add(fb);
		}
	}
}
