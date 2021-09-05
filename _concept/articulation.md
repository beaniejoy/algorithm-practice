# Articulation Points

- 단절점
- 특정 point가 없어졌을 때 두 개 이상의 Connected Graph로 나뉘는 점을 단절점

## 구하는 방법

### depth-first numbers

- DFS 방식으로 DFS tree를 구성
- 방문한 순서대로 dfn array에 표시
- DFS tree 상 부모노드의 dfn은 자식노드의 dfn보다 크다.  
  (`dfn[ancestor] < dfn[descendent]`)

### Tree Edge & Non Tree Edge

- tree edge
  - DFS(혹은 BFS)로 만든 tree 상 포함된 edges
- non-tree edge  
  - graph에서 tree edge가 아닌 edge 중에 자식노드에서 부모노드로 이동할 수 있는 edges
- cross edge

### Root Node
- DFS tree에서 루트노드는 tree상 자식노드가 2개 이상이면 articulation point 해당

### Non Root Node
- tree상 루트노드 이외의 노드들은 조건이 까다롭다.
- 해당 노드의 자식노드들 중에서 해당 노드의 부모노드로 도달할 수 있는 경로가 없을 때 해당노드는 articulation point 해당
- 대상 노드(target)에서 그의 부모노드와 자식노드로 나누어서 생각
  - target보다 자식노드가 그의 부모노드로 target을 거치지않고 우회로 갈 수 있는 길이 있다면 단절점이 아니다.
  - 우회로 갈 수 없고 target을 거쳐야만 자식노드에서 부모노드로 갈 수 있는 점이라면 단절점에 해당

### 구현
- Articulation points를 찾기 위한 dfn 로직  
  (depth-first numbers)

#### Initialization

```java
public Articulation(Graph graph, int maxVertices) {
  this.graph = graph;
  this.adjList = graph.getAdjList();
  this.dfsCount = 0;
  this.dfn = new int[maxVertices];
  this.low = new int[maxVertices];
  
  for(int i = 0; i < graph.getSize(); i++) {
    this.dfn[i] = -1;
    this.low[i] = -1;
  }
}
```

```java
// v is the parent of u
public void dfnlow(int u, int v) {
  dfn[u] = low[u] = dfsCount++;
  
  Graph.Node ptr = adjList[u].getNext();
  int w;
  for(; ptr != null; ptr = ptr.getNext()) {
    w = ptr.getVertex();
    
    if(dfn[w] < 0) {
      dfnlow(w, u);
      low[u] = min(low[u], low[w]);
    } else if(w != v)
      low[u] = min(low[u], dfn[w]);
  }
}

private int min(int a, int b) {
  return a < b ? a: b;
}
```

- Articulation point 판별
```java
public boolean isArticulation(int u) {
  Graph.Node ptr = adjList[u].getNext();
  
  // DFS tree상 root node일 때
  if(dfn[u] == 0)
    return isRootArticulation(u);
  
  int w;
  
  for(; ptr != null; ptr = ptr.getNext()) {
    w = ptr.getVertex();
    // 만약 u에 연결되어 있는 w가 dfs 순서가 더 작으면 w가 부모노드
    if(dfn[w] < dfn[u])
      continue;
    
    if(low[w] >= dfn[u]) {
      return true;
    }
  }
  
  return false;
}
// root node일 때 따로 판별
// root node의 자식노드의 개수가 2이상일 때 단절점 해당(DFS tree 상에서)
private boolean isRootArticulation(int root) {
  Graph.Node ptr = adjList[root].getNext();
  DFS dfs = new DFS(graph, MAX_VERTICES);
  int childCount = 0;
  boolean[] visited = dfs.getVisited();
  // root node는 방문했다고 체크해야함
  // root node 기점으로 자식노드 구별해야하기에
  visited[root] = true;

  for(; ptr != null; ptr = ptr.getNext()) {
    if(!visited[ptr.getVertex()]) {
      childCount++;
      dfs.dfsIterative(ptr.getVertex());
    }
  }
  
  if(childCount > 1) return true;
  else return false;
}
```