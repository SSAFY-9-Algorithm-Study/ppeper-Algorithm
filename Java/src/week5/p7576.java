package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p7576 {
	
	static class Tomato {
		int x, y;

		public Tomato(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int[][] map;
	static Queue<Tomato> list;
	static int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int n, m, tomato = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		list = new LinkedList<Tomato>();
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int input = Integer.parseInt(st.nextToken());
				map[i][j] = input;
				if (input == 0) {
					tomato += 1;
				}
				if (input == 1) {
					list.add(new Tomato(i, j));
				}
			}
		}
		int day = 0;
		while (!list.isEmpty() && 0 < tomato) {
			int size = list.size();
			while (0 < size--) {
				Tomato t = list.poll();
				for (int i = 0; i < 4; i++) {
					int nx = t.x + move[i][0], ny = t.y + move[i][1];
					if (nx < 0 || n <= nx || ny < 0 || m <= ny) continue;
					if (map[nx][ny] == 0) {
						map[nx][ny] = 1;
						list.offer(new Tomato(nx,  ny));
						tomato--;
					}
					
				}
			}
			day++;
		}
		if (tomato == 0) {
			System.out.println(day);			
		} else {
			System.out.println(-1);
		}
	}
}
