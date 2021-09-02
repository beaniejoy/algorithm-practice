import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baek_11726 {
	
	public static int solve(int N) {
		int[] arr = new int[N+1];
		
		arr[0] = 1;
		if(N > 0) arr[1] = 1;
		
		
		for(int i = 2; i <= N; i++) {
			arr[i] = (arr[i-1] + arr[i-2]) % 10007;
		}
		
		return arr[N];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(solve(N)));
		bw.flush();
		
		bw.close();
		br.close();
	}
}