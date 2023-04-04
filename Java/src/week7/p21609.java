package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class p21609 {

	static class Point implements Comparable<Point> {
		// 전체 리스트와 rainbow 변수
		List<Point> list, rainbow;
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public Point(int x, int y, List<Point> list, List<Point> rainbow) {
			this.x = x;
			this.y = y;
			this.list = list;
			this.rainbow = rainbow;
		}

		@Override
		public int compareTo(Point o) {
			if (this.list.size() == o.list.size()) {
				if (this.rainbow.size() == o.rainbow.size()) {
					if (this.x == o.x) {
						return o.y - this.y;
					}
					return o.x - this.x;
				}
				return o.rainbow.size() - this.rainbow.size();
			}
			return o.list.size() - this.list.size();
		}
	}

	static int n, m, answer;
	static int[][] map, copy;
	static boolean[][] visited;
	static PriorityQueue<Point> pq;
	static int[][] move = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		copy = new int[n][n];
		pq = new PriorityQueue<>();
		answer = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 블록이 있을 때 까지 반복
		while (true) {
			searchBlock();
			if (pq.isEmpty()) {
				break;
			}
			Point p = pq.poll();
			pq.clear();
			// 최소 조건 만족 X
			if (p.list.size() < 2) {
				break;
			}
			// 블록들 삭제
			deleteBlock(p);
			gravity();
			rotate();
			gravity();
		}
		System.out.println(answer);
	}

	private static void rotate() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				copy[n - 1 - j][i] = map[i][j];
			}
		}
		for (int i = 0; i < n; i++) {
			map[i] = copy[i].clone();
		}
	}

	private static void deleteBlock(Point p) {
		List<Point> blocks = p.list;
		int size = blocks.size();
		for (Point point : blocks) {
			map[point.x][point.y] = -2;
		}
		answer += size * size;
	}

	private static void gravity() {
		for (int col = 0; col < n; col++) {
			// 뒤에 부터 확인
			for (int j = n - 1; 0 <= j; j--) {
				// 빈칸 or 검은 돌
				if (map[j][col] == -2 || map[j][col] == -1) continue;
				int start = j;
				// 중력 작용
				for (int i = start; i < n - 1; i++) {
					int temp = map[i][col];
					// swap
					if (map[i + 1][col] == -2) {
						map[i][col] = map[i + 1][col];
						map[i + 1][col] = temp;
					} else {
						break;
					}
				}
			}
		}
	}

	private static void searchBlock() {
		visited = new boolean[n][n];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (0 < map[i][j] && !visited[i][j]) {
					int block = map[i][j];
					List<Point> blocks = new ArrayList<>();
					List<Point> rainbows = new ArrayList<>();
					Queue<Point> queue = new LinkedList<>();
					blocks.add(new Point(i, j));
					queue.add(new Point(i, j));
					visited[i][j] = true;
					
					while (!queue.isEmpty()) {
						Point p = queue.poll();
						for (int k = 0; k < 4; k++) {
							int nx = p.x + move[k][0], ny = p.y + move[k][1];
							if (nx < 0 || n <= nx || ny < 0 || n <= ny) continue;
							if (visited[nx][ny]) continue;
							if (map[nx][ny] == block || map[nx][ny] == 0) {
								if (map[nx][ny] == 0) {
									rainbows.add(new Point(nx, ny));
								}
								blocks.add(new Point(nx, ny));
								queue.offer(new Point(nx, ny));
								visited[nx][ny] = true;
							}
						}
					}
					// 생성된 하나의 블록 넣기
					pq.add(new Point(i, j, blocks, rainbows));
					// 무지개 블록들 visited 초기화
					for (Point p : rainbows) {
						visited[p.x][p.y] = false; 
					}
				}
			}
		}
	}
}
