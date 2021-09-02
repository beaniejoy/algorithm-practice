import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1966
 * - issue
 * 우선순위큐 객체 생성보다 배열객체 생성하는 것이 더 가볍다.
 * 우선순위큐 내부적으로 max heap 사용해서 최대값 반환,
 * 배열 sorting은 quick sort 사용하는 것으로 보임
 * 이 두 개의 성능상 차이는 어느정도인지 알아봐야 할 듯
 */
public class Baek_1966 {

  private static class Node {
    private boolean target;
    private int importance;
    
    /**
     * @param target 몇번째 프린트 순서인지 알고자하는 대상 여부
     * @param importance 중요도
     */
    public Node(boolean target, int importance) {
      this.target = target;
      this.importance = importance;
    }
    
    /**
     * @return 원하는 대상인지 여부 반환
     */
    public boolean isTarget() {
      return this.target;
    }
  }

  public static int solve(Queue<Node> printQ, int[] importances) {
    int answer = 1;
    int idx = importances.length - 1;
    
    // 정렬
    Arrays.sort(importances);
    
    while(!printQ.isEmpty()) {
      Node node = printQ.poll();
      if(importances[idx] == node.importance) {
        if(node.isTarget()) {
          return answer;
        }
        
        idx--;
        answer++;
      } else {
        printQ.offer(node);
      }
      
    }
    
    return -1;
  }

  public static void main(String[] args) throws NumberFormatException, IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int totalCase = Integer.parseInt(br.readLine());
    int[] answers = new int[totalCase];
    
    StringTokenizer st = null;
    Queue<Node> printQ = new LinkedList<>();
    
    for(int i = 0; i < totalCase; i++) {
    st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    
    // 성능이 살짝 떨어진다. 
    // 단순배열 생성하는 것보다 Collection 프레임워크 객체 생성하고 사용하는 것이 더 무겁다.
    // PriorityQueue<Integer> importances = new PriorityQueue<>(Collections.reverseOrder());
    int[] importances = new int[N];
    st = new StringTokenizer(br.readLine());
    for(int j = 0; j < N; j++) {
      boolean target = false;
      int importance = Integer.parseInt(st.nextToken());
      
      if(j == M) target = true;
      printQ.add(new Node(target, importance));
      importances[j] = importance;
    }
      answers[i] = solve(printQ, importances);
      printQ.clear();
    }
    
    for(int answer : answers) {
      bw.write(String.valueOf(answer));
      bw.newLine();
    }
    
    bw.flush();

    bw.close();
    br.close();
  }
}
