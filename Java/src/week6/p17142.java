package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p17142 {

	static class Virus {
		int x, y;

		public Virus(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int[][] map;
	static int[][] move = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static ArrayList<Virus> virus;
	static Virus[] pick;
	static int answer = Integer.MAX_VALUE;
	static int n, m;
	static int safeZone = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		virus = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input == 2) {
					virus.add(new Virus(i, j));
				}
				if (input == 0) {
					safeZone++;
				}
				map[i][j] = input;
			}
		}
		if (safeZone == 0) {
			System.out.println(0);
		} else {
			pick = new Virus[m];
			perm(0, 0);
			if (answer == Integer.MAX_VALUE) {
				System.out.println(-1);
			} else {
				System.out.println(answer);
			}
		}
	}

	private static void perm(int i, int count) {
		if (count == m) {
			spread();
			return;
		}
		for (int start = i; start < virus.size(); start++) {
			pick[count] = virus.get(start);
			perm(start + 1, count + 1);
		}
	}

	private static void spread() {
		int count = safeZone;
		boolean[][] visited = new boolean[n][n];
		Queue<Virus> queue = new LinkedList<Virus>();
		for (Virus virus : pick) {
			queue.offer(virus);
			visited[virus.x][virus.y] = true;
		}
		int time = 0;
		while (!queue.isEmpty()) {
			time++;
			int size = queue.size();
			// 한번씩 바이러스들이 퍼진다.
			for (int k = 0; k < size; k++) {
				Virus v = queue.poll();
				visited[v.x][v.y] = true;
				for (int i = 0; i < 4; i++) {
					int nx = v.x + move[i][0], ny = v.y + move[i][1];
					if (nx < 0 || n <= nx || ny < 0 || n <= ny) continue;
					if (visited[nx][ny]) continue;
					if (map[nx][ny] != 1) {
						if (map[nx][ny] == 0) {
							count--;
						}
						visited[nx][ny] = true;
						queue.offer(new Virus(nx, ny));
						// 모두 확산되면 종료
						if (count == 0) {
							answer = Math.min(answer, time);
							return;
						}
					}
				}
			}
		}
	}
}
