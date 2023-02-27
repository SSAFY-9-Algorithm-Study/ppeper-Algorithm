package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p9663 {
	
	static int n;
	static int[] chess;
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		chess = new int[n];
		chessGame(0);
		System.out.println(answer);
	}

	private static void chessGame(int row) {
		if (row == n) {
			// n-queen 만족
			answer++;
			return;
		}
		// 두려는 위치
		for (int pos = 1; pos <= n; pos++) {
			if (valid(row, pos)) {
				chess[row] = pos;
				chessGame(row + 1);
			}
			
		}
	}
	
	private static boolean valid(int row, int pos) {
		// 두려는 row까지 validate 확인
		for (int i = 0; i < row; i++) {
			// 같은행, 대각선은 불가능
			if (chess[i] == pos || row - i == Math.abs(chess[i] - pos)) {
				return false;
			}
		}
		return true;
	}
}