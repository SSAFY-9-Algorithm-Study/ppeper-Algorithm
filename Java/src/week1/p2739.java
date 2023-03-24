package week1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p2739 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[][] arr = new int[input[0]][input[1]];
		
		for (int i = 0; i < 2; i++) {
			for (int x = 0; x < arr.length; x++) {
				int[] buffer = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				for (int y = 0; y < arr[x].length; y++) {
					arr[x][y] += buffer[y]; 
				}
			}
		}
		for (int[] value : arr) {
			for (int i = 0; i < value.length; i++) {
				System.out.print(value[i] + " ");
			}
			System.out.println();
		}
	}
}
