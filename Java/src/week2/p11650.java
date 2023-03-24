package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Point implements Comparable<Point> {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point o) {
		// x가 같다면
		if (this.x == o.x) {
			return this.y - o.y;
		}
		// x는 증가순
		return this.x - o.x;
	}
}

public class p11650 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Point[] points = new Point[n];
		for (int i = 0; i < points.length; i++) {
			int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			points[i] = new Point(input[0], input[1]);
		}
		Arrays.sort(points);
		for (Point point : points) {
			System.out.print(point.x + " " + point.y + "\n");
		}
		br.close();
	}
}