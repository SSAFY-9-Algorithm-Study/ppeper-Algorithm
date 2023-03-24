package week6;

class Solution {

	private static int[] SCORE = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };

	public int[] solution(int n, int[] info) {
		int[] answer = new int[11];
		int[] temp = new int[11];
		int diff = 0;
		// 비트마스킹으로 1이면 라이언이 이긴것, 0이면 진것
		for (int subset = 1; subset < (1 << 10); subset++) {
			int ryan = 0, apeach = 0, count = 0;
			for (int i = 0; i < 10; i++) {
				// 라이언이 이김
				if ((subset & (1 << i)) != 0) {
					ryan += SCORE[i];
					temp[i] = info[i] + 1;
					count += temp[i];
				} else {
					if (0 < info[i]) {
						apeach += SCORE[i];
					}
					temp[i] = 0;
				}
			}
			if (n < count || ryan - apeach == 0) continue;

			// 남은 화살의 개수
			temp[10] = n - count; 
			if (diff < ryan - apeach) {
				diff = ryan - apeach;
				answer = temp.clone();
			} else if (diff == ryan - apeach) {
				// 작은 개수에 따라 업데이트
				for (int j = 10; 0 <= j; j--) {
					if (temp[j] < answer[j]) break;
					if (answer[j] < temp[j]) {
						answer = temp.clone();
						break;
					}
				}
			}
		}
		if (diff == 0) {
			return new int[] { -1 };
		}
		return answer;
	}
}