package week50;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node> {
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(@NotNull Node o) {
        if (this.y == o.y) return o.x - this.x;
        return this.y - o.y;
    }
}
public class 점수획득 {

    static int[][] board;
    static StringBuilder answer;
    static boolean[][] visited;
    static int[] spareBlock;
    static int K, M, index;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static PriorityQueue<Node> deleteBlock;

    public static void main(String[] args) throws IOException {
        initValue();
        runApp();
    }

    private static void runApp() {
        for (int i = 0; i < K; i++) {
            int[] result = getMaxRotateBlock();
            if (result[0] == -1) break;
            // 가장 큰 점수를 얻을 수 있게 보드 회전
            board = rotate90(result[0], result[1], result[2]);
            searchBoard(board);
            int score = deleteBlock.size();
            addSpareBlock();
            int extraScore = checkExtraPoint();
            score += extraScore;
            answer.append(score).append(" ");
        }
        System.out.println(answer.toString());
    }

    private static int checkExtraPoint() {
        int score = 0;
        while (true) {
            searchBoard(board);
            if (deleteBlock.size() < 3) break;
            score += deleteBlock.size();
            addSpareBlock();
        }
        return score;
    }

    private static void addSpareBlock() {
        while (!deleteBlock.isEmpty()) {
            Node n = deleteBlock.poll();
            board[n.x][n.y] = spareBlock[index];
            index++;
        }
    }

    // 가장 많은 점수를 얻을 수 있는 시작좌표와 회전률
    private static int[] getMaxRotateBlock() {
        int maxScore = 0;
        int rx = -1, ry = -1, rotate = -1;
        for (int r = 1; r <= 3; r++) {
            for (int i = 0; i <= 2; i++) {
                for (int j = 0; j <= 2; j++) {
                    int[][] rotateBoard = rotate90(j, i, r);
                    searchBoard(rotateBoard); // deleteBlock이 업데이트 됨
                    if (maxScore < deleteBlock.size()) {
                        rx = j;
                        ry = i;
                        rotate = r;
                        maxScore = deleteBlock.size();
                    }
                }
            }
        }
        return new int[] {rx, ry, rotate};
    }

    private static void searchBoard(int[][] board) {
        deleteBlock.clear();
        for (int i = 0; i < 5; i++) {
            Arrays.fill(visited[i], false);
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (visited[i][j]) continue;
                getNearBlock(i, j, board);
            }
        }
    }

    private static void getNearBlock(int i, int j, int[][] board) {
        int score = board[i][j];
        List<Node> list = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(i, j));
        visited[i][j] = true;
        while (!q.isEmpty()) {
            Node curr = q.poll();
            list.add(curr);
            for (int k = 0; k < 4; k++) {
                int nx = curr.x + dx[k], ny = curr.y + dy[k];
                if (nx < 0 || 5 <= nx || ny < 0 || 5 <= ny) continue;
                if (visited[nx][ny]) continue;
                if (board[nx][ny] != score) continue;
                visited[nx][ny] = true;
                q.add(new Node(nx, ny));
            }
        }
        // 3개 이상이 인접할때만 점수 획득
        if (3 <= list.size()) {
            deleteBlock.addAll(list);
        }
    }

    private static int[][] rotate90(int x, int y, int rotate) {
        int[][] buffer = cloneBoard();
        int[][] temp = cloneBoard();
        while (0 < rotate) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buffer[j + x][2 - i + y] = temp[i + x][j + y];
                }
            }
            for (int i = 0; i < 5; i++) {
                temp[i] = buffer[i].clone();
            }
            rotate--;
        }
        return buffer;
    }
    
    private static int[][] cloneBoard() {
        int[][] buffer = new int[5][5];
        for (int i = 0; i < 5; i++) {
            buffer[i] = board[i].clone();
        }
        return buffer;
    } 

    private static void initValue() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        deleteBlock = new PriorityQueue<>();
        board = new int[5][5];
        visited = new boolean[5][5];
        K = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        spareBlock = new int[M];
        answer = new StringBuilder();
        index = 0;
        for (int i = 0; i < 5; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        spareBlock = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static int parseInt(String input) {
        return Integer.parseInt(input);
    }
}
