package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class 플레이페어암호 {
	static char[][] board;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new char[5][5];
		sb = new StringBuilder();
		char[] input = br.readLine().toCharArray();
		HashSet<Character> set = new HashSet<Character>();
		char[] alphabets = br.readLine().toCharArray();
		int row = 0, col = 0;
		for (char alpha: alphabets) {
			if (set.contains(alpha)) continue;
			if (col == 5) {
				row++;
				col = 0;
			}
			set.add(alpha);
			board[row][col] = alpha;
			col++;
		}
		// 남은 숫자 넣어주기
		for (char c = 'A'; c <= 'Z'; c++) {
			if (c == 'J') continue;
			if (set.contains(c)) continue;
			if (col == 5) {
				row++;
				col = 0;
			}
			set.add(c);
			board[row][col] = c;
			col++;
		}
		// 2글자씩 올바르게 나누기
		// 연속된 알파벳이면 뒤에 'X'
		// 짝이 안맞기 때문에 마지막은 뒤에 'X'
		int idx = 0;
		ArrayList<Character> list = new ArrayList();
		while (idx < input.length) {
			if (idx + 1 < input.length) {
				// 연속된 알파벳이면 뒤에 'X'
				if (input[idx] == input[idx + 1]) {
					list.add(input[idx]);
					// 만약 XX면 뒤에 'Q'
					if (input[idx] == 'X') {
						list.add('Q');
					} else {
						list.add('X');						
					}
					idx += 1;
				} else {
					list.add(input[idx]);
					list.add(input[idx + 1]);
					idx += 2;
				}
			} else {
				// 하나가 남음
				list.add(input[idx]);
				list.add('X');
				break;
			}
		}
		for (int i = 0; i < list.size() - 1; i+=2) {
			char front = list.get(i);
			char back = list.get(i + 1);
			encrption(front, back);
		}
		System.out.println(sb);
	}

	private static void encrption(char front, char back) {
		int[] frontIdx = find(front);
		int[] backIdx = find(back);
		// 1. 행이 같을때
		if (frontIdx[0] == backIdx[0]) {
			sb.append(board[frontIdx[0]][(frontIdx[1] + 1) % 5]);
			sb.append(board[backIdx[0]][(backIdx[1] + 1) % 5]);
		} else if (frontIdx[1] == backIdx[1]) {
			// 2. 열이 같을때
			sb.append(board[(frontIdx[0] + 1) % 5][frontIdx[1]]);
			sb.append(board[(backIdx[0] + 1) % 5][backIdx[1]]);
		} else {
			sb.append(board[frontIdx[0]][backIdx[1]]);
			sb.append(board[backIdx[0]][frontIdx[1]]);
		}
	}

	private static int[] find(char alpha) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == alpha) {
					return new int[] {i, j};
				}
			}
		}
		return null;
	}

}
