```java
public class Articulation {
	private static final int MAX_VERTICES = 101;
	
	private Graph graph;
	private Graph.Node[] adjList;
	private int[] dfn;
	private int[] low;
	private int dfsCount;
	
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
	// 해당 node u가 articulation 조건에 부합하는지 여부 체크
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
	
	private boolean isRootArticulation(int root) {
		Graph.Node ptr = adjList[root].getNext();
		DFS dfs = new DFS(graph, MAX_VERTICES);
		int childCount = 0;
		boolean[] visited = dfs.getVisited();
		
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
	
	
	public static void main(String[] args) {
		Graph graph = new Graph(MAX_VERTICES);
		// vertex 입력
		graph.insertVertex(0);
		graph.insertVertex(1);
		graph.insertVertex(2);
		graph.insertVertex(3);
		graph.insertVertex(4);
		graph.insertVertex(5);
		graph.insertVertex(6);
		graph.insertVertex(7);
		graph.insertVertex(8);
		graph.insertVertex(9);
		// edge 입력
		graph.insertEdge(0, 1);
		graph.insertEdge(0, 3);
		graph.insertEdge(1, 2);
		graph.insertEdge(2, 3);
		graph.insertEdge(3, 4);
		graph.insertEdge(4, 5);
		graph.insertEdge(5, 6);
		graph.insertEdge(5, 8);
		graph.insertEdge(6, 7);
		graph.insertEdge(8, 9);
		
		Articulation articulation = new Articulation(graph, MAX_VERTICES);
    // node 3을 DFS의 루트노드로 시작
		articulation.dfnlow(3, 3);
		
		for(int i = 0; i < graph.getSize(); i++) {
			if(articulation.isArticulation(i)) {
				System.out.println(String.valueOf(i));
			}
		}
		
	}
}
```