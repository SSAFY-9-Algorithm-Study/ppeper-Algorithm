package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2606 {
		
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		graph = new ArrayList<ArrayList<Integer>>();
		visited = new boolean[n + 1];
		
		// init graph
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);
			graph.get(to).add(from);
		}
		System.out.println(bfs(1));
		br.close();
	}

	private static int bfs(int start) {
		StringBuilder sb = new StringBuilder();
		Queue<Integer> queue = new LinkedList<>();
		ArrayList<Integer> list = new ArrayList<>();
		queue.offer(start);
		visited[start] = true;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			for (int adj : graph.get(curr)) {
				if (!visited[adj]) {
					visited[adj] = true;
					queue.offer(adj);
					list.add(adj);
				}
			}
		}
		return list.size();
	}
}
