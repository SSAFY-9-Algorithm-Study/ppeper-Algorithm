package week49;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Person {
    int x, y;

    public Person(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }
}

public class 메이즈_러너 {

    static int N, M, K, remainPerson, moveTotal;
    static int[][] maze;
    static int[] exit; // 출구의 좌표
    static Person[] persons;
    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        init();
        runApplication();
    }

    private static void runApplication() {
        // K초 동안 탈출 시작
        for (int i = 0; i < K; i++) {
            // 모든 참가자가 미로를 탈출함
            if (remainPerson == 0) break;
            personMove();
        }
        printResult();
    }

    private static void personMove() {
        for (int i = 0; i < M; i++) {
            // 이미 참가자가 출구를 나감;
            if (persons[i] == null) continue;
            moveToMostCloseExit(i);
        }
        if (remainPerson == 0) return;
        mazeRotate();
    }

    private static void mazeRotate() {
        int[] result = findMostSmallSquare();
        // 정사각형의 크기 및 시작점
        int size = result[0];
        int x = result[1];
        int y = result[2];
        // 찾은 정사각형 90도 회전
        rotateSquare(size, x, y);
    }

    private static void rotateSquare(int size, int x, int y) {
        // 먼저 참가자들도 회전해야 하므로 해당 정사각형에 포함된 참가자들을 구해준다.
        Set<Integer> set = getPersonInSquare(size, x, y);
        // 참가자들 좌표 90도 회전
        for (int number : set) {
            Person p = persons[number];
            int currX = p.x - x, currY = p.y - y;
            // 90도 회전 -> (i, j) -> (j, size - 1 - i)
            p.x = currY + x;
            p.y = size - 1 - currX + y;
        }
        // 탈출구 좌표 회전
        int ex = exit[0] - x;
        int ey = exit[1] - y;
        exit[0] = ey + x;
        exit[1] = size - 1 - ex + y;
        // 찾은 정사각형 회전
        int[][] square = new int[N][N];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                int value = maze[i][j] - 1;
                if (value < 0) value = 0;
                // 회전했을때의 x, y 좌표

                int nx = i - x, ny = j - y;
                int temp = nx;
                nx = ny + x;
                ny = size - 1 - temp + y;
                square[nx][ny] = value;
            }
        }
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                maze[i][j] = square[i][j];
            }
        }

    }

    private static Set<Integer> getPersonInSquare(int size, int x, int y) {
        Set<Integer> set = new HashSet<>();

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                for (int number = 0; number < persons.length; number++) {
                    Person p = persons[number];
                    if (p == null) continue;
                    if (i == p.x && j == p.y) set.add(number);
                }
            }
        }
        return set;
    }

    // 한 명 이상의 참가자와 출구를 포함한 가장 작은 정사각형을 찾는다
    private static int[] findMostSmallSquare() {
        int size = 1; // 가장 작은 정사각형부터 확인
        while (size <= N) {
            // size크기의 정사각형을 만들 수 있는 좌측상단 시작점 범위
            for (int startX = 0; startX <= N - size; startX++) {
                for (int startY = 0; startY <= N - size; startY++) {
                    boolean findPerson = false;
                    boolean findExit = false;
                    // 정사각형 확인
                    for (int i = startX; i < startX + size; i++) {
                        for (int j = startY; j < startY + size; j++) {
                            // 출구를 포함
                            if (i == exit[0] && j == exit[1]) findExit = true;
                            // 사람을 포함
                            if (includePerson(i, j)) findPerson = true;

                            // 조건에 만족하는 정사각형 찾음 -> 종료
                            if (findPerson && findExit) {
                                return new int[]{size, startX, startY};
                            }
                        }
                    }
                }
            }
            size++;
        }
        // while문에서 종료가 안되면 기본적으로 N * N사이즈
        return new int[]{N, 0, 0};
    }

    private static boolean includePerson(int i, int j) {
        for (Person p : persons) {
            if (p == null) continue;
            if (p.x == i && p.y == j) return true;
        }
        return false;
    }

    // 현재 참가자가 출구로 이동하는 가장 가까운 방향으로 이동
    private static void moveToMostCloseExit(int number) {
        Person p = persons[number];
        int currDistanceToExit = distanceToExit(p.x, p.y);
        int dir = -1;
        for (int i = 0; i < 4; i++) {
            int nx = p.x + dx[i];
            int ny = p.y + dy[i];

            if (outMaze(nx, ny)) continue;
            if (0 < maze[nx][ny]) continue;

            int dist = distanceToExit(nx, ny);
            // 가까워지는 방향으로 이동
            if (dist < currDistanceToExit) {
                currDistanceToExit = dist;
                dir = i;
            }
        }
        // 이동이 가능하면 현재 참가자 이동
        if (dir != -1) {
            p.x += dx[dir];
            p.y += dy[dir];
            moveTotal += 1;
            // 참가자가 나갔는지 확인
            if (p.x == exit[0] && p.y == exit[1]) {
                persons[number] = null;
                remainPerson -= 1;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        K = parseInt(st.nextToken());
        maze = new int[N][N];
        persons = new Person[M];
        exit = new int[2];
        remainPerson = M;
        moveTotal = 0;
        // 미로 board 초기화
        for (int i = 0; i < N; i++) {
            maze[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        // 참가자들 위치 초기화
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = parseInt(st.nextToken()) - 1;
            int y = parseInt(st.nextToken()) - 1;
            persons[i] = new Person(x, y);
        }
        // 초기 탈출 위치
        st = new StringTokenizer(br.readLine());
        int ex = parseInt(st.nextToken()) - 1;
        int ey = parseInt(st.nextToken()) - 1;
        exit[0] = ex;
        exit[1] = ey;
    }

    private static boolean outMaze(int x, int y) {
        return (x < 0 || N <= x || y < 0 || N <= y);
    }

    private static int distanceToExit(int x, int y) {
        return Math.abs(x - exit[0]) + Math.abs(y - exit[1]);
    }

    private static void printResult() {
        // 모든 참가지들이 이동한 거리 출력
        System.out.println(moveTotal);
        // 출구 위치 출력
        System.out.println((exit[0] + 1) + " " + (exit[1] + 1));
    }

    private static int parseInt(String input) {
        return Integer.parseInt(input);
    }
}