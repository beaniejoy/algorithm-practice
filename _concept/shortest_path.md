# 최단 경로

- 가중치 그래프에서 간선의 가중치 합이 최소가 되도록 하는 경로

## 종류

- single source - single destination  
  하나의 출발점과 하나의 도착점 사이의 최단 경로 문제
- single source - all destinations
  하나의 출발점에서 다른 모든 노드 각각의 최단 경로를 구하는 문제
- all pairs shortest paths  
  그래프 내 모든 노드 쌍에 대한 최단 경로 구하는 문제

## Dijstra Algorithm

- single source shortest path 문제에 해당
