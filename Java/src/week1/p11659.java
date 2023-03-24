package week1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p11659 {
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder answer = new StringBuilder();
	    int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	    // 구간 합 저장 배열
	    int[] sum = new int[input[0] + 1];
	    for (int i = 0; i < sum.length; i++) {
			System.out.print(i + " ");
		}
//	    StringTokenizer st = new StringTokenizer(br.readLine());
//	    for (int i = 0; i < input[0]; i++) {
//			sum[i + 1] = sum[i] + parseInt(st.nextToken());
//		}
//	    for (int i = 0; i < input[1]; i++) {
//		    int[] index = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//		    int value = sum[index[1]] - sum[index[0] - 1];
//		    answer.append(value + "\n");
//		}
//	    System.out.println(answer);
	}
	
	static int parseInt(String input) {
		return Integer.parseInt(input);
	}
}
