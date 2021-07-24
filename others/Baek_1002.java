import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baek_1002 {
  public static int solve(int[] arr) {
    double length = Math.sqrt(Math.pow(arr[0] - arr[3], 2) + Math.pow(arr[1] - arr[4], 2));
    double sum = arr[2] + arr[5];
    double diff = Math.abs(arr[2] - arr[5]);

    /**
     * 경우의 수 총 4개 존재
     * - infinite	2개 point 일치 && r1, r2 길이 일치
     * - 2개 교점		point 간 거리 > r1, r2 길이차 or point 간 거리 < r1, r2 길이합
     * - 1개 교점		point 간 거리 = r1, r2 길이차 or point 간 거리 = r1, r2 길이합
     * - 0개 교점		point 간 거리 < r1, r2 길이차 or point 간 거리 > r1, r2 길이합
     */
    if (length == 0 && arr[2] == arr[5]) {
      return -1;
    }

    if (length < diff || length > sum) {
      return 0;
    } else if (length == diff || length == sum) {
      return 1;
    } else {
      return 2;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine()); // case

    int[] questions = new int[6];
    StringTokenizer st = null;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 6; j++) {
        questions[j] = Integer.parseInt(st.nextToken());
      }
      bw.write(String.valueOf(solve(questions)));
      bw.newLine();
    }
    bw.flush();

    bw.close();
    br.close();
  }
}