package week49;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {

    int x, y, dir, move;

    public Node(int x, int y, int dir, int move) {

        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.move = move;
    }
}

public class 보이저_1호 {

    static int N, M, R, C;
    static char[] direction = {'U', 'R', 'D', 'L'};
    // 위, 오른쪽, 아래, 왼
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static char[][] board;
    static boolean[][][] visited;
    static char answerDir;
    static int time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()) - 1;
        C = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < 4; i++) {
            visited = new boolean[N][M][4];
            runSignal(i);
        }
        System.out.println(answerDir);
        if (time == Integer.MAX_VALUE) System.out.println("Voyager"); else System.out.println(time);
    }

    private static void runSignal(int dir) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(R, C, dir, 0));
        visited[R][C][dir] = true;
        int currDir = dir;
        while (!q.isEmpty()) {
            Node node = q.poll();
            int nx = node.x + dx[currDir];
            int ny = node.y + dy[currDir];
            if (nx < 0 || N <= nx || ny < 0 || M <= ny) {
                if (time < node.move + 1) {
                    time = node.move + 1;
                    // 현재 시작한 방향 저장
                    answerDir = direction[dir];
                }
                continue;
            }
            // 다시 돌아서 같은 방향으로 이동 -> 무한궤도
            if (visited[nx][ny][currDir]) {
                if (time < Integer.MAX_VALUE) {
                    time = Integer.MAX_VALUE;
                    // 현재 시작한 방향 저장
                    answerDir = direction[dir];
                }
                continue;
            }
            if (board[nx][ny] == 'C') {
                if (time < node.move + 1) {
                    time = node.move + 1;
                    // 현재 시작한 방향 저장
                    answerDir = direction[dir];
                }
                break;
            }
            if (board[nx][ny] == '/') {
                if (currDir == 0) currDir = 1;
                else if (currDir == 1) currDir = 0;
                else if (currDir == 2) currDir = 3;
                else currDir = 2;
            } else if (board[nx][ny] == '\\') {
                if (currDir == 0) currDir = 3;
                else if (currDir == 3) currDir = 0;
                else if (currDir == 1) currDir = 2;
                else currDir = 1;
            }
            q.add(new Node(nx, ny, currDir, node.move + 1));
        }
    }
}
