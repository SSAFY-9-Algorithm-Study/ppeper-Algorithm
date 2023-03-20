package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p17825 {

	static class Horse {
		int number, mapIndex, index;
		boolean isFinish = false;

		public Horse(int number, int mapIndex, int index) {
			this.number = number;
			this.mapIndex = mapIndex;
			this.index = index;
		}

	}

	static int[] order;
	static int answer = 0;
	static int[] pick;
	static Horse[] horse;
	static int[][] map = { 
			{ 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40 },
			{ 10, 13, 16, 19, 25, 30, 35, 40 },
			{ 20, 22, 24, 25, 30, 35, 40 }, 
			{ 30, 28, 27, 26, 25, 30, 35, 40 } };
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		order = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		pick = new int[10];
		horse = new Horse[4];
		perm(0);
		System.out.println(answer);
	}

	private static void initState() {
		for (int i = 0; i < 4; i++) {
			horse[i] = new Horse(0, 0, 0);
		}
		visited = new boolean[4][21];
	}

	private static void perm(int i) {
		if (10 <= i) {
			initState();
			answer = Math.max(answer, playGame());
			return;
		}
		for (int j = 0; j < 4; j++) {
			pick[i] = j;
			perm(i + 1);
		}
	}

	private static int playGame() {
		// 점수 누적
		int score = 0;
		for (int i = 0; i < order.length; i++) {
			int move = order[i];
			// 현재 이동하려는 말
			Horse h = horse[pick[i]];
			if (!h.isFinish) {
				int size = map[h.mapIndex].length;
				// 전에 있던 위치 false
				visited[h.mapIndex][h.index] = false;
				if (h.index + move < size) {
					// 이동
					h.index += move;
					// 지름길을 타고 있지 않을때 지름길인 10, 20, 30으로 도착한지 확인
					if (h.mapIndex == 0) {
						switch (map[h.mapIndex][h.index]) {
						case 10:
							h.index = 0;
							h.mapIndex = 1;
							break;
						case 20:
							h.index = 0;
							h.mapIndex = 2;
							break;
						case 30:
							h.index = 0;
							h.mapIndex = 3;
							break;
						}
					}
					// 해당 위치에 이미 말이 있으면 현재 방법으로 불가능
					int number = map[h.mapIndex][h.index];
					// 겹쳐지는 구간 확인 25, 30, 35, 40
					boolean check = false;
					switch (number) {
					case 25:
						if (visited[1][4] || visited[2][3] || visited[3][4]) {
							check = true;
						}
						break;
					case 30:
						if (h.index != 0) {
							if (visited[1][5] || visited[2][4] || visited[3][5]) {
								check = true;
							}
						}
						break;
					case 35:
						if (visited[1][6] || visited[2][5] || visited[3][6]) {
							check = true;
						}
						break;
					case 40:
						if (visited[1][7] || visited[2][6] || visited[3][7] || visited[0][20]) {
							check = true;
						}
						break;
					}
					// 방문된 곳이면 해당 길은 불가능
					if (check || visited[h.mapIndex][h.index]) {
						score = 0;
						break;
					} else {
						visited[h.mapIndex][h.index] = true;
						score += number;
					}
				} else {
					h.isFinish = true;
				}
			}
		}
		return score;
	}
}
