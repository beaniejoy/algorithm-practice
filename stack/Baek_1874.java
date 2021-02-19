import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

// https://www.acmicpc.net/problem/1874
public class Baek_1874 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int[] output = new int[N];
		ArrayList<String> result = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			output[i] = sc.nextInt();
		}

		sc.close();

		Stack<Integer> stack = new Stack<>();
		// output's index
		int index = 0;
		// count for pushing
		int pushCount = 0;

		while (pushCount < N) {
			if (stack.isEmpty() || stack.peek() != output[index]) {
				stack.push(++pushCount);
				result.add("+");
				continue;
			}

			while (true) {
				stack.pop();
				result.add("-");
				index++;

				if (stack.isEmpty() || stack.peek() != output[index])
					break;
			}
		}

		while (index < N) {
			if (stack.peek() != output[index]) {
				System.out.println("NO");
				return;
			}

			stack.pop();
			result.add("-");
			index++;
		}

		for (String s : result) {
			System.out.println(s);
		}
	}
}