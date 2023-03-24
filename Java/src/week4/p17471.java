package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p17471 {

	static int diff = Integer.MAX_VALUE;
	static int[] population;
	static ArrayList<HashSet<Integer>> graph;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		graph = new ArrayList<HashSet<Integer>>();
		population = new int[n + 1];
		visited = new boolean[n + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 지역의 인구수
		for (int i = 1; i <= n; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		// 그래프
		for (int i = 0; i <= n; i++) {
			graph.add(new HashSet<Integer>());
		}
		// 연관 관계
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			for (int j = 0; j < m; j++) {
				int to = Integer.parseInt(st.nextToken());
				graph.get(i).add(to);
				graph.get(to).add(i);
			}
		}
		// 지역 나누기
		for (int divide = 1; divide < n; divide++) {
			devideArea(1, divide);
		}

		if (diff == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(diff);
		}
	}

	private static void devideArea(int start, int divide) {
		if (divide == 0) {
			if (checkAreaIsValid()) {
				diff = Math.min(diff, calculate());
			}
			return;
		}
		for (int i = start; i < population.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				devideArea(i + 1, divide - 1);
				visited[i] = false;
			}
		}
	}

	private static boolean checkAreaIsValid() {
		boolean flag = true;
		boolean[] checked = new boolean[visited.length];
		int a = 0;
		int b = 0;
		for (int i = 0; i < visited.length; i++) {
			if (visited[i]) {
				a = i;
			}
			if (!visited[i]) {
				b = i;
			}
		}
		Queue<Integer> queue = new LinkedList<>();
		queue.add(a);
		checked[a] = true;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			for (Integer adj : graph.get(curr)) {
				if (visited[adj] && !checked[adj]) {
					checked[adj] = true;
					queue.offer(adj);
				}
			}
		}
		queue.clear();
		checked[b] = true;
		queue.offer(b);
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			for (Integer adj : graph.get(curr)) {
				if (!visited[adj] && !checked[adj]) {
					checked[adj] = true;
					queue.offer(adj);
				}
			}
		}
		for (int i = 1; i < checked.length; i++) {
			if (!checked[i]) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	private static int calculate() {
		int a = 0;
		int b = 0;
		for (int i = 1; i < population.length; i++) {
			if (visited[i]) {
				a += population[i];
			} else {
				b += population[i];
			}
		}
		return Math.abs(a - b);
	}
}