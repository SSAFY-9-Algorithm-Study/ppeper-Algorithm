package week49;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 모노미노도미노_2 {

    static int N, score, tileCount;
    static int[][] green, blue;
    static int[][] info;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        info = new int[N][3];
        for (int i = 0; i < N; i++) {
            info[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        green = new int[6][4];
        blue = new int[6][4];
        runApplication();
        System.out.println(score);
        System.out.println(tileCount);
    }

    // 타일 info에 맞게두기
    private static void runApplication() {
        for (int[] op : info) {
            int t = op[0];
            int x = op[1];
            int y = op[2];

            putGreenTile(t, x, y);
            checkBlock(green);
            deleteBoundary(green);
            putBlueTile(t, y, x);
            checkBlock(blue);
            deleteBoundary(blue);
        }
        getBoardTileCount(green);
        getBoardTileCount(blue);
    }

    // 보드의 줄이 맞았는지 확인하기
    private static void checkBlock(int[][] board) {
        for (int i = board.length - 1; 2 <= i; i--) {
            boolean checkFull = true;
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    checkFull = false;
                    break;
                }
            }
            if (checkFull) {
                score++;
                // i행 한줄 제거
                deleteLine(board, i);
                // i행 아래로 밀기
                for (int a = (i - 1); 0 <= a; a--) {
                    for (int k = 0; k < board[0].length; k++) {
                        board[a + 1][k] = board[a][k];
                    }
                }
                // 밀린 첫째줄은 제거
                deleteLine(board, 0);
                i++; // 밀렸으므로 다시 현재행부터 시작
            }
        }
    }


    // 행 한줄 제거
    private static void deleteLine(int[][] board, int row) {
        for (int i = 0; i < board[0].length; i++) {
            board[row][i] = 0;
        }
    }

    private static void putGreenTile(int t, int x, int y) {
        if (t == 1) {
            int h = 0;
            while (h <= 5 && green[h][y] == 0) {
                h++;
            }
            h--;
            green[h][y] = t;
        } else if (t == 2) {
            // 1 * 2사이즈 -> 열 두개확인
            int h = 0;
            while (h <= 5 && green[h][y] == 0 && green[h][y + 1] == 0) {
                h++;
            }
            h--;
            green[h][y] = t;
            green[h][y + 1] = t;
        } else {
            int h = 0;
            // 2 * 1 사이즈 -> 행두개 확인
            while (h <= 4 && green[h][y] == 0 && green[h + 1][y] == 0) {
                h++;
            }
            h--;
            green[h][y] = t;
            green[h + 1][y] = t;
        }
    }

    private static void putBlueTile(int t, int x, int y) {
        if (t == 1) {
            int h = 0;
            while (h <= 5 && blue[h][y] == 0) {
                h++;
            }
            h--;
            blue[h][y] = t;
        } else if (t == 2) {
            // 1 * 2사이즈 -> 열 두개확인
            int h = 0;
            while (h <= 4 && blue[h][y] == 0 && blue[h + 1][y] == 0) {
                h++;
            }
            h--;
            blue[h][y] = t;
            blue[h + 1][y] = t;
        } else {
            int h = 0;
            // 2 * 1 사이즈 -> 행두개 확인
            while (h <= 5 && blue[h][y] == 0 && blue[h][y + 1] == 0) {
                h++;
            }
            h--;
            blue[h][y] = t;
            blue[h][y + 1] = t;
        }
    }

    // 보드의 타일의 옅은 부분 확인
    private static void deleteBoundary(int[][] board) {
        int count = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (0 < board[i][j]) {
                    count++;
                    break;
                }
            }
        }
        // 제거할 count 수 만큼 밀어주어야 한다
        int start = (board.length - 1 - count);
        for (int a = start; 0 <= a; a--) {
            for (int k = 0; k < board[0].length; k++) {
                board[a + count][k] = board[a][k];
            }
        }
        // 경계선에 있는 보드는 제거
        for (int i = 0; i < 2; i++) {
            deleteLine(board, i);
        }
    }

    private static void getBoardTileCount(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (0 < board[i][j]) tileCount++;
            }
        }
    }
}