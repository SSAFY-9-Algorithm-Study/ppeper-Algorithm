package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p20055 {

	static int[] belt;
	static boolean[] robot;
	static int n, k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		belt = new int[n * 2];
		robot = new boolean[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * n; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		int level = 0;
		// 벨트 움직이기 시작
		while (checkBelt()) {
			// 앞으로 한칸 회전
			level++;
			moveForward();
		}
		System.out.println(level);
	}

	private static void moveForward() {
		// 벨트 한칸 회전
		int temp = belt[belt.length - 1];
		for (int i = belt.length - 1; 0 < i; i--) {
			belt[i] = belt[i - 1];
		}
		belt[0] = temp;
		// 로봇의 위치도 변경하기
		boolean temp1 = robot[n - 1];
		for (int i = n - 1; 0 < i; i--) {
			robot[i] = robot[i - 1];
		}
		robot[0] = temp1;
		robot[n - 1] = false;
		// 로봇이 자동으로 이동
		// 전진하기 위해서는 앞에 로봇 X + 내구도가 1이상 남아있어야함
		for (int i = n - 1; 0 < i; i--) {
			if (!robot[i] && 1 <= belt[i] && robot[i - 1]) {
				robot[i - 1] = false;
				robot[i] = true;
				belt[i]--;
			}
		}
		robot[n - 1] = false;
		// 로봇 올리기
		if (1 <= belt[0]) {
			belt[0]--;
			robot[0] = true;
		}
	}

	private static boolean checkBelt() {
		int count = 0;
		for (int durability : belt) {
			// 내구도가 0인 칸의 개수
			if (durability <= 0) {
				count++;
			}
		}
		return count < k;
	}
}
