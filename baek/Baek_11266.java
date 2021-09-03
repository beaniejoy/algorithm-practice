import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baek_11266 {
	
	private static class Graph {
		int[] dfn;
		int[] low;
		boolean[] visited;
		boolean[] articulation;
		Node[] adjList;
		int dfnCount;
		
		Graph(int maxSize) {
			adjList = new Node[maxSize + 1];
			dfn = new int[maxSize + 1];
			low = new int[maxSize + 1];
			visited = new boolean[maxSize + 1];
			articulation = new boolean[maxSize + 1];
			dfnCount = 0;
			
			for(int i = 0; i <= maxSize; i++) {
				this.dfn[i] = -99;
				this.low[i] = -99;
			}
		}
		
		void insertVertex(int v) {
			if(adjList[v] != null)
				return;
			
			adjList[v] = new Node(v, null);
			dfn[v] = -1;
			low[v] = -1;
		}
		
		boolean insertEdge(int u, int v) {
			insertVertex(u);
			insertVertex(v);
			
			// (u, v)
			Node nextU = adjList[u];
			while(nextU.getNext() != null) {
				nextU = nextU.getNext();
				
				if(nextU.getVertex() == v)
					return false;
			}
			
			nextU.setNext(new Node(v, null));
			
			// (v, u)
			Node nextV = adjList[v];
			while(nextV.getNext() != null) {
				nextV = nextV.getNext();
				
				if(nextV.getVertex() == u) {
					nextU.setNext(null);
					return false;
				}
			}
			
			nextV.setNext(new Node(u, null));
			return true;
		}
		
		void dfnlow(int u, int v) {
			dfn[u] = low[u] = dfnCount++;
			Node child = adjList[u].getNext();
			int w;
			for(; child != null; child = child.getNext()) {
				w = child.getVertex();
				if(dfn[w] == -1) {
					dfnlow(w, u);
					low[u] = min(low[u], low[w]);
				} else if(w != v) {
					low[u] = min(low[u], dfn[w]);
				}
			}
		}
		
		void dfs(int u) {
			if(visited[u])
				return;
			
			visited[u] = true;
			
			Node next = adjList[u].getNext();
			for(; next != null; next = next.getNext()) {
				int nextVertex = next.getVertex();
				if(!visited[nextVertex]) {
					if(dfn[u] > 0 && dfn[u] <= low[nextVertex])
						articulation[u] = true;
					dfs(nextVertex);
				}
			}
		}
		
		void isArticulation(int root) {
			visited[root] = true;
			
			int childCount = 0;
			
			Node child = adjList[root].getNext();
			for(; child != null; child = child.getNext()) {
				int childVertex = child.getVertex();
				if(!visited[childVertex]) {
					childCount++;
					dfs(childVertex);
				}
			}
			
			if(childCount > 1)
				articulation[root] = true;
		}
		
		private int min(int a, int b) {
			return a < b ? a: b;
		}
		
	}
	
	private static class Node {
		int vertex;
		Node next;
		
		Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}
		
		public int getVertex() {
			return vertex;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int u;
		int v;
		Graph graph = new Graph(V);
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				
				graph.insertEdge(u, v);
		}
		
		for(int i = 1; i <= V; i++) {
			if(graph.dfn[i] == -1) {
				graph.dfnCount = 0;
				graph.dfnlow(i, i);
				graph.isArticulation(i);
			}
		}
		
		StringBuffer sb = new StringBuffer();
		int artCount = 0;
		
		for(int i = 1; i <= V; i++) {
			if(graph.articulation[i]) {
				artCount++;
				
				sb.append(i);
				sb.append(" ");
			}
		}
		
		bw.write(String.valueOf(artCount));
		if(artCount > 0) {
			bw.newLine();
			sb.deleteCharAt(sb.lastIndexOf(" "));
			bw.write(sb.toString());
		}
		
		bw.flush();

		bw.close();
		br.close();
	}
	
}
