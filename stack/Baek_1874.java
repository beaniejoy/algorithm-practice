import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Collection 말고 array로 풀어보기
 * 비교를 위해 두 개의 배열 필요
 * 	- 문제에 주어진 배열 
 * 	- pop, push 대상이되는 배열
 * 
 * https://www.acmicpc.net/problem/1874
 */
public class Baek_1874 {
	public static char[] solve(int[] arr, int N) {
		int curNum = 1;		// 1 ~ N 순차적으로 증가할 숫
		int curIdx = 0;		// 비교대상 array의 index
		int resultIdx = 0;	// 출력값을 담을 array의 index
		int index = 0;		// 주어진 array의 index
		
		int[] input = new int[N];
		char[] result = new char[N*2];
		
		while(true) {
			// push
			input[curIdx] = curNum;
			result[resultIdx++] = '+';
			
			// pop (주어진 arr의 요소와 같은 값인 경우)
			while(input[curIdx] == arr[index]) {
				result[resultIdx++] = '-';
				
				index++;
				curIdx--;
				
				if (curIdx < 0) break;
			}
			
			if(index >= N) break;
			if(curNum >= N) {
				result[0] = 'N';
				return result;
			}
			curIdx++;
			curNum++;
		}
		
		return result;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] output = new int[N];
        
        for (int i = 0; i < N; i++) {
        	output[i] = Integer.parseInt(br.readLine());
        }
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] result = solve(output, N);

        for(char c : result) {
        	if(c == 'N') {
        		bw.write("NO");
        		break;
        	}
        	bw.write(c);
        	bw.newLine();
        }
        
        bw.flush();

        bw.close();
        br.close();
	}
}