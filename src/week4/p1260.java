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

public class p1260 {
		
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
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
		for (int i = 1; i <= n; i++) {
			Collections.sort(graph.get(i));
		}
		
		StringBuilder sb = new StringBuilder(v + " ");
		dfs(v, sb);
		System.out.println(sb.toString());
		Arrays.fill(visited, false);
		bfs(v);
		br.close();
	}

	private static void dfs(int start, StringBuilder sb) {
		visited[start] = true;
		for (int adj : graph.get(start)) {
			if (!visited[adj]) {
				visited[adj] = true;
				dfs(adj, sb.append(adj + " "));
			}
		}
	}

	private static void bfs(int start) {
		StringBuilder sb = new StringBuilder();
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			sb.append(curr + " ");
			for (int adj : graph.get(curr)) {
				if (!visited[adj]) {
					visited[adj] = true;
					queue.offer(adj);
				}
			}
		}
		System.out.println(sb.toString());
	}
}
