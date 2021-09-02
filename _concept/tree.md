# Tree

## Connected Components

- 연결되어 있는 Vertex들의 구성
- DFS, BFS로 Connected Components 찾을 수 있음

```java
void connected() {
  for(int i = 0; i < n; i++) {
    if(!visited[i]) {
      dfs(i);
      // End of Connected Component
      System.out.println(String.valueOf(i));
    }
  }
}
```

## Spanning Tree

- Edges used by BFS, DFS don't make a cycle
  - BFS, DFS로 Spanning Tree를 만들 수 있음. (BFS/DFS Spanning Tree)
- 노드간 경로가 오직 하나 뿐인 그래프
- 순환이 없는 그래프
- Connected Components와 차이점
  - cycle 유무와 관련이 깊다.
  - Connected는 cycle이 존재할 수 있지만 Spanning은 cycle이 없음
  - Connected Componets가 더 큰 범위의 개념
- Connected Components에서 두 가지 부분으로 쪼갤 수 있음
  - 1. Tree edges (Spanning Tree에 속하는 edge들)
  - 2. Non Tree edges (이외의 edge들) 