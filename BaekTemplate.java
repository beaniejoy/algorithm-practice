import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BaekTemplate {
    public static int solve(int[] questions) {
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] questions = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            questions[i] = Integer.parseInt(st.nextToken());
        }

        solve(questions);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write("answer");
        bw.flush();

        bw.close();
        br.close();
    }
}