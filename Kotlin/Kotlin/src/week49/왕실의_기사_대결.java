package week49;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제 파악
 * 1. 말들이 이동을 함 -> 다른 말을 만나면 밀림
 * 	1.1 -> 벽을 만나면 밀리지 않음 (이동 불가)
 * 2. 밀리게 되면 말들에 trap이 있는 개수만큼 데미지를 입음
 * 	2.2 -> 데미지를 k 이상 받은 말들은 사라진다.
 * 3. 최종적으로 받은 데이미지를 구하라
 */
class Knight {
    int number, r, c, h, w, k, damage = 0;

    public Knight(int number, int r, int c, int h, int w, int k) {
        super();
        this.number = number;
        this.r = r;
        this.c = c;
        this.h = h;
        this.w = w;
        this.k = k;
    }
}

public class 왕실의_기사_대결 {

    static int L, N, Q;
    static Set<Integer> moveKnightNumber;
    static int[][] board;
    static boolean[] visited;
    static int[][] op;
    static Knight[] knights;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        board = new int[L][L];
        visited = new boolean[N + 1];
        knights = new Knight[N + 1];
        moveKnightNumber = new HashSet<Integer>();
        op = new int[Q][2];
        for (int i = 0; i < L; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for (int number = 1; number <= N; number++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            Knight knight = new Knight(number, r, c, h, w, k);
            knights[number] = knight;

        }
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            op[i][0] = Integer.parseInt(st.nextToken());
            op[i][1] = Integer.parseInt(st.nextToken());
        }
        runGame();
        int sum = 0;
        for (int i = 1; i < knights.length; i++) {
            if (knights[i] != null) {
                sum += knights[i].damage;
            }
        }
        System.out.println(sum);

    }

    // 나이트들 이동
    private static void runGame() {
        for (int[] oper : op) {
            Arrays.fill(visited, false);
            moveKnightNumber.clear();
            if (knights[oper[0]] != null) {
                moveKnightNumber.add(oper[0]);
                if (canMoveKnight(oper[0], oper[1])) {
                    moveKnight(oper[0], oper[1]);
                }
            }
        }
    }

    private static void moveKnight(int orderKnight, int dir) {
        for (int num : moveKnightNumber) {
            Knight knight = knights[num];

            knight.r += dx[dir];
            knight.c += dy[dir];
            if (orderKnight == num) continue;
            calculateDamage(knight);
        }

    }

    private static boolean canMoveKnight(int num, int dir) {
        Knight knight = knights[num];
        int r = knight.r;
        int c = knight.c;
        int h = knight.h;
        int w = knight.w;

        int nx = r + dx[dir];
        int ny = c + dy[dir];
        visited[num] = true;
        // 범위에서 벗어나면 종료
        if (nx < 0 || board.length <= nx || ny < 0 || board.length <= ny) return false;
        if (board.length < nx + h || board.length < ny + w) return false;
        if (isWall(nx, ny, h, w)) return false;
        for (int i = 1; i < knights.length; i++) {
            if (visited[i]) continue;
            if (knights[i] == null) continue;
            // 다른 말을 만날때
            if (meetKnight(nx, ny, h, w, knights[i])) {
                // 연쇄로 만나는 말이 이동가능한지 확인
                if (canMoveKnight(i, dir) == false) {
                    return false;
                }
            }
        }
        moveKnightNumber.add(num);
        return true;

    }

    private static boolean isWall(int r, int c, int h, int w) {
        for (int i = r; i < r + h; i++) {
            for (int j = c; j < c + w; j++) {
                if (board[i][j] == 2) return true;
            }
        }
        return false;
    }

    private static boolean meetKnight(int r, int c, int h, int w, Knight curr) {
        for (int i = r; i < r + h; i++) {
            for (int j = c; j < c + w; j++) {
                for (int a = curr.r; a < curr.r + curr.h; a++) {
                    for (int b = curr.c; b < curr.c + curr.w; b++) {
                        if (i == a && j == b) return true;
                    }
                }
            }
        }
        return false;
    }

    // 데미지 받는 양
    private static void calculateDamage(Knight knight) {
        int number = knight.number;
        int r = knight.r;
        int c = knight.c;
        int h = knight.h;
        int w = knight.w;
        int traps = 0;
        for (int i = r; i < r + h; i++) {
            for (int j = c; j < c + w; j++) {
                if (board[i][j] == 1) traps++;
            }
        }
        knight.damage += traps;
        knight.k -= traps;
        // knight가 소멸여부 확인
        if (knight.k <= 0) {
            knights[number] = null;
        }

    }
}

