package week50;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class User {
    int number, x, y;
    boolean isOut;

    public User(int number, int x, int y) {
        super();
        this.number = number;
        this.x = x;
        this.y = y;
        isOut = false;
    }
}

class Pair implements Comparable<Pair> {
    int x, y, distance;
    boolean isOut;

    public Pair(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        isOut = false;
    }

    public Pair(int x, int y, int distance) {
        super();
        this.x = x;
        this.y = y;
        this.distance = distance;
        isOut = false;
    }

    @Override
    public int compareTo(Pair o) {
        if (this.distance == o.distance) {
            // 행이 작은순 -> 열이 작은순
            if (this.x == o.x) return this.y - o.y;
            return this.x - o.x;
        }
        return this.distance - o.distance;
    }
}

public class 코드트리_빵 {

    static int N, M, remain;
    // 상좌우하
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int[][] board;
    static boolean[][] isLocked;
    static Pair[] stores;
    static List<User> userInBoard;
    static Queue<Pair> completeStore;

    public static void main(String[] args) throws IOException {
        init();
        runApplication();
    }

    private static void runApplication() {
        int time = 1;
        while (0 < remain) {
            // 격자에 사람이 있으면 이동 시작 (1)
            for (User user: userInBoard) {
                if (user.isOut) continue;
                moveToStore(user);
            }
            while (!completeStore.isEmpty()) {
                Pair p = completeStore.poll();
                isLocked[p.x][p.y] = true;
            }
            // 현재 시간이 t분이면 t번사람 이동
            if (time <= M) {
                goToBaseCamp(time);
            }
            if (remain == 0) break;
            time++;
        }
        System.out.println(time);
    }

    private static void goToBaseCamp(int number) {
        Pair s = stores[number];
        // 편의점에서 가장 가까이 있는 베이스 캠프에 들어간다.
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new Pair(s.x, s.y, 0));
        visited[s.x][s.y] = true;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        while (!q.isEmpty()) {
            Pair curr = q.poll();

            if (board[curr.x][curr.y] == 1) {
                pq.add(curr);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (outBoard(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (isLocked[nx][ny]) continue;
                q.add(new Pair(nx, ny, curr.distance + 1));
                visited[nx][ny] = true;
            }
        }
        Pair target = pq.poll();
        // 유저를 격자로 넣어준다
        userInBoard.add(new User(number, target.x, target.y));
        // 이동한 베이스캠프는 이제 이동 불가능
        isLocked[target.x][target.y] = true;
    }

    private static void moveToStore(User user) {
        // 가고 싶은 방향으로 1칸 이동 -> 최단거리로 움직임
        Pair s = stores[user.number];
        // 현재 편의점까지의 거리
        int currDistance = getDistance(user.x, user.y, s.x, s.y);
        for (int i = 0; i < 4; i++) {
            int nx = user.x + dx[i], ny = user.y + dy[i];
            if (outBoard(nx, ny)) continue;
            if (isLocked[nx][ny]) continue;
            int dist = getDistance(nx, ny, s.x, s.y);
            // 더 가까워지는 방향으로 1칸 이동
            if (dist <= currDistance) {
                // 행이 가장 작아야함
                user.x = nx;
                user.y = ny;
                break;
            }
        }
        // (2) 만약 편의점에 도착한다면 해당 편의점에서 멈춤
        if (user.x == s.x && user.y == s.y) {
            completeStore.add(new Pair(user.x, user.y));
            user.isOut = true;
            remain -= 1;
        }
    }

    private static int getDistance(int x, int y, int targetX, int targetY) {
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new Pair(x, y, 0));
        visited[x][y]= true;
        while (!q.isEmpty()) {
            Pair curr = q.poll();

            if (curr.x == targetX && curr.y == targetY) {
                return curr.distance;
            }

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (outBoard(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (isLocked[nx][ny]) continue;
                q.add(new Pair(nx, ny, curr.distance + 1));
                visited[nx][ny] = true;
            }
        }
        return Integer.MAX_VALUE;
    }

    private static boolean outBoard(int x, int y) {
        return (x < 0 || N <= x || y < 0 || N <= y);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        remain = M; // M명이 편의점에 도착해야함
        board = new int[N][N];
        isLocked = new boolean[N][N];
        stores = new Pair[M + 1];
        userInBoard = new ArrayList<>();
        completeStore = new LinkedList<Pair>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = parseInt(st.nextToken());
            }
        }
        // 편의점 위치
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = parseInt(st.nextToken()) - 1;
            int y = parseInt(st.nextToken()) - 1;
            stores[i] = new Pair(x, y);
        }
    }

    private static int parseInt(String input) {
        return Integer.parseInt(input);
    }
}
