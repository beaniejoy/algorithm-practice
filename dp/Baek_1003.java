import java.util.Scanner;

public class Baek_1003 {
  public static void solve(int T, int[] questions) {
    int max = 0;
    // 입력 값중 가장 큰 수 지정
    for (int i = 0; i < T; i++) {
      if (max < questions[i])
        max = questions[i];
    }
    // 가장 큰 수를 기준으로 Array생성
    int[] fibo = new int[max + 1];
    fibo[0] = 0;
    fibo[1] = 1;

    for (int i = 2; i <= max; i++) {
      fibo[i] = fibo[i - 1] + fibo[i - 2];
    }

    for (int i = 0; i < T; i++) {
      if (questions[i] == 0)
        System.out.println("1 0");
      else
        System.out.println(fibo[questions[i] - 1] + " " + fibo[questions[i]]);
    }

  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();

    int[] questions = new int[T];

    for (int i = 0; i < T; i++) {
      questions[i] = sc.nextInt();
    }

    solve(T, questions);

    sc.close();
  }
}