package week49;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Santa implements Comparable<Santa> {
    int r, c;
    int stun;

    public Santa(int r, int c) {
        super();
        this.r = r;
        this.c = c;
        stun = -2;
    }

    @Override
    public int compareTo(Santa o) {
        if (this.r == o.r) {
            return o.c - this.c;
        }
        return o.r - this.r;
    }
}

public class 루돌프의_반란 {


    static int N, M, P, C, D, remainSanta;
    // 상우하좌,대각선
    static int[] dx = {-1, 0, 1, 0, -1, 1, 1, -1};
    static int[] dy = {0, 1, 0, -1, 1, 1, -1, -1};
    static Santa[] santas;
    static int[][] board;
    static int rudolfX, rudolfY;
    static int[] santaScore; // 산타가 얻은 최종 점수
    static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        init();
        runApplication();
    }

    private static void runApplication() {
        answer = new StringBuilder();
        for (int turn = 1; turn <= M; turn++) {
            // 산타가 다 사라지면 바로 종료
            if (remainSanta == 0) break;
            ruDolfMove(turn);
            santaMove(turn);
            givePlusScore();
        }
        // 최종 스코어 출력
        for (int i = 1; i <= P; i++) {
            answer.append(santaScore[i] + " ");
        }
        System.out.println(answer.toString());
    }

    // 매턴마다 탈락하지 않은 산타들 점수 1점 부여
    private static void givePlusScore() {
        for (int i = 1; i <= P; i++) {
            if (santas[i] == null) continue;
            santaScore[i] += 1;
        }
    }

    private static void santaMove(int turn) {
        // 1 ~ P번까지 순서대로 움직임
        for (int number = 1; number <= P; number++) {
            Santa santa = santas[number];
            if (santa == null) continue;

            // 스턴이면 현재턴 패스
            if (turn < santa.stun + 2) continue;

            int x = santa.r, y = santa.c;
            int currRudolfToSanta = distance(rudolfX, rudolfY, x, y);
            int dir = -1;
            // 4방향으로 이동하며 루돌프랑 가장가까워지는 방향으로 이동
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (outBoard(nx,  ny)) continue;
                // 다른 산타가 있으면 이동 불가능
                if (board[nx][ny] != 0) continue;
                int dist = distance(rudolfX, rudolfY, nx, ny);
                // 가까워지는 방향으로 이동 해야함
                if (dist < currRudolfToSanta) {
                    currRudolfToSanta = dist;
                    dir = i;
                }
            }


            // 이동 불가능
            if (dir == -1) continue;

            board[x][y] = 0; // 이전의 산타위치 초기화
            int nx = x + dx[dir], ny = y + dy[dir];
            // 루돌프랑 충돌이 일어난다면
            if (nx == rudolfX && ny == rudolfY) {
                // D의 점수를 얻는다
                santaScore[number] += D;
                santa.stun = turn;

                // 산타가 움직여서 충돌이 일어났으므로 현재 dir의 반대방향으로 이동
                int moveX = -dx[dir], moveY = -dy[dir];
                // 밀려가는 칸 확인
                int cx = nx + moveX * D, cy = ny + moveY * D;

                if (outBoard(cx, cy)) {
                    santas[number] = null;
                    remainSanta--;
                } else {
                    if (board[cx][cy] != 0) {
                        // 현재 상호작용으로 밀리는 산타
                        interactionSanta(board[cx][cy], moveX, moveY);
                    }
                    santa.r = cx;
                    santa.c = cy;
                    board[cx][cy] = number;
                }

            } else {
                // 산타 이동
                santa.r = nx;
                santa.c = ny;
                board[nx][ny] = number;
            }
        }

    }

    // 루돌프의 이동
    private static void ruDolfMove(int turn) {
        // 가장가까운 산타 구하기
        Santa nearSanta = getNearSanta();
        int x = rudolfX;
        int y = rudolfY;
        int dir = 0, minDist = Integer.MAX_VALUE;
        // 8 방향중에 가장 가까운 방향으로 이동
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (outBoard(nx, ny)) continue;
            int dist = distance(nx, ny, nearSanta.r, nearSanta.c);
            // 가장 가까워지는 방향으로 업데이트
            if (dist < minDist) {
                minDist = dist;
                dir = i;
            }
        }
        // 루돌프 위치 변경
        rudolfX += dx[dir];
        rudolfY += dy[dir];


        // 산타와 충돌 확인
        int number = board[rudolfX][rudolfY];
        if (number != 0) {
            // C의 점수를 얻고 루돌프가 이동한 방향으로 C칸 밀려남
            santaScore[number] += C;
            Santa santa = santas[number];
            santa.stun = turn;

            int sx = santa.r, sy = santa.c;
            board[sx][sy] = 0; // 기존 산타위치 초기화
            // 이동하려는 좌표
            int nx = sx + dx[dir] * C, ny = sy + dy[dir] * C;

            // 산타가 나간다면 게임에서 제외됨
            if (outBoard(nx, ny)) {
                santas[number] = null;
                remainSanta--;
            } else {
                // 밀리는 위치로 변경 -> 다른 산타가 있다면 상호작용 발생
                if (board[nx][ny] != 0) {
                    // 현재 상호작용으로 밀리는 산타
                    interactionSanta(board[nx][ny], dx[dir], dy[dir]);
                }
                santa.r = nx;
                santa.c = ny;
                board[nx][ny] = number;
            }
        }

    }

    // 연쇄적으로 dir 방향으로 밀리면서 다른 산타가 있으면 밀림
    private static void interactionSanta(int number, int moveX, int moveY) {
        Santa curr = santas[number];
        int x = curr.r, y = curr.c;
        int nx = x + moveX, ny = y + moveY;
        board[x][y] = 0;

        // 상호작용하다가 게임에서 제외된 santa
        if (outBoard(nx, ny)) {
            santas[number] = null;
            remainSanta--;
        } else {
            // 다른 산타가 있으면 연쇄작용으로 밀어줌
            if (board[nx][ny] != 0) {
                // 현재 상호작용으로 밀리는 산타
                interactionSanta(board[nx][ny], moveX, moveY);
            }
            curr.r = nx;
            curr.c = ny;
            board[nx][ny] = number;
        }
    }

    private static Santa getNearSanta() {
        // r, c의 좌표의 우선순위에 따라 가능한 산타중 첫번째로 나오는 요소가 해당함.
        PriorityQueue<Santa> pq = new PriorityQueue<Santa>();
        int dist = Integer.MAX_VALUE;
        for (Santa santa: santas) {
            if (santa == null) continue;

            int currDist = distance(rudolfX, rudolfY, santa.r, santa.c);
            if (currDist < dist) {
                pq.clear();
                dist = currDist;
                pq.add(santa);
            } else if (currDist == dist) {
                pq.add(santa);
            }
        }
        return pq.poll();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        P = parseInt(st.nextToken());
        C = parseInt(st.nextToken());
        D = parseInt(st.nextToken());
        board = new int[N][N];
        // 산타들의 수
        santas = new Santa[P + 1];
        santaScore = new int[P + 1];
        remainSanta = P;
        st = new StringTokenizer(br.readLine());
        rudolfX = parseInt(st.nextToken()) - 1;
        rudolfY = parseInt(st.nextToken()) - 1;
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int num = parseInt(st.nextToken());
            int r = parseInt(st.nextToken()) - 1;
            int c = parseInt(st.nextToken()) - 1;
            santas[num] = new Santa(r, c);
            board[r][c] = num;

        }
    }

    private static int parseInt(String input) {
        return Integer.parseInt(input);
    }

    private static int distance(int r1, int c1, int r2, int c2) {
        return (int) (Math.pow(r1 - r2, 2.0) + Math.pow(c1 - c2, 2.0));
    }

    private static boolean outBoard(int x, int y) {
        return (x < 0 || N <= x || y < 0 || N <= y);
    }
}