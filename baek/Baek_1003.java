import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baek_1003 {
    public static int[][] solve(int T, int[] questions) {
        int max = 0;
        for(int i = 0; i < T; i++) {
            if(max < questions[i]) max = questions[i];
        }
        
        int[][] fibo = new int[max+1][2];
        
        fibo[0][0] = 1;
        fibo[0][1] = 0;
        fibo[1][0] = 0;
        fibo[1][1] = 1;
        
        for(int i = 2; i <= max; i++) {
            fibo[i][0] = fibo[i-1][0] + fibo[i-2][0];
            fibo[i][1] = fibo[i-1][1] + fibo[i-2][1];
        }
        
        int[][] answers = new int[T][2];
        for(int i = 0; i < T; i++) {
            answers[i][0] = fibo[questions[i]][0];
            answers[i][1] = fibo[questions[i]][1];
        }
        
        return answers;
    }
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine());
        
        int[] questions = new int[T];
        
        for(int i = 0; i < T; i++) {
            questions[i] = Integer.parseInt(br.readLine());
        }
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        for(int[] answer : solve(T, questions)) {
        	bw.write(answer[0] + " " + answer[1] + "\n");
        }
        bw.flush();
        
        bw.close();
        br.close();
    }
} 