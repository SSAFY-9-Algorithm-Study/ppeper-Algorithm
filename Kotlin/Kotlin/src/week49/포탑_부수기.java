package week49;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }
}

class Tower implements Comparable<Tower> {
    int x, y, power;
    boolean isAttack = false;
    int attackTime = 0;

    public Tower(int x, int y, int power) {
        super();
        this.x = x;
        this.y = y;
        this.power = power;
    }

    @Override
    public int compareTo(Tower o) {
        // 공격력이 가장 낮은 포탑 -> 가장 최근에 공격한 포탑 -> 행 + 열 큰 값 -> 열이 큰 값
        if (this.power == o.power) {
            if (this.attackTime == o.attackTime) {
                if (this.x + this.y == o.x + o.y) {
                    return o.y - this.y;
                }
                return (o.x + o.y) - (this.x + this.y);
            }
            return o.attackTime - this.attackTime;
        }
        return this.power - o.power;
    }
}

public class 포탑_부수기 {

    static int N, M, K, handicap, remainTower;
    static int[][] board;
    static boolean[][] visited;
    static TreeSet<Tower> towers;
    static Tower[][] towerMap;
    // 우하좌상, 대각선
    static int[] dx = {0, 1, 0, -1, -1, 1, 1, -1};
    static int[] dy = {1, 0, -1, 0, 1, 1, -1, -1};

    public static void main(String[] args) throws IOException {
        init();
        runApplication();
    }

    private static void runApplication() {
        for (int i = 1; i <= K; i++) {
            if (remainTower == 1) break;
            pickAttacker(i);
        }
        addTower();
        System.out.println(towers.pollLast().power);
    }

    private static void addTower() {
        towers.clear();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (towerMap[i][j] == null) continue;
                if (board[i][j] == 0) continue;
                towers.add(towerMap[i][j]);
            }
        }
    }

    private static void pickAttacker(int time) {
        addTower();
        // 우선순위에 따라 [가장 약한 포탑] 공격자 선정
        Tower attacker = towers.pollFirst();
        Tower t1 = towerMap[attacker.x][attacker.y];
        // 핸디캡 적용, 공격한 시간 저장
        t1.power += handicap;
        t1.isAttack = true;
        t1.attackTime = time;
        board[attacker.x][attacker.y] += handicap;
        // 공격자의 공격 대상
        Tower target = towers.pollLast();
        Tower t2 = towerMap[target.x][target.y];

        // 1. 레이저 공격 먼저 시도
        if (!lazerAttack(attacker, target)) {
            // 2. 포탄 공격
            bombAttack(attacker, target);
        }
        // 공격 대상 공격 후 남은 공격력
        board[t2.x][t2.y] = t2.power -= t1.power;
        t2.isAttack = true;
        if (board[t2.x][t2.y] <= 0) {
            board[t2.x][t2.y] = 0;
            towerMap[t2.x][t2.y] = null;
            remainTower -= 1;
        }

        // 공격 이후에 남은 포탑이 1개면 그 즉시 종료
        if (remainTower == 1) return;
        // 바뀐 타워들을 업데이트 해줌
        updateTower();
    }

    private static boolean lazerAttack(Tower attacker, Tower target) {
        int halfDamage = attacker.power / 2;
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }

        Queue<Pair> q = new LinkedList<>();
        Pair[][] buffer = new Pair[N][M]; // 경로 저장
        q.add(new Pair(attacker.x, attacker.y));
        while (!q.isEmpty()) {
            Pair curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = (curr.x + N + dx[i]) % N, ny = (curr.y + M + dy[i]) % M;
                if (visited[nx][ny]) continue;
                if (board[nx][ny] == 0) continue;
                buffer[nx][ny] = new Pair(curr.x, curr.y);
                visited[nx][ny] = true;
                q.add(new Pair(nx, ny));
            }
        }

        // 경로 도착 불가능
        if (!visited[target.x][target.y]) return false;

        // 레이저 공격
        int x = target.x, y = target.y;
        while (x != attacker.x || y != attacker.y) {
            Pair p = buffer[x][y];
            if (x == target.x && y == target.y) {
                x = p.x;
                y = p.y;
                continue;
            }
            board[x][y] = towerMap[x][y].power -= halfDamage;
            towerMap[x][y].isAttack = true;
            if (board[x][y] <= 0) {
                board[x][y] = 0;
                towerMap[x][y] = null;
                remainTower -= 1;
            }
            x = p.x;
            y = p.y;
        }

        return true;
    }

    // 포탄 공격
    private static void bombAttack(Tower attacker, Tower target) {
        // 범위에 있는 포탑들은 절반만 공격 당함
        int halfDamage = attacker.power / 2;

        // 주변 8방향도 공격 당함
        for (int i = 0; i < 8; i++) {
            int nx = (target.x + dx[i] + N) % N, ny = (target.y + dy[i] + M) % M;
            if (board[nx][ny] == 0) continue;
            // 공격자는 대상에서 제외
            if (towerMap[nx][ny].isAttack) continue;

            // 대상 공격 후 남은 공격력
            board[nx][ny] = towerMap[nx][ny].power -= halfDamage;
            towerMap[nx][ny].isAttack = true;
            if (board[nx][ny] <= 0) {
                towerMap[nx][ny] = null;
                board[nx][ny] = 0;
                remainTower -= 1;
            }
        }
    }

    private static void updateTower() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (towerMap[i][j] == null) continue;
                if (!towerMap[i][j].isAttack) {
                    towerMap[i][j].power += 1;
                    board[i][j] += 1;
                }
                towerMap[i][j].isAttack = false;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        handicap = N + M;
        K = parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];
        towers = new TreeSet<Tower>();
        towerMap = new Tower[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = parseInt(st.nextToken());
                // 타워 초기화
                if (board[i][j] != 0) {
                    towerMap[i][j] = new Tower(i, j, board[i][j]);
                    remainTower += 1;
                }
            }
        }
    }

    private static int parseInt(String input) {
        return Integer.parseInt(input);
    }
}