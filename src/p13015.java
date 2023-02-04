import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class p13015 {
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(br.readLine());
	    printTop(n);
	    printMid(n);
	    printBottom(n);
	}
	
	static void nextLine() {
		System.out.println();
	}
	
	static void star() {
		System.out.print("*");
	}
	
	static void blank() {
		System.out.print(" ");
	}
	
	static void printBlank(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(" ");
		}
	}
	
	static void printAllStar(int n) {
		for (int i = 0; i < n; i++) {
			star();
		}
	}
	
	static void printStar(int n) {
		for (int i = 0; i < n; i++) {
			if (i == 0 || i == n - 1) {
				star();
			} else {
				blank();
			}
		}
	}
	
	static void printTop(int n) {
		int blank = 2 * n - 3;
		printAllStar(n);
		printBlank(blank);
		printAllStar(n);
		nextLine();
		for (int i = 1; i <= n - 2; i++) {
			blank -= 2;
			printBlank(i);
			printStar(n);
			printBlank(blank);
			printStar(n);
			nextLine();
		}
	}
	
	static void printBottom(int n) {
		int blank = 1;
		for (int i = n - 2; 0 < i; i--) {
			printBlank(i);
			printStar(n);
			printBlank(blank);
			printStar(n);
			nextLine();
			blank += 2;
		}
		printAllStar(n);
		printBlank(blank);
		printAllStar(n);
	}
	
	static void printMid(int n) {
		printBlank(n - 1);
		printStar(n);
		printBlank(n - 2);
		star();
		nextLine();
	}
}
