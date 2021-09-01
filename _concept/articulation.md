# Articulation Points

- 단절점
- 특정 point가 없어졌을 때 두 개 이상의 Connected Graph로 나뉘는 점을 단절점

## Biconnected graph

articulation point가 없는 connected graph

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
