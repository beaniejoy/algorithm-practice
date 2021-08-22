# Graph

## 그래프 종류

- 무방향 그래프(Undirected Graph)
  - 방향이 없는 그래프
  - 노드(Vertex)는 간선(Edge)을 통해 양방향으로 이동 가능
  - 노드 A - B 연결: `(A, B)` or `(B, A)` 둘 중 하나로 표시하면 됨
- 방향 그래프(Directed Graph)
  - 간선의 방향이 있는 그래프
  - 노드 A -> B: `<A, B>` (`<B, A>`와 다름)
- 가중치 그래프(Weighted Graph)
  - Network라고도 부름
  - 간선에 가중치가 존재하는 그래프
- 연결 그래프
  - 무방향 그래프에서 모든 노드에 대해 경로가 존재
- 비연결 그래프
  - 무방향 그래프에서 특정 노드에 대해 경로가 존재 X
- 사이클
  - 어떤 경로에서 시작노드와 종료 노드가 동일한 경우가 존재하는 그래프
- 비순환 그래프
  - 사이클이 없는 그래프
- 완전 그래프
  - 그래프 내 모든 노드가 서로 연결

## 그래프 vs 트리

- 그래프
  - 방향, 무방향 둘다 가능
  - 루트 노드 X
  - 부모, 자식 관계 존재 X
  - 단순히 노드와 노드가 연결되는 간선의 구성으로 이루어진 자료구조
  - 사이클 존재
- 트리
  - 방향만 가능
  - 루트 노드 존재
  - 부모, 자식 관계 존재

## 그래프 표현 방식

- Undirected Graph에 해당
  (Directed Graph에서는 구현이 달라짐)

### Adjacency Matrix

<img width="855" alt="스크린샷 2021-08-22 오전 12 24 19" src="https://user-images.githubusercontent.com/41675375/130327298-4142c0aa-b1c2-4a21-975f-84853c8f9d8c.png">

```java
public class Graph {
  private static final int VERTICES_SIZE = 10;
  private int[][] adjMatrix;

  public Graph() {
    adjMatrix = new int[VERTICES_SIZE][VERTICES_SIZE];
  }

  public boolean insertEdge(int a, int b) {
    if(adjMatrix[a][b] == 1)
      return false;
    
    // Undirected Graph에 해당
    adjMatrix[a][b] = 1;
    adjMatrix[b][a] = 1;
  }
  // ...

}
```
- `adj[A][B] = 1`: `(A, B)` or `<A, B>`
- `adj[A][B] = 0`: no edge

### Adjacency Lists

<img width="459" alt="스크린샷 2021-08-22 오전 1 17 51" src="https://user-images.githubusercontent.com/41675375/130328265-449648dd-f880-4c15-bf67-32970e00d6c7.png">

```java
public class Graph {
  private Node[] adjList;

  private int size;

  public Graph(int maxVertices) {
    adjList = new Node[maxVertices + 1];
    size = 0;
  }

  public boolean insertVertex(int vertex) {
    if(size > adjList.length)
      return false;
    
    if(vertex == 0)
      return false;
    
    adjList[vertex] = new Node(vertex);
    size++;
    return true;
  }

  // Undirected Graph에 해당
  // A, B의 LinkedList 둘다 담는다.
  public boolean insertEdge(int a, int b) {
    // 같은 노드에 대한 Edge 연결은 허용 X
    if(a == b) 
      return false;
    
    Node nodeA = adjList[a];
    Node nodeB = adjList[b];
    
    if(nodeA == null || nodeB == null)
      return false;
    
    // nodeA의 LinkedList에 NodeB 연결하기
    Node nextA = nodeA;
    while(nextA.getNext() != null) {
      nextA = nextA.getNext();
      
      if(nextA.equals(b))
        return false;
    }
    // A의 LinkedList에 넣는 Node의 next는 A와 관련된 Node들이 연결
    // nodeA를 곧장 넣으면 안된다.
    nextA.setNext(new Node(b));

    // nodeB의 LinkedList에 NodeA 연결하기
    Node nextB = nodeB;
    while(nextB.getNext() != null) {
      nextB = nextB.getNext();
      
      if(nextB.equals(a)) {
        // B - A 연결 시 
        // B의 LinkedList 안에 nodeA가 존재하면
        // 기존의 A의 LinkedList에 넣었던 nodeB정보도 없앤다.
        nextA.setNext(null);;
        return false;
      }
    }
    nextB.setNext(new Node(a));;
    
    return true;
  }

  public Node[] getAdjList() {
    return adjList;
  }

  // 다른 알고리즘 선언된 클래스에서 해당 Node class 사용하기 위해 public 선언
  public static class Node {
    private int vertex;
    
    private Node next;
    
    Node(int vertex) {
      this.vertex = vertex;
      this.next = null;
    }
    
    int getVertex() {
      return vertex;
    }
    
    void setNext(Node next) {
      this.next = next;
    }
    
    Node getNext() {
      return next;
    }

    boolean equals(int vertex) {
      if(this.vertex == vertex)
        return true;
      else
        return false;
    }
  }
}
```
- Adjacency Lists에서는 `A - B` Edge에 대해 `(A, B)`, `(B, A)` 둘 다 LinkedList에 연결해주어야 한다.
- LinkedList(next에 해당)에 넣어지는 Node와, adjList에 있는 Node는 다르다.  
  - `adjList에 있는 Node`가 연결된 Edge에 대한 LinkedList를 관리
- 위에서 구현한 LinkedList는 next 참조변수만 저장한 형태
  - java Collection Framework 중 `LinkedList` 사용해도됨
- `Hash Table`과 비슷한 구조