import java.util.Scanner;

public class Beak_11726 {

  public static void solve(int N) {
    int[] arr = new int[N + 1];
    arr[0] = 1;
    arr[1] = 1;
    for (int i = 2; i <= N; i++) {
      // N이 1000이 넘어가면 원치않은 결과값 출력
      // 배열에 넣을 때 10007 나눈 나머지 입력
      arr[i] = (arr[i - 1] + arr[i - 2]) % 10007;
    }
    System.out.print(arr[N]);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    solve(N);
    sc.close();
  }
}