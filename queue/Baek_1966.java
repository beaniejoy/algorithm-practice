import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/1966
public class Baek_1966 {

  static class Node {
    private int index;

    private int order;

    Node(int index, int order) {
      this.index = index;
      this.order = order;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numCases = sc.nextInt();
    // print 대기 Q
    Queue<Node> printQ = new LinkedList<>();
    // 우선순위가 큰 순서부터 뽑기위한 Q
    Queue<Integer> orderQ = new PriorityQueue<Integer>(Collections.reverseOrder());

    // 결과를 넣기위한 array 생성
    int[] result = new int[numCases];
    int index = 0;

    for (int i = 0; i < numCases; i++) {

      int numDoc = sc.nextInt();
      int found = sc.nextInt();

      // number of Documents
      for (int j = 0; j < numDoc; j++) {
        int input = sc.nextInt();
        printQ.offer(new Node(j, input));
        orderQ.offer(input);
      }

      int order = 1;
      while (!printQ.isEmpty()) {
        Node tmp = printQ.poll();
        // Q안에 남아있는 데이터들 중 가장 우선순위가 큰 작업인지 비교
        if (tmp.order == orderQ.peek()) {
          if (tmp.index == found) {
            result[index++] = order;
            break;
          }

          order++;
          orderQ.poll();
        } else {
          printQ.offer(tmp);
        }
      }

      // 초기화
      printQ.clear();
      orderQ.clear();
    }

    for (int p : result)
      System.out.println(p);

    sc.close();
  }
}