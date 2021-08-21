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

### Adjacency Matrix

<img width="855" alt="스크린샷 2021-08-22 오전 12 24 19" src="https://user-images.githubusercontent.com/41675375/130327298-4142c0aa-b1c2-4a21-975f-84853c8f9d8c.png">

```java
public class Graph {
  private static final int VERTICES_SIZE = 10;
  private int[][] adjMatrix;

  public Graph() {
    adjMatrix = new int[VERTICES_SIZE][VERTICES_SIZE];
  }

  // ...

}
```
- `adj[A][B] = 1`: `(A, B)` or `<A, B>`
- `adj[A][B] = 0`: no edge

### Adjacency Lists

<img width="459" alt="스크린샷 2021-08-22 오전 1 17 51" src="https://user-images.githubusercontent.com/41675375/130328265-449648dd-f880-4c15-bf67-32970e00d6c7.png">


- Adjacency MultiLists
