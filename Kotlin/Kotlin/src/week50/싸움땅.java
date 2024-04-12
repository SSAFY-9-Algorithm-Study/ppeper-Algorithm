package week50;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Player {
    int number;
    int x, y, dir, basePower;
    Gun gun;
    public Player(int number, int x, int y, int dir, int basePower) {
        super();
        this.number = number;
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.basePower = basePower;
        gun = null;
    }

    int getPower() {
        if (gun == null) return basePower;
        else return basePower + gun.power;
    }

}

class Gun implements Comparable<Gun> {
    int power;

    public Gun(int power) {
        super();
        this.power = power;
    }

    @Override
    public int compareTo(Gun o) {
        // 총의 능력치가 큰 순으로 정렬
        return o.power - this.power;
    }

    @Override
    public String toString() {
        return String.valueOf(power);
    }
}

public class 싸움땅 {

    static int N, M, K;
    static int[] playerPoint;
    // 시계방향으로 회전 (상우하좌)
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Player[] players;
    static PriorityQueue<Gun>[][] gunMap;

    public static void main(String[] args) throws IOException {
        init();
        runApplication();
    }

    private static void runApplication() {
        for (int i = 1; i <= K; i++) {
            for (Player p : players) {
                playerMove(p);
            }
        }
        printResult();
    }

    // 플레이어 순차적으로 이동
    private static void playerMove(Player p) {
        int nx = p.x + dx[p.dir];
        int ny = p.y + dy[p.dir];
        // 격자를 벗어나면 정반대 방향으로 이동
        if (outMap(nx, ny)) p.dir = reverseDir(p.dir);

        // 실제 플레이어 위치 변경
        p.x += dx[p.dir];
        p.y += dy[p.dir];
        // 플레이어가 이동한 위치에 다른 플레이어가 있는지 확인
        Player player = findPlayerOrNull(p.number, p.x, p.y);
        // 플레이어가 없을 때 -> 해당 위치의 총을 얻음
        if (player == null) {
            if (!gunMap[p.x][p.y].isEmpty()) {
                getGunAtThisArea(p);
            }
        } else {
            // 플레이어가 있을 때(player) -> 싸움
            fightWithPlayer(p, player);
        }

    }

    private static void fightWithPlayer(Player player, Player otherPlayer) {
        // 해당 플레이어의 초기 능력치와 가지고 있는 총의 공격력의 합
        int myPower = player.getPower();
        int otherPower = otherPlayer.getPower();
        int score = Math.abs(myPower - otherPower);

        // 1. 상대방이 이겼을때
        if (myPower < otherPower) {
            getPoint(otherPlayer, score);
            lostPlayer(player);
            getGunAtThisArea(otherPlayer);
        } else if (otherPower < myPower) {
            // 내가 이겼을때
            getPoint(player, score);
            lostPlayer(otherPlayer);
            getGunAtThisArea(player);
        } else {
            // 비겼을 때 -> 기존의 초기 능력치가 높은 플레이어가 승리
            if (player.basePower < otherPlayer.basePower) {
                getPoint(otherPlayer, score);
                lostPlayer(player);
                getGunAtThisArea(otherPlayer);
            } else {
                getPoint(player, score);
                lostPlayer(otherPlayer);
                getGunAtThisArea(player);
            }
        }
    }

    // 본인이 가지고 있는 총을 해당 격자에 내려 놓고 이동
    private static void lostPlayer(Player p) {
        int x = p.x, y = p.y;
        Gun gun = p.gun;
        if (gun != null) {
            // 격자에 총을 둠
            gunMap[x][y].add(gun);
            p.gun = null;
        }
        // 원래 가지고 있던 방향으로 이동 -> 플레이어가 있거나 맵을 나가면 90도 회전
        while (true) {
            int nx = x + dx[p.dir];
            int ny = y + dy[p.dir];
            if (outMap(nx, ny) || findPlayerOrNull(p.number, nx, ny) != null) {
                p.dir = (p.dir + 1) % 4;
            } else {
                break;
            }
        }
        // 설제 플레이어 이동
        p.x += dx[p.dir];
        p.y += dy[p.dir];
        // 해당 격자에 총이 있으면 총을 얻음
        PriorityQueue<Gun> gunList = gunMap[p.x][p.y];
        if (!gunList.isEmpty()) {
            p.gun = gunList.poll();
        }

    }

    // 플레이가 점수를 얻음
    private static void getPoint(Player p, int score) {
        int number = p.number;
        playerPoint[number] += score;
    }


    // 총을 얻을 때 -> 현재 플레이어가 총을 가지고 있으면 가장 공격력이 쎈 총을 획득
    private static void getGunAtThisArea(Player p) {
        PriorityQueue<Gun> gunList = gunMap[p.x][p.y];
        if (p.gun != null) {
            gunList.add(p.gun);
        }
        // 우선순위에 따라 가장 강력한 총을 얻음
        Gun maxGun = gunList.poll();
        // 플레이어가 총을 얻음
        p.gun = maxGun;
    }

    private static Player findPlayerOrNull(int number, int x, int y) {
        for (Player p : players) {
            if (p.number == number) continue;
            if (p.x == x && p.y == y) {
                return p;
            }
        }
        return null;
    }

    private static int reverseDir(int dir) {
        return (dir + 2) % 4;
    }

    private static boolean outMap(int x, int y) {
        return (x < 0 || N <= x || y < 0 || N <= y);
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();
        for (int score : playerPoint) {
            sb.append(score + " ");
        };
        System.out.println(sb.toString());
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        K = parseInt(st.nextToken());
        gunMap = new PriorityQueue[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                gunMap[i][j] = new PriorityQueue<>();
            }
        }
        // 기존의 gunMap 만들기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int input = parseInt(st.nextToken());
                if (input != 0) {
                    gunMap[i][j].add(new Gun(input));
                }
            }
        }
        players = new Player[M];
        playerPoint = new int[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = parseInt(st.nextToken()) - 1;
            int y = parseInt(st.nextToken()) - 1;
            int d = parseInt(st.nextToken());
            int s = parseInt(st.nextToken());
            players[i] = new Player(i, x, y, d, s);
        }
    }

    private static int parseInt(String input) {
        return Integer.parseInt(input);
    }
}
