import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Baek_5397 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int numCases = sc.nextInt();
    Stack<String> stack = new Stack<>();
    Stack<String> tmpStack = new Stack<>();

    List<String> result = new ArrayList<>();
    sc.nextLine();

    for (int i = 0; i < numCases; i++) {

      String[] inputArray = sc.nextLine().split("");

      for (String input : inputArray) {
        switch (input) {
          case "<":
            if (!stack.isEmpty()) {
              String pop = stack.pop();
              tmpStack.push(pop);
            }
            break;
          case ">":
            if (!tmpStack.isEmpty()) {
              String pop = tmpStack.pop();
              stack.push(pop);
            }
            break;
          case "-":
            if (!stack.isEmpty())
              stack.pop();
            break;
          default:
            stack.push(input);
        }
      }

      StringBuffer sb = new StringBuffer();
      // # 주의 #
      // 왼쪽("<")으로 이동하고 input을 한다음에 그냥 엔터를 치는 경우도 존재
      // "<" 경우 때 임시로 보관하고 있는 tmpStack으로 몰아주면 된다.
      while (!stack.isEmpty())
        tmpStack.push(stack.pop());

      while (!tmpStack.isEmpty())
        sb.append(tmpStack.pop());

      result.add(sb.toString());
    }

    sc.close();

    for (String s : result)
      System.out.println(s);
  }
}