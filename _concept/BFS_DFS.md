# BFS, DFS

- BFS
  - 너비 우선 탐색
  - Breath First Search
- DFS
  - 깊이 우선 탐색
  - Depth First Search

## BFS 구현




## DFS 구현

### Recursive 방법

```java
public class DFS {
  private boolean[] visited;
  // Graph 게시글 참고
  private Graph.Node[] adjList;

  public DFS(Graph graph) {
    visited = new boolean[Graph.MAX_VERTICES];
    adjList = graph.getAdjList();
  }

  public void dfsRecursive(int v) {
    
    if(visited[v])
      return;
    
    visited[v] = true;
    // 방문한 Vertex 체크
    System.out.println(String.valueOf(v));
    
    Graph.Node next = adjList[v].getNext();
    while(next != null) {
      if(!visited[next.getVertex()])
        dfsRecursive(next.getVertex());
      
      next = next.getNext();
    }
  }
}
```
