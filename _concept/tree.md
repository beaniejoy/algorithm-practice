# Tree

## Connected Graph

<img width="345" alt="스크린샷 2021-09-05 오후 11 50 28" src="https://user-images.githubusercontent.com/41675375/132131178-473ee815-cc93-4589-83f5-2ef2fd469693.png">


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

## Biconnected Graph

<img width="333" alt="스크린샷 2021-09-05 오후 11 50 41" src="https://user-images.githubusercontent.com/41675375/132131308-4e25dcdb-a563-4b47-a555-f2c697264001.png">

- [articulation point](https://github.com/beaniejoy/algorithm-skillUp/blob/main/_concept/articulation.md)가 없는 Connected Graph

## Spanning Tree

<img width="804" alt="스크린샷 2021-09-05 오후 11 52 02" src="https://user-images.githubusercontent.com/41675375/132131224-9a566c63-1f97-43a8-8892-08355ed66890.png">

- Edges used by BFS, DFS don't make a cycle
  - BFS, DFS로 Spanning Tree를 만들 수 있음. (BFS/DFS Spanning Tree)
- 노드간 경로가 오직 하나 뿐인 그래프
- 순환이 없는 그래프

## Connected Components, Spanning Tree 비교
- cycle 유무와 관련이 깊다.
- Connected는 cycle이 존재할 수 있지만 Spanning은 cycle이 없음
- Connected Componets가 더 큰 범위의 개념
- Connected Components에서 두 가지 부분으로 쪼갤 수 있음
  - Tree edges (Spanning Tree에 속하는 edge들)
  - Non Tree edges (이외의 edge들) 

